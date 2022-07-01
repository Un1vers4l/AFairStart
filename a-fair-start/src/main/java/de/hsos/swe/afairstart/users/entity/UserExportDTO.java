package de.hsos.swe.afairstart.users.entity;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import de.hsos.swe.afairstart.devices.entity.DeviceType;

public class UserExportDTO {

    private final String username;
    private final String role;

    private final String name;
    private Map<DeviceType, Long> deviceExpericence = new HashMap<>();
    private Map<DeviceType, ArrayDeque<Long>> recentBookingsDuration = new HashMap<>();

    public UserExportDTO(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.name = user.getName();
        this.deviceExpericence = user.getDeviceExpericence();
        this.recentBookingsDuration = user.getRecentBookingsDuration();
    }

    public Map<DeviceType, ArrayDeque<Long>> getRecentBookingsDuration() {
        return recentBookingsDuration;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public Map<DeviceType, Long> getDeviceExpericence() {
        return deviceExpericence;
    }

    @Override
    public String toString() {
        return "UserExportDTO{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", deviceExpericence=" + deviceExpericence +
                ", recentBookings=" + recentBookingsDuration.toString() +
                '}';
    }
}
