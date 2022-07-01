package de.hsos.swe.afairstartnew.devices.entity;

import de.hsos.swe.afairstartnew.common.entity.ExportDTO;

public class DeviceExportDTO extends ExportDTO<Device> {

    private final long id;
    private final String type;

    public DeviceExportDTO(Device entity) {
        this.id = entity.getId();
        this.type = entity.getType();
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DeviceExportDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
