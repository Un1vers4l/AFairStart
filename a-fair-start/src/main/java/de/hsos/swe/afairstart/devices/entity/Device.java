package de.hsos.swe.afairstart.devices.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

@Entity
public class Device {

    @Id
    @GeneratedValue(generator = "deviceIdSequence")
    private Long id;
    private DeviceType type;

    public Long getId() {
        return id;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) && type.equals(device.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
