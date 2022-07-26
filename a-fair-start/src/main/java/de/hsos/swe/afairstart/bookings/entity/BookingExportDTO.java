package de.hsos.swe.afairstart.bookings.entity;

import javax.enterprise.inject.Vetoed;
import java.time.LocalDateTime;

@Vetoed
public class BookingExportDTO {

    private final long id;
    private final String user;
    private final long deviceId;
    private final LocalDateTime scheduledStart;
    private final long intendedDuration;
    private final Long expectedDuration;
    private final LocalDateTime actualStart;
    private final Long actualDuration;
    private final LocalDateTime scheduledEnd;

    public LocalDateTime getScheduledEnd() {
        return scheduledEnd;
    }

    public BookingExportDTO(Booking entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.deviceId = entity.getDeviceId();
        this.scheduledStart = entity.getScheduledStart();
        this.intendedDuration = entity.getIntendedDuration();
        this.expectedDuration = entity.getExpectedDuration();
        this.actualStart = entity.getActualStart();
        this.actualDuration = entity.getActualDuration();
        this.scheduledEnd = entity.getScheduledEnd();
    }

    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getScheduledStart() {
        return scheduledStart;
    }

    public long getIntendedDuration() {
        return intendedDuration;
    }

    public Long getExpectedDuration() {
        return expectedDuration;
    }

    public LocalDateTime getActualStart() {
        return actualStart;
    }

    public Long getActualDuration() {
        return actualDuration;
    }

    @Override
    public String toString() {
        return "BookingExportDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", device=" + deviceId +
                ", scheduledStart=" + scheduledStart +
                ", intendedDuration=" + intendedDuration +
                ", expectedDuration=" + expectedDuration +
                ", actualStart=" + actualStart +
                ", actualDuration=" + actualDuration +
                '}';
    }

}
