package de.hsos.swe.abetterstart.devices.control;

import de.hsos.swe.abetterstart.devices.entity.DeviceExportDTO;
import de.hsos.swe.abetterstart.devices.entity.DeviceImportDTO;
import de.hsos.swe.abetterstart.devices.entity.DevicesManagement;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class UserService implements ManageDevices {

    private final DevicesManagement devicesManagement;

    @Inject
    public UserService(DevicesManagement devicesManagement) {
        this.devicesManagement = devicesManagement;
    }

    @Override
    public List<DeviceExportDTO> list() {
        return devicesManagement.list();
    }

    @Override
    public List<DeviceExportDTO> list(String type) {
        return devicesManagement.list(type);
    }

    @Override
    public Optional<DeviceExportDTO> get(Long id) {
        return devicesManagement.get(id);
    }

    @Override
    public Optional<DeviceExportDTO> create(DeviceImportDTO deviceImportDTO) {
        return devicesManagement.create(deviceImportDTO);
    }

    @Override
    public Optional<DeviceExportDTO> update(Long id, DeviceImportDTO deviceImportDTO) {
        return devicesManagement.update(id, deviceImportDTO);
    }

    @Override
    public boolean delete(Long id) {
        return devicesManagement.delete(id);
    }
}
