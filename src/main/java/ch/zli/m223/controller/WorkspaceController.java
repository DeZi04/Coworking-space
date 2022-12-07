package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Workplace;
import ch.zli.m223.service.WorkspaceService;

@Path("/workpsaces")
@Tag(name = "workspace", description = "Handling of workspaces")
@RolesAllowed({ "User", "Admin" })
public class WorkspaceController {

    @Inject
    WorkspaceService workspaceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Index all categories.", 
        description = "Returns a list of all Workspaces."
    )
    public List<Workplace> index() {
        return workspaceService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Creates a new workspace.", 
        description = "Creates a new workspace and returns the newly added workspace."
    )
    public Workplace create(Workplace workspace) {
       return workspaceService.createWorkspace(workspace);
    }

    @Path("/{id}")
    @DELETE
    @Operation(
        summary = "Deletes an workspace.",
        description = "Deletes an workspace by its id."
    )
    public void delete(@PathParam("id") Integer id) {
        workspaceService.deleteWorkspace(id);
    }
}
