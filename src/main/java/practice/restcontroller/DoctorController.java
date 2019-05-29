package practice.restcontroller;

import practice.dao.DoctorDAOIMPL;
import practice.model.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/doctor")
public class DoctorController {

    DoctorDAOIMPL doctorDAOIMPL = new DoctorDAOIMPL();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllDoctors(){

        List<Doctor> doctorList = doctorDAOIMPL.getAll();

        return Response.status(200).entity(doctorList).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getDoctor (@PathParam("id") int doctorId){

        Doctor doctor = doctorDAOIMPL.getById(doctorId);

        return Response.status(200).entity(doctor).build();

    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCity (Doctor doctor){

        doctorDAOIMPL.insert(doctor);

        return Response.status(201).build();

    }


    @PUT
    @Path("/update")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response updateCity (Doctor doctor){

        doctorDAOIMPL.update(doctor);

        return Response.status(204).build();
    }

    @DELETE
    @Path ("/delete/{id}")
    public Response deleteCity(@PathParam("id") int doctorId){

        doctorDAOIMPL.delete(doctorId);

        return Response.status(204).build();
    }

}
