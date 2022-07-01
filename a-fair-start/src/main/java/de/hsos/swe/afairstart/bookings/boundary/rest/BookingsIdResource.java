package de.hsos.swe.afairstart.bookings.boundary.rest;

import de.hsos.swe.afairstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstart.bookings.gateway.BookingsRepository;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;

/**
 * Access a specific Booking Instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/bookings/{id:\\d+}")
@RolesAllowed({ "user", "admin" })
public class BookingsIdResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    BookingsRepository bRepository;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "DELETE", "CHECKIN", "CHECKOUT").build();
    }

    @GET
    public Response get(@PathParam("id") Long id) {
        Optional<BookingExportDTO> booking = bRepository.get(id);

        if (booking.isPresent()) {
            return Response.ok(booking.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (bRepository.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Custom HTTP Method for checking in a booking
    @Path("/signOn")
    @PUT
    @Transactional
    public Response signOn(@PathParam("id") long id) {
        if (bRepository.checkIn(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Custom HTTP Method for checking out a booking
    @Path("/signOff")
    @PUT
    @Transactional
    public Response signOff(@PathParam("id") long id) {
        if (bRepository.checkOut(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
