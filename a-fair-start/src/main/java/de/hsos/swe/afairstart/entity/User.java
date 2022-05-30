package de.hsos.swe.afairstart.entity;

import java.util.HashSet;

public class User {
    private int userID;
    private String name;
    private UserRole role;
    private HashSet<DeviceTyp, int> deviceLevel;
}
