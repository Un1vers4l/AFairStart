package de.hsos.swe.afairstart.booking.control;

import de.hsos.swe.afairstart.booking.entity.Device;
import de.hsos.swe.afairstart.booking.entity.User;

public interface TimeService {
    public int getTime (User user, Device device, int processTime);
}
