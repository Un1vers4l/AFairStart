package de.hsos.swe.abetterstart.users.entity;

import java.util.List;
import java.util.Optional;

public interface UsersManagement {

    List<UserExportDTO> list();
    Optional<UserExportDTO> get(String username);

    Optional<UserExportDTO> create(UserImportDTO importDTO);
    Optional<UserExportDTO> update(UserImportDTO importDTO);
    boolean delete(String username);
}
