package de.hsos.swe.afairstart.booking.gateway.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.swe.afairstart.booking.control.DeviceService;
import de.hsos.swe.afairstart.booking.entity.Device;

@Transactional
@ApplicationScoped
public class DeviceRepos implements DeviceService {

    @Inject 
    EntityManager em;
    
    @Override
    public Device getDevice(int deviceID) {
        try {
            return em.find(Device.class, deviceID);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return null;
    }

    @Override
    public List<Device> getAllDevices() {
        return em.createQuery("Select d From Device d", Device.class).getResultList();
    }
    
}
