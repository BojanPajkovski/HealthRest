package practice.restcontroller;

import practice.dao.CityDAOIMPL;
import practice.model.City;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 10.04.2019.
 */

@Path("/city")
public class CityController {


    CityDAOIMPL cityDAOIMPL = new CityDAOIMPL();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCitites(){

        List <City> cities = cityDAOIMPL.getAll();

        return Response.status(200).entity(cities).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getCity (@PathParam("id") int cityId){

        City city = cityDAOIMPL.getById(cityId);

        return Response.status(200).entity(city).build();

    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCity (City city){

         cityDAOIMPL.insert(city);

        return Response.status(201).build();

    }

    @PUT
    @Path("/update")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response updateCity (City city){

        cityDAOIMPL.update(city);

        return Response.status(204).build();
    }


/*
    *//*@*//**//*PO*//*ST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertFaculty (University university){

        universityDAOIMPL.insert(university);

        return Response.status(201).build();
    }*/

}



