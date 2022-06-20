package de.hsos.swe.afairstartnew.bookings.entity;

import de.hsos.swe.afairstartnew.common.entity.ImportDTO;

import javax.enterprise.inject.Vetoed;
import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.FormParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Vetoed
public class BookingImportDTO extends ImportDTO<Booking> {

    @JsonbTransient
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private long deviceId;
    private LocalDateTime scheduledStart;
    private long intendedDuration;

    public long getDeviceId() {
        return deviceId;
    }

    @FormParam("deviceId")
    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getScheduledStart() {
        return scheduledStart;
    }

    @FormParam("scheduledStart")
    public void setScheduledStart(String scheduledStart) {
        scheduledStart = scheduledStart.replace("T", " ").replace("Z","");
        this.scheduledStart = LocalDateTime.parse(scheduledStart, dateTimeFormatter);
    }

    public long getIntendedDuration() {
        return intendedDuration;
    }

    @FormParam("intendedDuration")
    public void setIntendedDuration(int intendedDuration) {
        this.intendedDuration = intendedDuration;
    }

    public BookingImportDTO() {
    }

    public BookingImportDTO(Booking booking) {
        this.deviceId = booking.getDeviceId();
        this.scheduledStart = booking.getScheduledStart();
        this.intendedDuration = booking.getIntendedDuration();
    }

    @Override
    public String toString() {
        return "BookingImportDTO{" +
                "deviceId=" + deviceId +
                ", scheduledStart=" + scheduledStart +
                ", intendedDuration=" + intendedDuration +
                '}';
    }

    @Override
    public Booking toEntity() {
        Booking booking = new Booking();
        booking.setDeviceId(deviceId);
        booking.setScheduledStart(scheduledStart);
        booking.setIntendedDuration(intendedDuration);
        return booking;
    }
}
