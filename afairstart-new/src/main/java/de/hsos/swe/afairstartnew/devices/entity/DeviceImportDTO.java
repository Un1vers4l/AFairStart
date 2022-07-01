package de.hsos.swe.afairstartnew.devices.entity;

import de.hsos.swe.afairstartnew.common.entity.ImportDTO;

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
