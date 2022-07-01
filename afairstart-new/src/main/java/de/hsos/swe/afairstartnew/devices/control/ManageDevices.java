package de.hsos.swe.afairstartnew.devices.control;

import de.hsos.swe.afairstartnew.common.control.ManageEntity;
import de.hsos.swe.afairstartnew.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstartnew.devices.entity.DeviceImportDTO;

import java.util.List;

public interface ManageDevices extends ManageEntity<Long, DeviceImportDTO, DeviceExportDTO> {

    List<DeviceExportDTO> list(String type);
}
