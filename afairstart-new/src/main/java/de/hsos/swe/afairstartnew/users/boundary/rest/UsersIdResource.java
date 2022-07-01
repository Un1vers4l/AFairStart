package de.hsos.swe.afairstartnew.users.boundary.rest;

import de.hsos.swe.afairstartnew.common.boundary.rest.InstanceResource;
import de.hsos.swe.afairstartnew.users.control.ManageUsers;
import de.hsos.swe.afairstartnew.users.entity.UserExportDTO;
import de.hsos.swe.afairstartnew.users.entity.UserImportDTO;

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
 * Access a specific User instance
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/users/{username:\\w+}")
@RolesAllowed({"user", "admin"})
public class UsersIdResource implements InstanceResource<String, UserImportDTO> {

    private final ManageUsers manageUsers;

    @Context
    private SecurityContext securityContext;

    @Inject
    public UsersIdResource(ManageUsers manageUsers) {
        this.manageUsers = manageUsers;
    }

    @Override @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "PUT", "DELETE").build();
    }

    @Override @GET
    public Response get(@PathParam("username") String username) {
        return Response.ok(manageUsers.list()).build();
    }

    @Override @PUT @Transactional
    public Response put(@PathParam("username") String username, UserImportDTO userImportDTO) {
        userImportDTO.setUsername(username);
        Optional<UserExportDTO> user = manageUsers.update(userImportDTO);

        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override @DELETE @Transactional
    public Response delete(@PathParam("username") String username) {
        if (manageUsers.delete(username)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
