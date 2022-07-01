package de.hsos.swe.afairstartnew.users.control;

import de.hsos.swe.afairstartnew.users.entity.UserExportDTO;
import de.hsos.swe.afairstartnew.users.entity.UserImportDTO;
import de.hsos.swe.afairstartnew.users.entity.UsersManagement;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class UserService implements ManageUsers {

    private final UsersManagement usersManagement;

    @Inject
    public UserService(UsersManagement usersManagement) {
        this.usersManagement = usersManagement;
    }

    @Override
    public List<UserExportDTO> list() {
        return usersManagement.list();
    }

    @Override
    public Optional<UserExportDTO> get(String username) {
        return usersManagement.get(username);
    }

    @Override
    public Optional<UserExportDTO> create(UserImportDTO importDTO) {
        return usersManagement.create(importDTO);
    }

    @Override
    public Optional<UserExportDTO> update(UserImportDTO importDTO) {
        return usersManagement.update(importDTO);
    }

    @Override
    public boolean delete(String username) {
        return usersManagement.delete(username);
    }
}
