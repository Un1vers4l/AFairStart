package de.hsos.swe.afairstart.booking.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="fairUser")
public class User {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userSeq")
    private int userID;
    private String userName;
    private UserRole userRole;
    private HashMap<DeviceType, Integer> deviceLevel = new HashMap<DeviceType, Integer>();
    
    

    public User() {
    }
    
    public User(String name, UserRole role) {
        this.userName = name;
        this.userRole = role;
    }
    
    @OneToMany
    private List<Booking> booking = new ArrayList<Booking>();

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getName() {
        return userName;
    }
    public void setName(String name) {
        this.userName = name;
    }
    public UserRole getRole() {
        return userRole;
    }
    public void setRole(UserRole role) {
        this.userRole = role;
    }
    public HashMap<DeviceType, Integer> getDeviceLevel() {
        return deviceLevel;
    }
    public void setDeviceLevel(HashMap<DeviceType, Integer> deviceLevel) {
        this.deviceLevel = deviceLevel;
    }
    public void addBooking(Booking booking) {
        this.booking.add(booking);
    }

    
}
