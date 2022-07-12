package de.hsos.swe.afairstart.devices.boundary.rest;

import de.hsos.swe.afairstart.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstart.devices.entity.DeviceImportDTO;
import de.hsos.swe.afairstart.devices.gateway.DevicesRepository;

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
 * Access to list and creation of Devices
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/devices")
public class DevicesResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    DevicesRepository dRepository;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "POST").build();
    }

    @GET
    @RolesAllowed({ "user", "admin" })
    public Response get(@QueryParam("type") String type) {
        if (type == null) {
            return Response.ok(dRepository.list()).build();
        } else {
            return Response.ok(dRepository.list(type)).build();
        }
    }

    // Only admins are allowed to create devices
    @POST
    @Transactional
    @RolesAllowed("admin")
    public Response post(DeviceImportDTO deviceImportDTO) {
        Optional<DeviceExportDTO> device = dRepository.create(deviceImportDTO);

        if (device.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(device.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
