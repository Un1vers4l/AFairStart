package de.hsos.swe.afairstart.booking.boundary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

public class PostBooking {
    public int userId;
    public int deviceId;

    @JsonbProperty("bookingTime") public LocalDateTime bookingTime;
    public int calculatedDuration;
}
