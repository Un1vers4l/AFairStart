package de.hsos.swe.abetterstart.bookings.boundary.web;

import de.hsos.swe.abetterstart.bookings.control.ManageBookings;
import de.hsos.swe.abetterstart.bookings.entity.BookingExportDTO;
import de.hsos.swe.abetterstart.bookings.entity.BookingImportDTO;
import de.hsos.swe.abetterstart.common.boundary.web.ErrorPage;
import de.hsos.swe.abetterstart.common.boundary.web.InstancePage;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

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
 * Access a specific Booking instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
@Path("/bookings/{id:\\d+}")
@RolesAllowed({"user", "admin"})
public class BookingsIdPage implements InstancePage<Long, BookingImportDTO> {

    private final ManageBookings manageBookings;
    private final Template detailsTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public BookingsIdPage(ManageBookings manageBookings, @Location("bookings/details.qute.html") Template detailsTemplate) {
        this.manageBookings = manageBookings;
        this.detailsTemplate = detailsTemplate;
    }

    @Override @GET
    public TemplateInstance get(Long id) {
        Optional<BookingExportDTO> booking = manageBookings.get(id);

        if (booking.isPresent()) {
            return detailsTemplate.data("booking", booking.get());
        } else {
            return ErrorPage.error(Response.Status.NOT_FOUND.getStatusCode(), "Booking not found");
        }
    }

    @Path("/delete") @POST @Transactional
    public Response delete(Long id) {
        if (manageBookings.delete(id)) {
            return Response.ok().header("Refresh", "0; url=/bookings").build();
        } else {
            return Response.ok(ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "Booking could not be created").render()).build();
        }
    }

    @Path("/checkIn") @POST @Transactional
    public TemplateInstance checkIn(Long id) {
        if (manageBookings.checkIn(id)) {
            return get(id);
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "Booking could not be created");
        }
    }

    @Path("/checkOut") @POST @Transactional
    public TemplateInstance checkOut(Long id) {
        if (manageBookings.checkOut(id)) {
            return get(id);
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "Booking could not be created");
        }
    }
}
