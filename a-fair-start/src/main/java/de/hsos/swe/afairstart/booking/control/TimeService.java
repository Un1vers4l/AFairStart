package de.hsos.swe.afairstart.control;

import de.hsos.swe.afairstart.entity.Device;
import de.hsos.swe.afairstart.entity.User;

public interface TimeService {
    public int getTime (User user, Device device, int processTime);
}
