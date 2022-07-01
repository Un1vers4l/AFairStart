package de.hsos.swe.afairstart.devices.entity;

public class DeviceExportDTO {

    private final long id;
    private final DeviceType type;

    public DeviceExportDTO(Device entity) {
        this.id = entity.getId();
        this.type = entity.getType();
    }

    public long getId() {
        return id;
    }

    public DeviceType getType() {
        return type;
    }

    public String toString() {
        return "DeviceExportDTO{" +
                "id=" + id +
                ", type='" + type.toString() + '\'' +
                '}';
    }
}
