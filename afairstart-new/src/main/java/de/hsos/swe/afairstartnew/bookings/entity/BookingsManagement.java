package de.hsos.swe.afairstartnew.bookings.entity;

import java.util.List;
import java.util.Optional;

public interface BookingsManagement {

    List<BookingExportDTO> list(String username);
    Optional<BookingExportDTO> get(Long id);

    Optional<BookingExportDTO> create(String username, BookingImportDTO importDTO);
    boolean delete(Long id);

    boolean checkIn(Long id);
    boolean checkOut(Long id);
}
