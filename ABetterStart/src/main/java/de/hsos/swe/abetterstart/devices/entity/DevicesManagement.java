package de.hsos.swe.abetterstart.devices.entity;

import java.util.List;
import java.util.Optional;

public interface DevicesManagement {

    List<DeviceExportDTO> list();
    List<DeviceExportDTO> list(String type);
    Optional<DeviceExportDTO> get(Long id);

    Optional<DeviceExportDTO> create(DeviceImportDTO deviceImportDTO);
    Optional<DeviceExportDTO> update(Long aLong, DeviceImportDTO deviceImportDTO);
    boolean delete(Long aLong);
}
