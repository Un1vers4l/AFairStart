package de.hsos.swe.afairstart.entity;

import java.util.Date;
import java.sql.Time;

public class Booking {
    private int bookingID;
    private Time bookingTime;
    private Date bookingDate;
    private int calculatedDuration;
    private int actualDuration;
}
