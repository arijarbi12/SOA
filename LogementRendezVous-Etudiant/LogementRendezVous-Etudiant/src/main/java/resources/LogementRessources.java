package resources;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/logements")
public class LogementRessources {

    LogementBusiness logementBusiness = new LogementBusiness();

    //web service : list logement=>json
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok()
                .entity(this.logementBusiness.getLogements())
                .build();
    }




    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement(Logement logement) {
            if(this.logementBusiness.addLogement(logement)){

            return Response.status(201).entity("created!").build();
        } else
            return Response.status(200).entity("error").build();
        }

    @GET
    @Path("/getById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById( int id) {
        return Response.ok()
                .entity(this.logementBusiness.getLogementsByReference(id))
                .build();
    }


    @GET
    @Path("/getByDelecation/{delecation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDelecation(@PathParam("delecation") String delecation) {
        return Response.ok()
                .entity(this.logementBusiness.getLogementsByDeleguation(delecation))
                .build();
    }

    @GET
    @Path("/getLogementByReference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(int reference) {
        return Response.ok()
                .entity(this.logementBusiness.getLogementsListeByref(reference))
                .build();
    }
    @DELETE
    @Path("/deleteLogement/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("id") int id) {
        return Response.ok()
                .entity(this.logementBusiness.deleteLogement(id))
                .build();
    }

    @PUT
    @Path("/updateLogement/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("id") int reference, Logement logement) {
        return Response.ok()
                .entity(this.logementBusiness.updateLogement(reference,logement))
                .build();
    }
    @PUT
    @Path("/setLogements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setLogements() {


        List<Logement> logements = this.logementBusiness.getLogements();
        logementBusiness.setLogements(logements);

        return Response.ok()
                .entity(logements)
                .build();
    }
}



