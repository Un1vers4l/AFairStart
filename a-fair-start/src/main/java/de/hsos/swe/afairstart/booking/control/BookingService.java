package de.hsos.swe.afairstart.booking.control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import de.hsos.swe.afairstart.booking.entity.Booking;

public interface BookingService {
    public Booking getBooking(int bookingId);
    public List<Booking> getAllBooking();
    public Booking createBooking(int userID, int deviceID, LocalTime bookingTime, LocalDate bookingDate, int calculatedDuration);
    public boolean deleteBooking (int bookingID);
    public boolean logOn (int bookingID);
    public long logOff (int bookingID);
}
