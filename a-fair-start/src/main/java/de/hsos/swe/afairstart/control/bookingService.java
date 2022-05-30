package de.hsos.swe.afairstart.control;

import de.hsos.swe.afairstart.entity.Booking;

public interface bookingService {
    Booking createBooking(int userID, int deviceID, Time bookingTime, Date bookingDate, int calculatedDuration);
    boolean deleteBooking (int booingID);
    boolean logOn (int booingID);
    boolean logOff (int booingID);
}
