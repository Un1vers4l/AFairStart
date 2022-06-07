package de.hsos.swe.afairstart.booking.boundary.rest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsos.swe.afairstart.booking.control.DeviceService;


@Path("/api/v1/device")
@ApplicationScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeviceResouce {
    @Inject
    DeviceService deviceService;

    @Path("/{deviceId}")
    @GET
    public Response getDevice(@PathParam("deviceId") int deviceId){
        return Response.ok(deviceService.getDevice(deviceId)).build();
    }

    @GET
    public Response getAllDevices(){
        return Response.ok(deviceService.getAllDevices()).build();
    }
}
