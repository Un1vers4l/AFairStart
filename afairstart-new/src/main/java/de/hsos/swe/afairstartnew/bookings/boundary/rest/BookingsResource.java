package de.hsos.swe.afairstartnew.bookings.boundary.rest;

import de.hsos.swe.afairstartnew.bookings.control.ManageBookings;
import de.hsos.swe.afairstartnew.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstartnew.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstartnew.common.boundary.rest.ListResource;

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
 * Access to list and creation of Bookings
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/bookings")
@RolesAllowed({"user", "admin"})
public class BookingsResource implements ListResource<BookingImportDTO> {

    private final ManageBookings manageBookings;

    @Context
    private SecurityContext securityContext;

    @Inject
    public BookingsResource(ManageBookings manageBookings) {
        this.manageBookings = manageBookings;
    }

    @Override @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "POST").build();
    }

    @Override @GET
    public Response get() {
        // Show admins all bookings, instead of their own
        if (securityContext.isUserInRole("admin")) {
            return Response.ok(manageBookings.list("admin")).build();
        }
        // Show users their own bookings
        else {
            String username = securityContext.getUserPrincipal().getName();
            return Response.ok(manageBookings.list(username)).build();
        }
    }

    @Override @POST @Transactional
    public Response post(BookingImportDTO bookingImportDTO) {
        String username = securityContext.getUserPrincipal().getName();
        Optional<BookingExportDTO> booking = manageBookings.create(username, bookingImportDTO);

        if (booking.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(booking.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
