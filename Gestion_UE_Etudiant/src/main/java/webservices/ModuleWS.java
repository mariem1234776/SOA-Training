package webservices;

import entities.Module;
import entities.UniteEnseignement;
import filtres.Secured;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
@Secured
public class ModuleWS {
    static ModuleBusiness helper = new ModuleBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addModule(Module module) {
        if (module == null || module.getMatricule() == null
                || module.getUniteEnseignement() == null) {
            return Response.status(400).build();
        }
        if (UniteEnsWS.helper.getUEByCode(module.getUniteEnseignement().getCode()) == null) {
            return Response.status(404).build();
        }
        if (helper.getModuleByMatricule(module.getMatricule()) != null) {
            return Response.status(400).build();
        }
        if (helper.addModule(module)) {
            return Response.status(201).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@QueryParam("codeUE") Integer codeUE) {
        if (codeUE == null) {
            return Response.status(400).build();
        }
        UniteEnseignement ue = UniteEnsWS.helper.getUEByCode(codeUE);
        if (ue == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(helper.getModulesByUE(ue)).build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(200).entity(module).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}