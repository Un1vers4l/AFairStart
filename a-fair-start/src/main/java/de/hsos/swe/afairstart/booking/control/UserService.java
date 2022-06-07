package de.hsos.swe.afairstart.booking.control;

import java.util.List;

import de.hsos.swe.afairstart.booking.entity.User;

public interface UserService {
    public User getUser (int userID);
    public List<User> getAllUser();
}
