package de.hsos.swe.afairstart.bookings.gateway;

import de.hsos.swe.afairstart.bookings.control.BookingService;
import de.hsos.swe.afairstart.bookings.entity.Booking;
import de.hsos.swe.afairstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstart.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstart.bookings.entity.NeuralDAO;
import de.hsos.swe.afairstart.devices.entity.Device;
import de.hsos.swe.afairstart.devices.entity.DeviceType;
import de.hsos.swe.afairstart.users.entity.User;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class BookingsRepository implements BookingService {

    private final EntityManager entityManager;
    private final NeuralGuesstimatorClient neuralGuesstimatorClient;

    /*@Inject // NeuralGuesstimatorClient Mock-Implementation
    public BookingsRepository(EntityManager entityManager) {
        this(entityManager, BookingImportDTO::getIntendedDuration);
    }*/

    public BookingsRepository(EntityManager entityManager,
        NeuralGuesstimatorClient neuralGuesstimatorClient) {
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
        return results.map(BookingExportDTO::new).collect(Collectors.toList());
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

        booking.setExpectedDuration(neuralGuesstimatorClient.getExpectedDuration(this.createNeuralDAO(booking.getId())));
        long duration = Math.max(booking.getIntendedDuration(), booking.getExpectedDuration());
        LocalDateTime endTime = booking.getScheduledStart().plusMinutes(duration);
        Optional<Booking> existingBooking = entityManager
                .createQuery(
                        "SELECT b FROM Booking b WHERE b.scheduledStart >= :startTime AND b.scheduledStart <= :endTime",
                        Booking.class)
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
            User user = entityManager.find(User.class, booking.getUser());
            if (user != null) {
                Device device = entityManager.find(Device.class, booking.getDeviceId());
                if (device != null) {
                    user.pushBooking(booking.getActualDuration(), device.getType());
                }
            }
            entityManager.merge(booking);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NeuralDAO createNeuralDAO(Long id){
        Booking booking = getBooking(id).orElse(null);
        Device device = entityManager.find(Device.class, booking.getDeviceId());
        User user = entityManager.find(User.class, booking.getUser());
        ArrayDeque<Long> bookings = user.getRecentBookingsDuration().get(device.getType());
        Long level = user.getDeviceExpericence().get(device.getType());
        NeuralDAO neuralDAO = new NeuralDAO(device.getType(), level, bookings, booking.getIntendedDuration());
        return neuralDAO;
    }
}
