package de.hsos.swe.afairstartnew.devices.boundary.web;

import de.hsos.swe.afairstartnew.common.boundary.web.ErrorPage;
import de.hsos.swe.afairstartnew.common.boundary.web.ListPage;
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
 * Access to list and creation of Devices
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
@Path("/devices")
@RolesAllowed({"user", "admin"})
public class DevicesPage implements ListPage<DeviceImportDTO> {

    private final ManageDevices manageDevices;
    private final Template listTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public DevicesPage(ManageDevices manageDevices, @Location("devices/list.qute.html") Template listTemplate) {
        this.manageDevices = manageDevices;
        this.listTemplate = listTemplate;
    }

    @GET
    public TemplateInstance get(@QueryParam("type") String type) {
        if (type == null || type.isEmpty()) {
            return listTemplate.data("devices", manageDevices.list(type));
        } else {
            return listTemplate.data("devices", manageDevices.list());
        }
    }

    @Override @POST @Transactional
    public TemplateInstance post(@Form DeviceImportDTO deviceImportDTO) {
        Optional<DeviceExportDTO> device = manageDevices.create(deviceImportDTO);

        if (device.isPresent()) {
            return get(null);
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "Device could not be created");
        }
    }
}
