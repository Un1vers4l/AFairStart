package de.hsos.swe.afairstart.control;

import java.time.LocalDate;
import java.time.LocalTime;

import de.hsos.swe.afairstart.entity.Booking;

public interface BookingService {
    public Booking createBooking(int userID, int deviceID, LocalTime bookingTime, LocalDate bookingDate, int calculatedDuration);
    public boolean deleteBooking (int bookingID);
    public boolean logOn (int bookingID);
    public boolean logOff (int bookingID);
}
