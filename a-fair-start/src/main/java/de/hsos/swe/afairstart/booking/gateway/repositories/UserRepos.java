package de.hsos.swe.afairstart.booking.gateway.repositories;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import de.hsos.swe.afairstart.booking.control.UserService;
import de.hsos.swe.afairstart.booking.entity.User;

@Transactional
@ApplicationScoped
public class UserRepos implements UserService{

    @Inject
    EntityManager em;
    
    @Override
    public User getUser(int userID) {
        try {
            return em.find(User.class,userID);
        } catch (Exception e) {
            return null;
            //TODO: handle exception
        }
    }

    @Override
    public List<User> getAllUser() {
        return em.createQuery("SELECT u From User u", User.class).getResultList();
        }
    
}
