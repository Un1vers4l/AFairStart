package de.hsos.swe.afairstartnew.devices.boundary.web;

import de.hsos.swe.afairstartnew.common.boundary.web.ErrorPage;
import de.hsos.swe.afairstartnew.common.boundary.web.InstancePage;
import de.hsos.swe.afairstartnew.devices.control.ManageDevices;
import de.hsos.swe.afairstartnew.devices.entity.DeviceExportDTO;
import de.hsos.swe.afairstartnew.devices.entity.DeviceImportDTO;
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
 * Access a specific Device instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/devices/{id:\\d+}")
@RolesAllowed({"user", "admin"})
public class DevicesIdPage implements InstancePage<Long, DeviceImportDTO> {

    private final ManageDevices manageDevices;
    private final Template detailsTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public DevicesIdPage(ManageDevices manageDevices, @Location("devices/details.qute.html") Template detailsTemplate) {
        this.manageDevices = manageDevices;
        this.detailsTemplate = detailsTemplate;
    }

    @Override @GET
    public TemplateInstance get(@PathParam("id") Long id) {
        Optional<DeviceExportDTO> device = manageDevices.get(id);

        if (device.isPresent()) {
            return detailsTemplate.data("device", device.get());
        } else {
            return ErrorPage.error(Response.Status.NOT_FOUND.getStatusCode(), "Device not found");
        }
    }

    @Override @POST @Transactional
    public TemplateInstance post(@PathParam("id") Long id, @Form DeviceImportDTO deviceImportDTO) {
        Optional<DeviceExportDTO> device = manageDevices.update(id, deviceImportDTO);

        if (device.isPresent()) {
            return get(id);
        } else {
            return ErrorPage.error(Response.Status.NOT_FOUND.getStatusCode(), "Device not found");
        }
    }

    @Path("/delete") @POST @Transactional
    public Response delete(@PathParam("id") Long id) {
        if (manageDevices.delete(id)) {
            return Response.ok().header("Location", "/devices").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
