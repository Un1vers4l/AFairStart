package de.hsos.swe.abetterstart.common.entity;

public abstract class ImportDTO<E> {

    public ImportDTO() {}

    public abstract E toEntity();
}
