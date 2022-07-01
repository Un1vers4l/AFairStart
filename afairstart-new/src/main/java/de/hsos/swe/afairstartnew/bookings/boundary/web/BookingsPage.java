package de.hsos.swe.afairstartnew.bookings.boundary.web;

import de.hsos.swe.afairstartnew.bookings.control.ManageBookings;
import de.hsos.swe.afairstartnew.bookings.entity.BookingExportDTO;
import de.hsos.swe.afairstartnew.bookings.entity.BookingImportDTO;
import de.hsos.swe.afairstartnew.common.boundary.web.ErrorPage;
import de.hsos.swe.afairstartnew.common.boundary.web.ListPage;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.Form;

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
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
@Path("/bookings")
@RolesAllowed({"user", "admin"})
public class BookingsPage implements ListPage<BookingImportDTO> {

    private final ManageBookings manageBookings;
    private final Template listTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public BookingsPage(ManageBookings manageBookings, @Location("bookings/list.qute.html") Template listTemplate) {
        this.manageBookings = manageBookings;
        this.listTemplate = listTemplate;
    }

    @Override @GET
    public TemplateInstance get() {
        // Show admins all bookings, instead of their own
        if (securityContext.isUserInRole("admin")) {
            return listTemplate.data("bookings", manageBookings.list("admin"));
        }
        // Show users their own bookings
        else {
            String username = securityContext.getUserPrincipal().getName();
            return listTemplate.data("bookings", manageBookings.list(username));
        }
    }

    @Override @POST @Transactional
    public TemplateInstance post(@Form BookingImportDTO bookingImportDTO) {
        String username = securityContext.getUserPrincipal().getName();
        Optional<BookingExportDTO> bookingExportDTO = manageBookings.create(username, bookingImportDTO);

        if (bookingExportDTO.isPresent()) {
            return get();
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "Booking could not be created");
        }
    }
}
