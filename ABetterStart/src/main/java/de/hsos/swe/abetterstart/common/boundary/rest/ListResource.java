package de.hsos.swe.abetterstart.common.boundary.rest;

import de.hsos.swe.abetterstart.common.entity.ImportDTO;

import javax.ws.rs.core.Response;

/**
 * Each REST resource that accesses a list of entities should extend this class.
 * In this project some HTTP methods are only used here and ignored in the implementations,
 * but normally all HTTP methods should be used even if they only return the default implementation.
 * NOTE: This is completely optional and a preference from Prof Roosmann.
 */
public interface ListResource<E extends ImportDTO<?>> {

    Response options();

    default Response get() {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    default Response post(E e) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    default Response put(E e) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    default Response patch(E e) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
    default Response delete() {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
    }
}
