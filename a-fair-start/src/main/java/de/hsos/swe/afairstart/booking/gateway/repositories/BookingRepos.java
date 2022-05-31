package de.hsos.swe.afairstart.gateway.repositories;

import java.time.LocalDate;
import java.time.LocalTime;

import de.hsos.swe.afairstart.control.BookingService;
import de.hsos.swe.afairstart.entity.Booking;

public class BookingRepos implements BookingService {
    @Override
    public boolean deleteBooking(int bookingID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean logOn(int bookingID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean logOff(int bookingID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Booking createBooking(int userID, int deviceID, LocalTime bookingTime, LocalDate bookingDate, int calculatedDuration) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
