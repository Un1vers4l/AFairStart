package de.hsos.swe.afairstartnew.common.control;

import de.hsos.swe.afairstartnew.common.entity.ExportDTO;
import de.hsos.swe.afairstartnew.common.entity.ImportDTO;

import java.util.List;
import java.util.Optional;

/**
 * A base usecase interface for managing entities.
 * It's meant enforce the usage of DTOs for all operations.
 */
public interface ManageEntity<Id, IDto extends ImportDTO<?>, EDto extends ExportDTO<?>> {

    default List<EDto> list() {
        throw new UnsupportedOperationException();
    }

    default Optional<EDto> get(Id id) {
        throw new UnsupportedOperationException();
    }

    default Optional<EDto> create(IDto iDto) {
        throw new UnsupportedOperationException();
    }

    default Optional<EDto> update(IDto iDto) {
        throw new UnsupportedOperationException();
    }
    default Optional<EDto> update(Id id, IDto iDto) {
        throw new UnsupportedOperationException();
    }

    default boolean patch(IDto iDto) {
        throw new UnsupportedOperationException();
    }
    default boolean patch(Id id, IDto iDto) {
        throw new UnsupportedOperationException();
    }

    default boolean delete(Id id) {
        throw new UnsupportedOperationException();
    }
}
