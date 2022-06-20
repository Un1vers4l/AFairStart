package de.hsos.swe.afairstartnew.bookings.control;

import de.hsos.swe.afairstartnew.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstartnew.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstartnew.common.control.ManageEntity;

import java.util.List;
import java.util.Optional;

public interface ManageBookings extends ManageEntity<Long, BookingImportDTO, BookingExportDTO> {

    List<BookingExportDTO> list(String username);

    Optional<BookingExportDTO> create(String username, BookingImportDTO importDTO);

    boolean checkIn(Long id);
    boolean checkOut(Long id);
}
