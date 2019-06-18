package practice.restcontroller;

import practice.dao.DoctorDAOIMPL;
import practice.dto.PatientsPerDoctorDTO;
import practice.model.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    public Response insertDoctor (Doctor doctor){

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

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCities (@Context UriInfo info){

        String name = info.getQueryParameters().getFirst("name");
        String surname = info.getQueryParameters().getFirst("surname");
        //Boolean isMatichen = Boolean.parseBoolean(info.getQueryParameters().getFirst("isMatichen"));

        System.out.println("VAlues are " + name + surname );
        System.out.println("SQL string is " + prepareSQLString(name,surname));

        String sql = prepareSQLString(name, surname);
        List <Doctor> doctors = doctorDAOIMPL.searchDoctors(sql);
        return Response.status(200).entity(doctors).build();
    }

    @GET
    @Path("/filterByHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterHospitalsByCity(@PathParam("hospitalId") Integer hospitalId){

        List<Doctor> hospitals =  doctorDAOIMPL.getDoctorsByHospitalId(hospitalId);

        return Response.status(200).entity(hospitals).build();

    }

                        /*GET PATIETNS BY DOCTOR CREATING DTO*/

    @GET
    @Path("/patientsByDoctor/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response patientsByDoctor(@PathParam("id") Integer doctorId){

        List<PatientsPerDoctorDTO> patientsPerDoctorDTOs =  doctorDAOIMPL.getPatientsByDoctor(doctorId);

        return Response.status(200).entity(patientsPerDoctorDTOs).build();

    }


    private String prepareSQLString(String name, String surname){

        String sql = "SELECT * from doctor as d where ";

        boolean hasName = false;


        if(name != null){
            sql+= "d.name like '%" + name + "%'";
            hasName = true;
        }

        if(surname !=null){
            if(hasName) {
                sql+=" and d.surname like '%" + surname + "%'";
            } else {
                sql+=" d.surname like '%" + surname + "%'";
            }

        }
        return sql;
    }

}
