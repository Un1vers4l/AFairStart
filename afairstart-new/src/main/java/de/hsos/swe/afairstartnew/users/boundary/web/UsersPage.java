package de.hsos.swe.afairstartnew.users.boundary.web;

import de.hsos.swe.afairstartnew.common.boundary.web.ErrorPage;
import de.hsos.swe.afairstartnew.common.boundary.web.ListPage;
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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.Optional;

/**
 * Admin only access to list and creation of Users
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
@Path("/users")
@RolesAllowed({"admin"})
public class UsersPage implements ListPage<UserImportDTO> {

    private final ManageUsers manageUsers;
    private final Template listTemplate;

    @Context
    private SecurityContext securityContext;

    @Inject
    public UsersPage(ManageUsers manageUsers, @Location("users/list.qute.html") Template listTemplate) {
        this.manageUsers = manageUsers;
        this.listTemplate = listTemplate;
    }

    @Override @GET
    public TemplateInstance get() {
        List<UserExportDTO> users = manageUsers.list();
        return listTemplate.data("users", users);
    }

    @Override @POST
    public TemplateInstance post(@Form UserImportDTO userImportDTO) {
        Optional<UserExportDTO> user = manageUsers.create(userImportDTO);

        if (user.isPresent()) {
            return get();
        } else {
            return ErrorPage.error(Response.Status.BAD_REQUEST.getStatusCode(), "User could not be created");
        }
    }
}
