package practice.restcontroller;

import practice.dao.DoctorDAOIMPL;
import practice.dao.PatientDAOIMPL;
import practice.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


/**
 * Created by User on 29.05.2019.
 */@Path("/patient")
public class PatientController {

    PatientDAOIMPL patientDAOIMPL = new PatientDAOIMPL();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getPatientByID(@PathParam("id") int patientId ){

        Patient patient = patientDAOIMPL.getById(patientId);
        return Response.status(200).entity(patient).build();


    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getAllPatients(){

        List<Patient> patientList = patientDAOIMPL.getAll();

        return Response.status(200).entity(patientList).build();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPatient (Patient patient){

        patientDAOIMPL.insert(patient);

        return Response.status(201).build();

    }

    @PUT
    @Path("/update")
    @Consumes (MediaType.APPLICATION_JSON)
    public Response updatePatient (Patient patient){

        patientDAOIMPL.update(patient);

        return Response.status(204).build();
    }

    @DELETE
    @Path("/delete/{id}")

    public Response deletePatient(@PathParam("id") int patientId){

        patientDAOIMPL.delete(patientId);
        return Response.status(204).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCities (@Context UriInfo info){

        String name = info.getQueryParameters().getFirst("name");
        String surname = info.getQueryParameters().getFirst("surname");
        String stringAge = info.getQueryParameters().getFirst("age");

        Integer age = null;

        age = Integer.valueOf(stringAge);


        System.out.println("Values are " + name + surname + age);
        System.out.println("SQL string is " + prepareSQLString(name,surname, age));

        String sql = prepareSQLString(name, surname, age);
        List <Patient> patients = patientDAOIMPL.searchPatients(sql);
        return Response.status(200).entity(patients).build();
    }

    private String prepareSQLString(String name,String surname, Integer age){

        String sql = "SELECT * from patient as p where p.name like '%" + name + "%'" + " and p.surname like '%"+surname+"%' and " +
                "p.age =" + age;

        return sql;
    }


}
