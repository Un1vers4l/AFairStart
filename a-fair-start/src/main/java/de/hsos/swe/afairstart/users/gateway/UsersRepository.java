package de.hsos.swe.afairstart.users.gateway;

import de.hsos.swe.afairstart.users.control.UserService;
import de.hsos.swe.afairstart.users.entity.User;
import de.hsos.swe.afairstart.users.entity.UserExportDTO;
import de.hsos.swe.afairstart.users.entity.UserImportDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UsersRepository implements UserService {

    private final EntityManager entityManager;

    @Inject
    public UsersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserExportDTO> list() {
        Stream<UserExportDTO> map = entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultStream().map(UserExportDTO::new);
        return map.collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUser(String username) {
        return Optional.ofNullable(entityManager.find(User.class, username));
    }

    @Override
    public Optional<UserExportDTO> get(String username) {
        return getUser(username).map(UserExportDTO::new);
    }

    @Override
    public Optional<UserExportDTO> create(UserImportDTO importDTO) {
        User user = importDTO.toEntity();
        entityManager.persist(user);
        return Optional.of(user).map(UserExportDTO::new);
    }

    @Override
    public Optional<UserExportDTO> update(UserImportDTO importDTO) {
        User user = getUser(importDTO.getUsername()).orElse(null);
        if (user == null) {
            return Optional.empty();
        }

        user.setPassword(importDTO.getPassword());
        user.setRole(importDTO.getRole());
        user.setName(importDTO.getName());

        return Optional.of(entityManager.merge(user)).map(UserExportDTO::new);
    }

    @Override
    public boolean delete(String username) {
        Optional<User> user = getUser(username);
        if (user.isPresent()) {
            entityManager.remove(user.get());
            return true;
        } else {
            return false;
        }
    }

}
