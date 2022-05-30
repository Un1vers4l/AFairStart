package de.hsos.swe.afairstart.boundary.rest;

import de.hsos.swe.afairstart.entity.Booking;

public class bookingRessource {
    int getTime (int userID, int deviceID, int processTime){
        return 0;
    }
    
    boolean logOn (int booingID){
        return false;
    }
    
    boolean logOff (int booingID){
        return false;
    }
    
    Booking createBooking (int userID, int deviceID, int calculatedDuration, Time bookingTime, Date bookingDate){
        return null;
    }

    boolean deleteBooking (int bookingID){
        return false;
    }
}
