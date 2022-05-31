package de.hsos.swe.afairstart.booking.boundary.rest;

import de.hsos.swe.afairstart.booking.entity.Booking;

import java.util.Date;
import java.sql.Time;


public class BookingResource {
    //TODO: Response als Rückgabetyp
    int getTime (int userID, int deviceID, int processTime){
        return 0;
    }
    
    boolean logOn (int bookingID){
        return false;
    }
    
    boolean logOff (int bookingID){
        return false;
    }
    
    Booking createBooking (int userID, int deviceID, int calculatedDuration, Time bookingTime, Date bookingDate){
        return null;
    }

    boolean deleteBooking (int bookingID){
        return false;
    }
}
