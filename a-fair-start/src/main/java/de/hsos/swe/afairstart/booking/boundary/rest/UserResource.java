package de.hsos.swe.afairstart.booking.boundary.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import de.hsos.swe.afairstart.booking.boundary.dto.PostBooking;
import de.hsos.swe.afairstart.booking.control.BookingService;
import de.hsos.swe.afairstart.booking.control.UserService;


@Path("/api/v1/user")
@ApplicationScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public Response getAllUser(){
        return Response.ok(userService.getAllUser()).build();
    }

}
