package de.hsos.swe.afairstart.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Device {
    @Id
    @SequenceGenerator(name = "DeviceSeq", sequenceName = "device_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "deviceSeq")
    private int deviceID;
    private DeviceType deviceType;

    

    public Device() {
    }
    
    public int getDeviceID() {
        return deviceID;
    }
    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }
    public DeviceType getType() {
        return deviceType;
    }
    public void setType(DeviceType type) {
        deviceType = type;
    }
    
}
