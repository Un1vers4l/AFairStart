package de.hsos.swe.afairstartnew.users.boundary.web;

import de.hsos.swe.afairstartnew.common.boundary.web.ErrorPage;
import de.hsos.swe.afairstartnew.common.boundary.web.InstancePage;
import de.hsos.swe.afairstartnew.users.control.ManageUsers;
import de.hsos.swe.afairstartnew.users.entity.UserExportDTO;
import de.hsos.swe.afairstartnew.users.entity.UserImportDTO;
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
 * Access a specific Users instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
@Path("/users/{id:\\w+}")
@RolesAllowed({"user", "admin"})
public class UsersIdPage implements InstancePage<String, UserImportDTO> {

    private final ManageUsers manageUsers;
    private final Template detailsTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public UsersIdPage(ManageUsers manageUsers, @Location("users/details.qute.html") Template detailsTemplate) {
        this.manageUsers = manageUsers;
        this.detailsTemplate = detailsTemplate;
    }

    @Override @GET
    public TemplateInstance get(@PathParam("id") String id) {
        Optional<UserExportDTO> user = manageUsers.get(id);

        if (user.isPresent()) {
            return detailsTemplate.data("user", user.get());
        } else {
            return ErrorPage.error(Response.Status.NOT_FOUND.getStatusCode(), "User not found");
        }
    }

    @POST @Transactional
    public TemplateInstance post(@Form UserImportDTO userImportDTO) {
        Optional<UserExportDTO> user = manageUsers.update(userImportDTO);

        if (user.isPresent()) {
            return get(userImportDTO.getUsername());
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "User could not be created");
        }
    }

    @Path("/delete") @POST @Transactional
    public Response delete(@PathParam("id") String id) {
        manageUsers.delete(id);
        return Response.status(Response.Status.OK).build();
    }
}
