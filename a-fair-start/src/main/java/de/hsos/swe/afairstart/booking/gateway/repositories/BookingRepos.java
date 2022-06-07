package de.hsos.swe.afairstart.booking.gateway.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import de.hsos.swe.afairstart.booking.control.BookingService;
import de.hsos.swe.afairstart.booking.entity.Booking;
import de.hsos.swe.afairstart.booking.entity.Device;
import de.hsos.swe.afairstart.booking.entity.User;

@ApplicationScoped
@Transactional
public class BookingRepos implements BookingService {

    Logger log = Logger.getLogger(BookingRepos.class);

    @Inject 
    EntityManager em;

    @Inject
    DeviceRepos deviceRepos;

    @Inject
    UserRepos userRepos;

    @Override
    public boolean deleteBooking(int bookingID) {
        try {
            Booking booking = em.find(Booking.class, bookingID);
            em.remove(booking);
            return true;
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }
    }

    @Override
    public boolean logOn(int bookingID) {
        Booking booking = getBooking(bookingID);
        if(booking!=null){
            booking.login();
            return true;
        }
        return false;
    }

    @Override
    public long logOff(int bookingID) {
        Booking booking = getBooking(bookingID);
        if(booking!=null){
            booking.logOff();
            return booking.getActualDuration();
        }
        return -10;
    }

    @Override
    public Booking createBooking(int userID, int deviceID, LocalTime bookingTime, LocalDate bookingDate, int calculatedDuration) {
       try {
           Device device = deviceRepos.getDevice(deviceID);
           Booking booking = new Booking(bookingTime, bookingDate, calculatedDuration, device);
           User user = userRepos.getUser(userID);
           em.persist(booking);
           user.addBooking(booking);
           em.persist(user);
           return booking;           
       } catch (Exception e) {
            log.error("create Booking not successful");   
            return null;
       }
    }

    @Override
    public Booking getBooking(int bookingId) {
        try {
            Booking booking = em.find(Booking.class, bookingId);
            return booking;
        } catch (Exception e) {
            //TODO: handle exception
        }
        return null;
    }

    @Override
    public List<Booking> getAllBooking() {
        return em.createNamedQuery("SELECT b FROM Booking b", Booking.class).getResultList();
    }
    
}
