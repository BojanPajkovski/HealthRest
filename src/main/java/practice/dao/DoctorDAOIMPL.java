package practice.dao;

import practice.dbconnection.ConnectionWithPattern;
import practice.model.Doctor;
import practice.dto.PatientsPerDoctorDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.09.2018.
 */
public class DoctorDAOIMPL {

    public void delete(int id) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;


        try {


            stmt = conn.createStatement();
            String sqlQuery = "DELETE from doctors  where doctors.id = ";
            sqlQuery += id;
            stmt.execute(sqlQuery);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }

    }

    public void insert(Doctor doctor) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;
        ResultSet rst = null;


        try {


            String sqlQuery = "INSERT INTO doctors (name ,surname, job_position,age,isMatichen, hospitalId) VALUES(?,?,?,?,?,?);";


            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSurName());
            stmt.setString(3, doctor.getJobPosition());
            stmt.setInt(4, doctor.getAge());
            stmt.setBoolean(5, doctor.isMatichen());
            stmt.setInt(6, doctor.getHospitalId());

            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }


    }

    public void update(Doctor doctor) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;
        ResultSet rst = null;

        try {

            String sqlQuery = "update doctor set name =?, surname = ?,job_position =?,age=?,isMatichen =?,hospitalId = ? where id= ?";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSurName());
            stmt.setString(3, doctor.getJobPosition());
            stmt.setInt(4, doctor.getAge());
            stmt.setBoolean(5, doctor.isMatichen());
            stmt.setInt(6, doctor.getHospitalId());
            stmt.setInt(7,doctor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }

    }

    public List<Doctor> getAll() {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Doctor> doctors = null;


        try {


            stmt = conn.createStatement();

            String sqlQuery = "SELECt * from doctors";

            rst = stmt.executeQuery(sqlQuery);

            doctors = new ArrayList<Doctor>();


            while (rst.next()) {

                int doctorId = rst.getInt("id");
                String doctorName = rst.getString("name");
                String doctorSurname = rst.getString("surname");
                String doctorJobPosition = rst.getString("job_position");
                int doctorAge = rst.getInt("age");
                boolean doctorIsMAtichen = rst.getBoolean("isMatichen");
                int doctorHospitalId = rst.getInt("hospitalId");

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname,doctorJobPosition,doctorAge,doctorIsMAtichen,doctorHospitalId);
                doctors.add(doctor);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }
        return doctors;

    }

    public Doctor getById (int id){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = " SELECT * FROM city WHERE id = ";
            sqlQuery+=id;

            rst = stmt.executeQuery(sqlQuery);

            while(rst.next()){

                int doctorId = rst.getInt("id");
                String doctorName = rst.getString("name");
                String doctorSurname = rst.getString("surname");
                String doctorJobPosition = rst.getString("job_position");
                int doctorAge = rst.getInt("age");
                boolean doctorIsMAtichen = rst.getBoolean("isMatichen");
                int doctorHospitalId = rst.getInt("hospitalId");

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname,doctorJobPosition,doctorAge,doctorIsMAtichen,doctorHospitalId);
                return doctor;
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }
        return null;
    }

    public List<PatientsPerDoctorDTO> getPatientsByDoctor (int id){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List <PatientsPerDoctorDTO> patientsPerDoctors = null;



        try{

            stmt = conn.createStatement();

            String sqlQuery = "SELECT d.name as DoctorName, p.name as PatientName, p.age as PatientAge, dp.dg from health.doctor as d inner join health.doctor_patient as " +
                    "dp on d.id=dp.doctor_id inner join patient as p on p.id=dp.patient_id where d.id = ";

            sqlQuery+=id;

            rst = stmt.executeQuery(sqlQuery);

            patientsPerDoctors = new ArrayList<PatientsPerDoctorDTO>();

            while(rst.next()){

                String doctorName = rst.getString("DoctorName");
                String patientName = rst.getString("PatientName");
                Integer patientAge = rst.getInt("PatientAge");
                String dg = rst.getString("dg");

                PatientsPerDoctorDTO patientsPerDoctor = new PatientsPerDoctorDTO(doctorName,patientName,dg,patientAge);

                patientsPerDoctors.add(patientsPerDoctor);


            }



        }catch(Exception e){e.printStackTrace();}

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }

        return patientsPerDoctors;
    }

    public List <Doctor> searchDoctorsByText( String text){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Doctor> doctors = null;


        try {


            stmt = conn.createStatement();

            String sqlQuery = "SELECT * FROM health.doctor as d where d.name like '";

            sqlQuery+=text +"%' ";

            sqlQuery+= "or d.surname like '";
            sqlQuery+=text+"%' ";
            sqlQuery+="or d.job_position like '";

            sqlQuery+=text+"%' ";

            rst = stmt.executeQuery(sqlQuery);

            doctors = new ArrayList<Doctor>();


            while (rst.next()) {

                int doctorId = rst.getInt("id");
                String doctorName = rst.getString("name");
                String doctorSurname = rst.getString("surname");
                String doctorJobPosition = rst.getString("job_position");
                int doctorAge = rst.getInt("age");
                boolean doctorIsMAtichen = rst.getBoolean("isMatichen");
                int doctorHospitalId = rst.getInt("hospitalId");

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname,doctorJobPosition,doctorAge,doctorIsMAtichen,doctorHospitalId);
                doctors.add(doctor);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }
        return doctors;



    }

    public List <Doctor> getDoctorsByHospitalId (int id){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List <Doctor> doctors = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = " SELECT * FROM health.doctor WHERE hospitalId = ";
            sqlQuery+=id;

            rst = stmt.executeQuery(sqlQuery);

            doctors = new ArrayList<Doctor>();

            while(rst.next()){

                int doctorId = rst.getInt("id");
                String doctorName = rst.getString("name");
                String doctorSurname = rst.getString("surname");
                String doctorJobPosition = rst.getString("job_position");
                int doctorAge = rst.getInt("age");
                boolean doctorIsMAtichen = rst.getBoolean("isMatichen");
                int doctorHospitalId = rst.getInt("hospitalId");

                Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname,doctorJobPosition,doctorAge,doctorIsMAtichen,doctorHospitalId);
                doctors.add(doctor);
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        finally {

            try { rst.close(); } catch (Exception e)  {   e.printStackTrace(); }
            try { stmt.close(); } catch (Exception e) {  e.printStackTrace(); }
            try { conn.close(); } catch (Exception e) {  e.printStackTrace();}
        }
        return doctors;
    }

}
