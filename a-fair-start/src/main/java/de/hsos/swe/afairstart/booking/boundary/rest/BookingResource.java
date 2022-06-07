package de.hsos.swe.afairstart.booking.boundary.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import de.hsos.swe.afairstart.booking.boundary.dto.PostBooking;
import de.hsos.swe.afairstart.booking.control.BookingService;


@Path("/api/v1/booking")
@ApplicationScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BookingResource {
    Logger log = Logger.getLogger(this.getClass());

    @Inject
    BookingService service;

    @GET
    @Path("/{bookingId}")
    public Response getBooking(@PathParam("bookingId") int bookingId){
        System.out.println(LocalDateTime.now());
        try {
            return Response.ok(service.getBooking(bookingId)).build();
        } catch (Exception e) {
            return null;        
        }
    }

    @DELETE
    @Path("/{bookingId}")
    public Response deleteBooking(@PathParam("bookingId") int bookingId){
        try {
            return Response.ok(service.deleteBooking(bookingId)).build();
        } catch (Exception e) {
            return null;
        }
    }
    
    @POST
    public Response createBooking(PostBooking bookingDTO){
        try {
            return Response.ok(service.createBooking(bookingDTO.userId, bookingDTO.deviceId, bookingDTO.bookingTime.toLocalTime(), bookingDTO.bookingTime.toLocalDate(), bookingDTO.calculatedDuration)).build();
        } catch (Exception e) {
            return null;
            //TODO: handle exception
        }
    }

    @PUT
    @Path("/{bookingId}/login")
    public Response login(@PathParam("bookingId") int bookingId){
        return Response.ok(service.logOn(bookingId)).build();
    }

    @PUT
    @Path("/{bookingId}/logoff")
    public Response logOff(@PathParam("bookingId") int bookingId){
        return Response.ok(service.logOff(bookingId)).build();
    }
}
