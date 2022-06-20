package de.hsos.swe.afairstartnew.bookings.control;

import de.hsos.swe.afairstartnew.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstartnew.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstartnew.bookings.entity.BookingsManagement;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class UserService implements ManageBookings {

    private final BookingsManagement bookingsManagement;

    @Inject
    public UserService(BookingsManagement bookingsManagement) {
        this.bookingsManagement = bookingsManagement;
    }

    @Override
    public List<BookingExportDTO> list(String username) {
        return bookingsManagement.list(username);
    }

    @Override
    public Optional<BookingExportDTO> get(Long id) {
        return bookingsManagement.get(id);
    }

    @Override
    public Optional<BookingExportDTO> create(String username, BookingImportDTO importDTO) {
        return bookingsManagement.create(username, importDTO);
    }

    @Override
    public boolean delete(Long id) {
        return bookingsManagement.delete(id);
    }

    @Override
    public boolean checkIn(Long id) {
        return bookingsManagement.checkIn(id);
    }

    @Override
    public boolean checkOut(Long id) {
        return bookingsManagement.checkOut(id);
    }
}
