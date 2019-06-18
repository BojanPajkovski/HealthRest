package practice.restcontroller;

import practice.dao.HospitalDAOIMPL;
import practice.model.Hospital;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/hospital")
public class HospitalController {

    HospitalDAOIMPL hospitalDAOIMPL = new HospitalDAOIMPL();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllHospitals(){

        List<Hospital> allHospitals = hospitalDAOIMPL.getAll();

        return Response.status(200).entity(allHospitals).build();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertHospital (Hospital hospital){

        hospitalDAOIMPL.insert(hospital);

        return Response.status(201).build();

    }

    @PUT
    @Path("/update")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response updateHospital (Hospital hospital){

        hospitalDAOIMPL.update(hospital);

        return Response.status(204).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getHospitalById (@PathParam("id") int hospitalId){

        Hospital hospital = hospitalDAOIMPL.getById(hospitalId);

        return Response.status(200).entity(hospital).build();

    }

    @DELETE
    @Path("/delete/{id}")

    public Response deleteHospital(@PathParam("id") int hospitalId){

        hospitalDAOIMPL.delete(hospitalId);

        return Response.status(204).build();
    }

    @GET
    @Path("/filterByCity/{cityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterHospitalsByCity(@PathParam("cityId") Integer cityId){

       List<Hospital> hospitals =  hospitalDAOIMPL.getHospitalsByCity(cityId);

        return Response.status(200).entity(hospitals).build();

    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCities (@Context UriInfo info){

        String name = info.getQueryParameters().getFirst("name");
        String location = info.getQueryParameters().getFirst("location");
       /* Integer population = null;
        if(populationStr!=null){
            population = Integer.valueOf(populationStr);
        }*/

        System.out.println("VAlues are " + name + location);
        System.out.println("SQL string is " + prepareSQLString(name,location));

        String sql = prepareSQLString(name, location);
        List <Hospital> hospitals = hospitalDAOIMPL.searchHospitals(sql);
        return Response.status(200).entity(hospitals).build();
    }

    private String prepareSQLString(String name, String location){
        String sql = "SELECT * from hospital as h where ";

        boolean hasName = false;
        if(name != null){
            sql+= "h.name like '%" + name + "%'";
            hasName = true;
        }

        if(location !=null){
            if(hasName) {
                sql+=" and h.location < " + location;
            } else {
                sql+=" h.location < " + location;
            }

        }
        return sql;
    }
}
