package ressources;

import entities.Logement;
import filtres.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("logements")
public class LogementRessources {
    public static LogementBusiness logementMetier = new LogementBusiness();
 @Secured
@POST
 @Path("/new")
@Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement l) {
     if(logementMetier.addLogement(l))
         return  Response.status(Status.CREATED).build();
     return  Response.status(Status.NOT_FOUND).build();
    }

@Secured
@GET
@Path("/get")
@Produces(MediaType.APPLICATION_JSON)
    public Response getLogements() {
        List<Logement> liste=logementMetier.getLogements();
    if(liste.size()==0)
        return  Response.status(Status.NOT_FOUND).build();
    return  Response.status(Status.OK).entity(liste).build();


    }
@Secured
@PUT
@Path("reference/{id}")
@Consumes(MediaType.APPLICATION_JSON)
    public Response updateLogement(Logement updatedLogement, @PathParam("id") int reference) {


        if (logementMetier.updateLogement(reference,updatedLogement)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
@Secured
@DELETE
@Path("reference/{id}")
@Consumes(MediaType.APPLICATION_JSON)
        public  Response deleteLogement( @PathParam("id") int reference ){
           if(logementMetier.deleteLogement(reference))
                    return Response.status(Status.OK).build();


            return Response.status(Status.NOT_FOUND).build();

        }

}
