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
 * Access a specific Device instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/devices/{id:\\d+}")
@RolesAllowed({ "user", "admin" })
public class DevicesIdResource {

    @Context
    private SecurityContext securityContext;

    @Inject
    DevicesRepository dRepository;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "PUT", "DELETE").build();
    }

    @GET
    public Response get(@PathParam("id") Long id) {
        Optional<DeviceExportDTO> device = dRepository.get(id);

        if (device.isPresent()) {
            return Response.ok(device.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Transactional
    public Response put(@PathParam("id") Long id, DeviceImportDTO deviceImportDTO) {
        Optional<DeviceExportDTO> device = dRepository.update(id, deviceImportDTO);

        if (device.isPresent()) {
            return Response.ok(device.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (dRepository.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
