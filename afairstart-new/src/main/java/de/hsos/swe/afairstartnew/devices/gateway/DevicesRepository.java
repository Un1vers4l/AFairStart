package de.hsos.swe.afairstartnew.devices.gateway;

import de.hsos.swe.afairstartnew.devices.entity.Device;
import de.hsos.swe.afairstartnew.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstartnew.devices.entity.DeviceImportDTO;
import de.hsos.swe.afairstartnew.devices.entity.DevicesManagement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DevicesRepository implements DevicesManagement {

    private final EntityManager entityManager;

    @Inject
    public DevicesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DeviceExportDTO> list() {
        return entityManager.createQuery("SELECT d FROM Device d", Device.class)
                .getResultStream().map(DeviceExportDTO::new).toList();
    }

    public List<DeviceExportDTO> list(String type) {
        return entityManager.createQuery("SELECT d FROM Device d WHERE d.type = :type", Device.class)
                .setParameter("type", type)
                .getResultStream().map(DeviceExportDTO::new).toList();
    }

    private Optional<Device> getDevice(long id) {
        return Optional.ofNullable(entityManager.find(Device.class, id));
    }

    @Override
    public Optional<DeviceExportDTO> get(Long id) {
        return getDevice(id).map(DeviceExportDTO::new);
    }

    @Override
    public Optional<DeviceExportDTO> create(DeviceImportDTO deviceImportDTO) {
        Device device = deviceImportDTO.toEntity();
        entityManager.persist(device);
        return Optional.of(device).map(DeviceExportDTO::new);
    }

    @Override
    public Optional<DeviceExportDTO> update(Long id, DeviceImportDTO deviceImportDTO) {
        Device device = getDevice(id).orElse(null);

        if (device == null) {
            return Optional.empty();
        }

        device.setType(deviceImportDTO.getType());
        return Optional.ofNullable(entityManager.merge(device)).map(DeviceExportDTO::new);

    }

    @Override
    public boolean delete(Long id) {
        Optional<Device> device = getDevice(id);

        if (device.isPresent()) {
            entityManager.remove(device.get());
            return true;
        } else {
            return false;
        }
    }
}
