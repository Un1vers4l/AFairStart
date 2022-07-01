package de.hsos.swe.afairstart.users.control;

import de.hsos.swe.afairstart.users.entity.User;
import de.hsos.swe.afairstart.users.entity.UserExportDTO;
import de.hsos.swe.afairstart.users.entity.UserImportDTO;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Optional;

@RequestScoped
public interface UserService {

    public List<UserExportDTO> list();

    public Optional<User> getUser(String username);

    public Optional<UserExportDTO> get(String username);

    public Optional<UserExportDTO> create(UserImportDTO importDTO);

    public Optional<UserExportDTO> update(UserImportDTO importDTO);

    public boolean delete(String username);
}
