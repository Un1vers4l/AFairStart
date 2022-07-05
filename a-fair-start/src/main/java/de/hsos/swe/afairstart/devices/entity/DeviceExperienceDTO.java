package de.hsos.swe.afairstart.devices.entity;

public class DeviceExperienceDTO {
    
    private DeviceType type;
    private Long level;

    public DeviceType getType() {
        return type;
    }

    public Long getLevel() {
        return level;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
    
}
