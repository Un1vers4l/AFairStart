package de.hsos.swe.afairstart.devices.control;

import java.util.List;
import java.util.Optional;

import de.hsos.swe.afairstart.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstart.devices.entity.DeviceImportDTO;

public interface DevicesService {

    List<DeviceExportDTO> list();

    List<DeviceExportDTO> list(String type);

    Optional<DeviceExportDTO> get(Long id);

    Optional<DeviceExportDTO> create(DeviceImportDTO deviceImportDTO);

    Optional<DeviceExportDTO> update(Long aLong, DeviceImportDTO deviceImportDTO);

    boolean delete(Long aLong);
}
