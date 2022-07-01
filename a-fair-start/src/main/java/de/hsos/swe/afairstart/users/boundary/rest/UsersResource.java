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
 * Admin only access to list and creation of Users
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v2/users")
@RolesAllowed({ "admin" })
public class UsersResource {

    @Inject
    UserService userService;

    @Context
    private SecurityContext securityContext;

    @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "POST").build();
    }

    @GET
    public Response get() {
        return Response.ok(userService.list()).build();
    }

    @POST
    @Transactional
    public Response post(UserImportDTO userImportDTO) {
        Optional<UserExportDTO> user = userService.create(userImportDTO);

        if (user.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(user.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
