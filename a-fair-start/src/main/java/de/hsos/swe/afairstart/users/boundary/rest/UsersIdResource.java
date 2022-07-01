package de.hsos.swe.afairstart.users.boundary.rest;

import de.hsos.swe.afairstart.users.control.UserService;
import de.hsos.swe.afairstart.users.entity.UserExportDTO;
import de.hsos.swe.afairstart.users.entity.UserImportDTO;

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
@Path("/api/v2/users/{username}")
@RolesAllowed({ "user", "admin" })
public class UsersIdResource {

    @Inject
    UserService userService;

    @Context
    private SecurityContext securityContext;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "PUT", "DELETE").build();
    }

    @GET
    public Response get(@PathParam("username") String username) {
        return Response.ok(userService.list()).build();
    }

    @PUT
    @Transactional
    public Response put(@PathParam("username") String username, UserImportDTO userImportDTO) {
        userImportDTO.setUsername(username);
        Optional<UserExportDTO> user = userService.update(userImportDTO);

        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Transactional
    public Response delete(@PathParam("username") String username) {
        if (userService.delete(username)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
