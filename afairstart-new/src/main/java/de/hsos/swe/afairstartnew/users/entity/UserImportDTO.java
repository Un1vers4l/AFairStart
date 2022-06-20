package de.hsos.swe.afairstartnew.users.entity;

import de.hsos.swe.afairstartnew.common.entity.ImportDTO;

import javax.ws.rs.FormParam;
import java.util.HashMap;
import java.util.Map;

public class UserImportDTO extends ImportDTO<User> {

    private String username;
    private String password;
    private String role = "user";

    private String name;
    private Map<String, Long> deviceExpericence = new HashMap<>();

    public String getUsername() {
        return username;
    }

    @FormParam("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @FormParam("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    @FormParam("role")
    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Long> getDeviceExpericence() {
        return deviceExpericence;
    }

    public void setDeviceExpericence(Map<String, Long> deviceExpericence) {
        this.deviceExpericence = deviceExpericence;
    }

    @Override
    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setName(name);
        user.setDeviceExpericence(deviceExpericence);
        return user;
    }
}
