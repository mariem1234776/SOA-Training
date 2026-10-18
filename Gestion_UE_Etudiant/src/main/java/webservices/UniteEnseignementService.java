package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/uniteenseignement")
public class UniteEnseignementService {
    @GET
    @Path("/code/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code) {

        UniteEnseignement ue = new UniteEnseignementBusiness().getUEByCode(code);


        return Response
                .status(200)
                .entity(ue)
                .build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUniteEnseignement(UniteEnseignement ue) {

        return Response
                .status(200)
                .entity(new UniteEnseignementBusiness().addUniteEnseignement(ue))
                .build();
    }

    @GET
    @Path("/domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByDomaine(@PathParam("domaine") String domaine) {

        List<UniteEnseignement> result =
                new UniteEnseignementBusiness().getUEByDomaine(domaine);

        return Response
                .status(200)
                .entity(result)
                .build();
    }
    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@PathParam("semestre") int semestre) {

        List<UniteEnseignement> result =
                new UniteEnseignementBusiness().getUEBySemestre(semestre);

        return Response
                .status(200)
                .entity(result)
                .build();
    }
    @DELETE
    @Path("/delete/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUniteEnseignement(@PathParam("code") int code) {
        return Response
                .status(200)
                .entity(new UniteEnseignementBusiness().deleteUniteEnseignement(code))
                .build();
    }
    @PUT
    @Path("/update/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUniteEnseignement(
            @PathParam("code") int code,
            UniteEnseignement updatedUE) {

        return Response
                .status(200)
                .entity(new UniteEnseignementBusiness()
                        .updateUniteEnseignement(code, updatedUE))
                .build();
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUE() {
        return Response
                .status(200)
                .entity(new UniteEnseignementBusiness().getListeUE())
                .build();
    }
}
