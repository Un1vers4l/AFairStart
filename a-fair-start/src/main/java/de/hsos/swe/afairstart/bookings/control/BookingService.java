package de.hsos.swe.afairstart.bookings.control;

import java.util.List;
import java.util.Optional;

import de.hsos.swe.afairstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstart.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstart.bookings.entity.NeuralDAO;

public interface BookingService {

    List<BookingExportDTO> list(String username);

    Optional<BookingExportDTO> get(Long id);

    Optional<BookingExportDTO> create(String username, BookingImportDTO importDTO);

    boolean delete(Long id);

    boolean checkIn(Long id);

    boolean checkOut(Long id);

    NeuralDAO createNeuralDAO(Long id);
}
