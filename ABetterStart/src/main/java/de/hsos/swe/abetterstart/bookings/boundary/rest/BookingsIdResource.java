package de.hsos.swe.abetterstart.bookings.boundary.rest;

import de.hsos.swe.abetterstart.bookings.control.ManageBookings;
import de.hsos.swe.abetterstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.abetterstart.bookings.entity.BookingImportDTO;
import de.hsos.swe.abetterstart.common.boundary.rest.InstanceResource;

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
@RolesAllowed({"user", "admin"})
public class BookingsIdResource implements InstanceResource<Long, BookingImportDTO> {

    private final ManageBookings manageBookings;

    @Context
    private SecurityContext securityContext;

    @Inject
    public BookingsIdResource(ManageBookings manageBookings) {
        this.manageBookings = manageBookings;
    }

    @Override @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "DELETE", "CHECKIN", "CHECKOUT").build();
    }

    @Override @GET
    public Response get(@PathParam("id") Long id) {
        Optional<BookingExportDTO> booking = manageBookings.get(id);

        if (booking.isPresent()) {
            return Response.ok(booking.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override @DELETE @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (manageBookings.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Custom HTTP Method for checking in a booking
    @CHECKIN @Transactional
    public Response signOn(@PathParam("id") long id) {
        if (manageBookings.checkIn(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // Custom HTTP Method for checking out a booking
    @CHECKOUT @Transactional
    public Response signOff(@PathParam("id") long id) {
        if (manageBookings.checkOut(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
