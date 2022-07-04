package de.hsos.swe.afairstart.bookings.gateway;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import de.hsos.swe.afairstart.bookings.entity.Booking;
import de.hsos.swe.afairstart.bookings.entity.NeuralDAO;
import de.hsos.swe.afairstart.devices.entity.DeviceType;
import de.hsos.swe.afairstart.users.entity.UserExportDTO;
import de.hsos.swe.afairstart.users.gateway.UsersRepository;

/**
 * As of the original class diagram, the NeuralGuesstimator is a REST client.
 * Executing the NeuralGuesstimator in Java and reading it's output would be a better idea.
 */
//@RegisterRestClient(baseUri = "http://localhost:8123/api")
@ApplicationScoped
public class NeuralGuesstimatorClient {

    @Inject
    UsersRepository userRepo;

    public Long getExpectedDuration(NeuralDAO neuralDAO){

        long intendedDuration = neuralDAO.intendedDuration;
        long level = neuralDAO.level;
        ArrayDeque<Long> recentBookings = neuralDAO.recentBookings;
        DeviceType device = neuralDAO.device;

        String path = "src/main/java/de/hsos/swe/afairstart/bookings/gateway/models/" + device + "/" + level + ".h5";

        try{
            File kerasModel = new File(path);
            MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(
                kerasModel.getAbsolutePath(), false);
            int inputs = 6;
            INDArray features = Nd4j.zeros(inputs, inputs);
            Iterator<Long> it = recentBookings.iterator();
            for(int i = 0; i < inputs-1; i++){
                if(it.hasNext()){
                    features.putScalar(new int[] {i}, it.next());
                } else {
                    features.putScalar(new int[] {i}, 1);
                }
            }
            features.putScalar(new int[] {5}, intendedDuration);
            double prediction = model.output(features).getDouble(0);
            System.out.println(prediction);
            return Long.valueOf((int)prediction);
        } catch(Exception e){
            System.out.println(e);
        }
        return Long.valueOf(0);
    }
}    

