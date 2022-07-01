package de.hsos.swe.afairstartnew.users.entity;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity @Table(name = "users")
@Vetoed @UserDefinition
public class User {

    @Id @Username private String username;
    @Password private String password;
    @Roles private String role = "user";

    private String name;
    @ElementCollection(fetch = FetchType.EAGER) private Map<String, Long> deviceExpericence = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }

    public String getRole() {
        return role;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && role.equals(user.role) && name.equals(user.name) && deviceExpericence.equals(user.deviceExpericence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, name, deviceExpericence);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", deviceExpericence=" + deviceExpericence +
                '}';
    }
}
