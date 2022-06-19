package de.hsos.swe.abetterstart.bookings.gateway;

import de.hsos.swe.abetterstart.bookings.entity.Booking;
import de.hsos.swe.abetterstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.abetterstart.bookings.entity.BookingImportDTO;
import de.hsos.swe.abetterstart.bookings.entity.BookingsManagement;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class BookingsRepository implements BookingsManagement {

    private final EntityManager entityManager;
    private final NeuralGuesstimatorClient neuralGuesstimatorClient;

    @Inject // NeuralGuesstimatorClient Mock-Implementation
    public BookingsRepository(EntityManager entityManager) {
        this(entityManager, BookingImportDTO::getIntendedDuration);
    }

    public BookingsRepository(EntityManager entityManager, @RestClient NeuralGuesstimatorClient neuralGuesstimatorClient) {
        this.entityManager = entityManager;
        this.neuralGuesstimatorClient = neuralGuesstimatorClient;
    }

    @Override
    public List<BookingExportDTO> list(String username) {
        Stream<Booking> results;
        if (username.equals("admin")) {
            results = entityManager.createQuery("SELECT b FROM Booking b", Booking.class)
                    .getResultStream();
        } else {
            results = entityManager.createQuery("SELECT b FROM Booking b WHERE b.user = :username", Booking.class)
                    .setParameter("username", username).getResultStream();
        }
        return results.map(BookingExportDTO::new).toList();
    }

    private Optional<Booking> getBooking(Long id) {
        return entityManager.createQuery("SELECT b FROM Booking b WHERE b.id = :id", Booking.class)
                .setParameter("id", id).getResultStream().findFirst();
    }

    @Override
    public Optional<BookingExportDTO> get(Long id) {
        return getBooking(id).map(BookingExportDTO::new);
    }

    @Override
    public Optional<BookingExportDTO> create(String username, BookingImportDTO importDTO) {
        Booking booking = importDTO.toEntity();
        booking.setUser(username);

        booking.setExpectedDuration(neuralGuesstimatorClient.getExpectedDuration(new BookingImportDTO(booking)));
        long duration = Math.max(booking.getIntendedDuration(), booking.getExpectedDuration());
        LocalDateTime endTime = booking.getScheduledStart().plusMinutes(duration);
        Optional<Booking> existingBooking = entityManager.createQuery("SELECT b FROM Booking b WHERE b.scheduledStart >= :startTime AND b.scheduledStart <= :endTime", Booking.class)
                .setParameter("startTime", booking.getScheduledStart())
                .setParameter("endTime", endTime)
                .getResultStream().findFirst();

        if (existingBooking.isPresent()) {
            return Optional.empty();
        }

        entityManager.persist(booking);
        return Optional.of(booking).map(BookingExportDTO::new);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Booking> booking = getBooking(id);
        if (booking.isPresent()) {
            entityManager.remove(booking.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIn(Long id) {
        Booking booking = getBooking(id).orElse(null);
        if (booking != null && booking.getActualStart() == null) {
            booking.setActualStart(LocalDateTime.now());
            entityManager.merge(booking);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkOut(Long id) {
        Booking booking = getBooking(id).orElse(null);
        if (booking != null && booking.getActualStart() != null) {
            long duration = booking.getActualStart().until(LocalDateTime.now(), java.time.temporal.ChronoUnit.MINUTES);
            booking.setActualDuration(duration);
            entityManager.merge(booking);
            return true;
        } else {
            return false;
        }
    }
}
