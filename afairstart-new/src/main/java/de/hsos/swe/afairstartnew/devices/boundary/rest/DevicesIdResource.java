package de.hsos.swe.afairstartnew.devices.boundary.rest;

import de.hsos.swe.afairstartnew.common.boundary.rest.InstanceResource;
import de.hsos.swe.afairstartnew.devices.control.ManageDevices;
import de.hsos.swe.afairstartnew.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstartnew.devices.entity.DeviceImportDTO;

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
@RolesAllowed({"user", "admin"})
public class DevicesIdResource implements InstanceResource<Long, DeviceImportDTO> {

    private final ManageDevices manageDevices;

    @Context
    private SecurityContext securityContext;

    @Inject
    public DevicesIdResource(ManageDevices manageDevices) {
        this.manageDevices = manageDevices;
    }

    @Override @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "PUT", "DELETE").build();
    }

    @Override @GET
    public Response get(@PathParam("id") Long id) {
        Optional<DeviceExportDTO> device = manageDevices.get(id);

        if (device.isPresent()) {
            return Response.ok(device.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override @PUT @Transactional
    public Response put(@PathParam("id") Long id, DeviceImportDTO deviceImportDTO) {
        Optional<DeviceExportDTO> device = manageDevices.update(id, deviceImportDTO);

        if (device.isPresent()) {
            return Response.ok(device.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override @DELETE @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (manageDevices.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
