package de.hsos.swe.afairstart.booking.control;

import java.util.List;

import de.hsos.swe.afairstart.booking.entity.Device;

public interface DeviceService {
    public Device getDevice (int deviceID);
    public List<Device> getAllDevices();
}
