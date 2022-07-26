package de.hsos.swe.afairstart.bookings.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Vetoed
public class Booking {

    @Id
    @GeneratedValue(generator = "booking_seq")
    private long id;
    @Version
    private int version;

    @Column(name = "username")
    private String user;
    private long deviceId;
    private LocalDateTime scheduledStart;
    private long intendedDuration;
    private Long expectedDuration;
    private LocalDateTime actualStart;
    private Long actualDuration;
    private LocalDateTime scheduledEnd;

    public LocalDateTime getScheduledEnd() {
        return scheduledEnd;
    }

    public void setScheduledEnd(LocalDateTime scheduledEnd) {
        this.scheduledEnd = scheduledEnd;
    }

    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long device) {
        this.deviceId = device;
    }

    public LocalDateTime getScheduledStart() {
        return scheduledStart;
    }

    public void setScheduledStart(LocalDateTime scheduledStart) {
        this.scheduledStart = scheduledStart;
    }

    public long getIntendedDuration() {
        return intendedDuration;
    }

    public void setIntendedDuration(long intendedDuration) {
        this.intendedDuration = intendedDuration;
    }

    public Long getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(Long expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public LocalDateTime getActualStart() {
        return actualStart;
    }

    public void setActualStart(LocalDateTime actualStart) {
        this.actualStart = actualStart;
    }

    public Long getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(Long actualDuration) {
        this.actualDuration = actualDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Booking booking = (Booking) o;
        return deviceId == booking.deviceId && intendedDuration == booking.intendedDuration && user.equals(booking.user)
                && scheduledStart.equals(booking.scheduledStart)
                && Objects.equals(expectedDuration, booking.expectedDuration)
                && Objects.equals(actualStart, booking.actualStart)
                && Objects.equals(actualDuration, booking.actualDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, deviceId, scheduledStart, intendedDuration, expectedDuration, actualStart,
                actualDuration);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", version=" + version +
                ", user='" + user + '\'' +
                ", deviceId=" + deviceId +
                ", scheduledStart=" + scheduledStart +
                ", intendedDuration=" + intendedDuration +
                ", expectedDuration=" + expectedDuration +
                ", actualStart=" + actualStart +
                ", actualDuration=" + actualDuration +
                '}';
    }
}
