package webservices;

import entities.Module;
import entities.UniteEnseignement;
import filtres.Secured;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleWS {
    ModuleBusiness helper = new ModuleBusiness();

    @Path("/list")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module){
        if(helper.addModule(module)){
            return Response
                    .status(201)
                    .entity("Module added").build();
        }else return Response
                .status(400)
                .entity("Module not added")
                .build();

    }
    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(
            @PathParam("matricule") String matricule) {

        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {
            return Response.status(200)
                    .entity(module)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }

    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(
            @QueryParam("type") String type) {

        List<Module> result =
                helper.getModulesByType(Module.TypeModule.valueOf(type));

        return Response.status(200)
                .entity(result)
                .build();
    }

    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(
            @PathParam("matricule") String matricule,
            Module module) {

        if (helper.updateModule(matricule, module)) {
            return Response.status(200)
                    .entity("Module updated")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Module not updated")
                    .build();
        }
    }

    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(
            @PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("Module deleted")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Module not found")
                    .build();
        }
    }

    @Path("/ue/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("code") int code) {

        UniteEnseignement ue =
                new UniteEnseignementBusiness().getUEByCode(code);

        if (ue == null) {
            return Response.status(404)
                    .entity("UE not found")
                    .build();
        }

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }
}
