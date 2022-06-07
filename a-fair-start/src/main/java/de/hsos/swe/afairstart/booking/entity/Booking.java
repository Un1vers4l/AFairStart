package de.hsos.swe.afairstart.booking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import static java.time.temporal.ChronoUnit.MINUTES;

@Entity
public class Booking {
    @Id
    @SequenceGenerator(name = "bookingSeq", sequenceName = "booking_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "bookingSeq")
    private int bookingID;
    private LocalTime bookingTime;
    private LocalTime loginTime;
    private LocalDate bookingDate;
    private int calculatedDuration;
    private long actualDuration;
    @ManyToOne
    private Device device;

    
    public Booking() {
    }
    
    public Booking(LocalTime bookingTime, LocalDate bookingDate, int calculatedDuration, Device device) {
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
        this.calculatedDuration = calculatedDuration;
        this.device = device;
    }
    public void login(){
        this.loginTime= LocalTime.now();
    }
    public void logOff(){
        actualDuration = loginTime.until(LocalTime.now(), MINUTES);
    }
    public int getBookingID() {
        return bookingID;
    }
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    public LocalTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
    public int getCalculatedDuration() {
        return calculatedDuration;
    }
    public void setCalculatedDuration(int calculatedDuration) {
        this.calculatedDuration = calculatedDuration;
    }
    public long getActualDuration() {
        return actualDuration;
    }
    public void setActualDuration(int actualDuration) {
        this.actualDuration = actualDuration;
    }
    
}
