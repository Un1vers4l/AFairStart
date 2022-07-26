package de.hsos.swe.afairstart.bookings.boundary.rest;

import de.hsos.swe.afairstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstart.bookings.entity.BookingImportDTO;
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
 * Access to list and creation of Bookings
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/bookings")
@RolesAllowed({ "user", "admin" })
public class BookingsResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    BookingsRepository bRepository;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "POST").build();
    }

    @GET
    public Response get(@QueryParam("type") String type, @QueryParam("date") boolean date) {
        if (type == null && date == false) {
            // Show admins all bookings, instead of their own
            if (securityContext.isUserInRole("admin")) {
                return Response.ok(bRepository.list("admin")).build();
            }
            // Show users their own bookings
            else {
                String username = securityContext.getUserPrincipal().getName();
                return Response.ok(bRepository.list(username)).build();
            }
        } else if (type != null && date == true) {

            return Response.ok(bRepository.list(type, date)).build();
        } else if (type == null && date == true) {
            // Show current Bookings
            return Response.ok(bRepository.list(type, date)).build();
        }
        return null;
    }

    @POST
    @Transactional
    public Response post(BookingImportDTO bookingImportDTO) {
        String username = securityContext.getUserPrincipal().getName();
        Optional<BookingExportDTO> booking = bRepository.create(username, bookingImportDTO);

        if (booking.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(booking.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
