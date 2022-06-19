package de.hsos.swe.abetterstart.users.entity;

import de.hsos.swe.abetterstart.common.entity.ExportDTO;

import java.util.HashMap;
import java.util.Map;

public class UserExportDTO extends ExportDTO<User> {

    private final String username;
    private final String role;

    private final String name;
    private Map<String, Long> deviceExpericence = new HashMap<>();

    public UserExportDTO(User user) {
        this.username = user.getUsername();
        this.role = user.getRole();
        this.name = user.getName();
        this.deviceExpericence = user.getDeviceExpericence();
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

    public Map<String, Long> getDeviceExpericence() {
        return deviceExpericence;
    }

    @Override
    public String toString() {
        return "UserExportDTO{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", deviceExpericence=" + deviceExpericence +
                '}';
    }
}
