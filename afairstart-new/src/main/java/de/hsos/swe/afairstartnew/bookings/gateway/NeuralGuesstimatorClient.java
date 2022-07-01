package de.hsos.swe.afairstartnew.bookings.gateway;

import de.hsos.swe.afairstartnew.bookings.entity.BookingImportDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;

/**
 * As of the original class diagram, the NeuralGuesstimator is a REST client.
 * Executing the NeuralGuesstimator in Java and reading it's output would be a better idea.
 */
@RegisterRestClient(baseUri = "http://localhost:8123/api")
public interface NeuralGuesstimatorClient {

    @POST
    long getExpectedDuration(BookingImportDTO booking);
}
