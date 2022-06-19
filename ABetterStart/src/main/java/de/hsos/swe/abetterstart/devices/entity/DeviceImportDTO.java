package de.hsos.swe.abetterstart.devices.entity;

import de.hsos.swe.abetterstart.common.entity.ImportDTO;

public class DeviceImportDTO extends ImportDTO<Device> {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Device toEntity() {
        Device device = new Device();
        device.setType(type);
        return device;
    }
}
