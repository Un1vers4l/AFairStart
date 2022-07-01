package de.hsos.swe.afairstart.devices.entity;

public class DeviceImportDTO {

    private DeviceType type;

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public Device toEntity() {
        Device device = new Device();
        device.setType(type);
        return device;
    }
}
