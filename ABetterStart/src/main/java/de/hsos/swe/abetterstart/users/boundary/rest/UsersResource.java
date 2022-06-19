package de.hsos.swe.abetterstart.users.boundary.rest;

import de.hsos.swe.abetterstart.common.boundary.rest.ListResource;
import de.hsos.swe.abetterstart.users.control.ManageUsers;
import de.hsos.swe.abetterstart.users.entity.UserExportDTO;
import de.hsos.swe.abetterstart.users.entity.UserImportDTO;

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
@RolesAllowed({"admin"})
public class UsersResource implements ListResource<UserImportDTO> {

    private final ManageUsers manageUsers;

    @Context
    private SecurityContext securityContext;

    @Inject
    public UsersResource(ManageUsers manageUsers) {
        this.manageUsers = manageUsers;
    }

    @Override @OPTIONS
    public Response options() {
        return Response.ok().allow("GET", "POST").build();
    }

    @Override @GET
    public Response get() {
        return Response.ok(manageUsers.list()).build();
    }

    @Override @POST @Transactional
    public Response post(UserImportDTO userImportDTO) {
        Optional<UserExportDTO> user = manageUsers.create(userImportDTO);

        if (user.isPresent()) {
            return Response.status(Response.Status.CREATED).entity(user.get()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
