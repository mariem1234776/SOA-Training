package webservices;

import entities.UniteEnseignement;
import filtres.Secured;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/UE")
@Secured
public class UniteEnsWS {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUE(UniteEnseignement ue) {
        if (ue == null || helper.getUEByCode(ue.getCode()) != null) {
            return Response.status(400).build();
        }
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).build();
        }
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUE(@QueryParam("semestre") Integer semestre,
                          @QueryParam("code") Integer code) {
        if (code != null) {
            UniteEnseignement ue = helper.getUEByCode(code);
            if (ue != null) {
                return Response.status(200).entity(ue).build();
            }
            return Response.status(404).build();
        }
        if (semestre != null) {
            return Response.status(200).entity(helper.getUEBySemestre(semestre)).build();
        }
        return Response.status(200).entity(helper.getListeUE()).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }
}