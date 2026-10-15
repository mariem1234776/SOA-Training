package webservices;
import entities.Module;

import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class ModuleService {
    @GET
    @Path("/matricule/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getmodulebymatricule(@PathParam("matricule") String matricule) {
        Module module = new ModuleBusiness().getModuleByMatricule(matricule);
        return Response
                .status(200)
                .entity(module)
                .build();
    }
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        return Response
                .status(200)
                .entity(new ModuleBusiness().addModule(module))
                .build();
    }
    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@QueryParam("type") String type) {

        List<Module> result = new ModuleBusiness().getModulesByType(Module.TypeModule.valueOf(type));

        return Response
                .status(200)
                .entity(result)
                .build();
    }

    @PUT
    @Path("/update/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {

        return Response
                .status(200)
                .entity(new ModuleBusiness().updateModule(matricule, updatedModule))
                .build();
    }

    @DELETE
    @Path("/delete/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(@PathParam("matricule") String matricule) {

        return Response
                .status(200)
                .entity(new ModuleBusiness().deleteModule(matricule))
                .build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {

        List<Module> result = new ModuleBusiness().getAllModules();

        return Response
                .status(200)
                .entity(result)
                .build();
    }
    @GET
    @Path("/ue/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@PathParam("code") int code) {

        UniteEnseignement ue = new UniteEnseignementBusiness().getUEByCode(code);

        List<Module> result = new ModuleBusiness().getModulesByUE(ue);

        return Response
                .status(200)
                .entity(result)
                .build();
    }
}
