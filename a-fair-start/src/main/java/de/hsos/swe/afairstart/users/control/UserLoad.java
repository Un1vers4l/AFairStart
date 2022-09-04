package de.hsos.swe.afairstart.users.control;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import de.hsos.swe.afairstart.devices.control.DevicesService;
import de.hsos.swe.afairstart.devices.entity.DeviceImportDTO;
import de.hsos.swe.afairstart.devices.entity.DeviceType;
import de.hsos.swe.afairstart.users.entity.UserImportDTO;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class UserLoad {

    @Inject
    UserService service;

    @Inject
    DevicesService devices;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        UserImportDTO admin = new UserImportDTO("admin", "admin", "admin", "Administrator");
        service.create(admin);
        UserImportDTO user = new UserImportDTO("user", "user", "user", "Max Mustermann");
        service.create(user);

        DeviceImportDTO threeD = new DeviceImportDTO(DeviceType.ThreeDimensionalPrinter);
        DeviceImportDTO printer = new DeviceImportDTO(DeviceType.Printer);
        devices.create(threeD);
        devices.create(printer);
    } 
}