package practice.restcontroller;

import practice.dao.CityDAOIMPL;
import practice.model.City;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCities (@Context UriInfo info){

        String name = info.getQueryParameters().getFirst("name");
        String populationStr = info.getQueryParameters().getFirst("population");
        Integer population = null;
        if(populationStr!=null){
            population = Integer.valueOf(populationStr);
        }

        System.out.println("VAlues are " + name + population);
        System.out.println("SQL string is " + prepareSQLString(name,population));

        String sql = prepareSQLString(name, population);
        List <City> cities = cityDAOIMPL.searchCities(sql);
        return Response.status(200).entity(cities).build();
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

    @DELETE
    @Path ("/delete/{id}")
    public Response deleteCity(@PathParam("id") int cityId){

        cityDAOIMPL.delete(cityId);

        return Response.status(204).build();
    }


    private String prepareSQLString(String name, Integer population){
        String sql = "SELECT * from city as c where ";

        boolean hasName = false;
        if(name != null){
            sql+= "c.name like '%" + name + "%'";
            hasName = true;
        }

        if(population !=null){
            if(hasName) {
                sql+=" and c.population < " + population;
            } else {
                sql+=" c.population < " + population;
            }

        }
        return sql;
    }
}



