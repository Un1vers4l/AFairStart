package de.hsos.swe.afairstart.booking.entity;

import java.util.HashMap;

public class User {
    private int userID;
    private String name;
    private UserRole role;
    private HashMap<DeviceType, Integer> deviceLevel = new HashMap<DeviceType, Integer>();
}
