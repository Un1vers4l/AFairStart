package de.hsos.swe.abetterstart.devices.control;

import de.hsos.swe.abetterstart.common.control.ManageEntity;
import de.hsos.swe.abetterstart.devices.entity.DeviceExportDTO;
import de.hsos.swe.abetterstart.devices.entity.DeviceImportDTO;

import java.util.List;

public interface ManageDevices extends ManageEntity<Long, DeviceImportDTO, DeviceExportDTO> {

    List<DeviceExportDTO> list(String type);
}
