package de.hsos.swe.afairstartnew.common.entity;

public abstract class ImportDTO<E> {

    public ImportDTO() {}

    public abstract E toEntity();
}
