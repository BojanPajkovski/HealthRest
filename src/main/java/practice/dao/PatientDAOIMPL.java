package practice.dao;

import practice.dbconnection.ConnectionWithPattern;
import practice.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21.09.2018.
 */
public class PatientDAOIMPL {

    public void delete(int id) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;


        try {


            stmt = conn.createStatement();
            String sqlQuery = "DELETE from patient  where patient.id = ";
            sqlQuery += id;
            stmt.execute(sqlQuery);

        } catch (Exception ex) {
            ex.printStackTrace();
        }




    }

    public void insert(Patient patient) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;
        ResultSet rst = null;


        try {


            String sqlQuery = "INSERT INTO patient (name ,surname, age) VALUES(?,?,?);";


            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getSurName());
            stmt.setInt(3, patient.getAge());


            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    public void update(Patient patient) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;
        ResultSet rst = null;

        try {

            String sqlQuery = "update patient set name =?, surname = ?,age =? where id= ?";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getSurName());
            stmt.setInt(3, patient.getAge());
            stmt.setInt(4,patient.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public List<Patient> getAll() {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Patient> patients = null;


        try {


            stmt = conn.createStatement();

            String sqlQuery = "SELECt * from patient";

            rst = stmt.executeQuery(sqlQuery);

            patients = new ArrayList<Patient>();


            while (rst.next()) {

                int patientId = rst.getInt("id");
                String patientName = rst.getString("name");
                String patientSurname = rst.getString("surname");
                int patientAge = rst.getInt("age");


                Patient patient = new Patient(patientId, patientName, patientSurname,patientAge);
                patients.add(patient);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return patients;

    }

    public Patient getById (int id){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = " SELECT * FROM patient WHERE id = ";
            sqlQuery+=id;

            rst = stmt.executeQuery(sqlQuery);

            while(rst.next()){

                int patientId = rst.getInt("id");
                String patientName = rst.getString("name");
                String patientSurname = rst.getString("surname");
                int patientAge = rst.getInt("age");

                Patient patient = new Patient(patientId, patientName, patientSurname,patientAge);
                return patient;
            }

        }catch(Exception e){

            e.printStackTrace();
        }



        return null;
    }

    public List <Patient> searchPatientByText( String text){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Patient> patients = null;


        try {


            stmt = conn.createStatement();

            String sqlQuery = "SELECT * FROM health.patient as p where p.name like '";

                    sqlQuery+=text +"%' ";

                    sqlQuery+= "or p.surname like '";

                    sqlQuery+=text+"%'";

            rst = stmt.executeQuery(sqlQuery);

            patients = new ArrayList<Patient>();


            while (rst.next()) {

                int patientId = rst.getInt("id");
                String patientName = rst.getString("name");
                String patientSurname = rst.getString("surname");
                int patientAge = rst.getInt("age");


                Patient patient = new Patient(patientId, patientName, patientSurname,patientAge);
                patients.add(patient);


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return patients;

    }

    public Patient getYoungestPatient(){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        Patient patient = null;

        try{

            stmt = conn.createStatement();

            String sqlQuery = "SELECT  p.name, p.surname, p.age FROM health.patient as p order by age ASC limit 1 ";

            rst = stmt.executeQuery(sqlQuery);

            while (rst.next()) {


                String patientName = rst.getString("name");
                String patientSurname = rst.getString("surname");
                int patientAge = rst.getInt("age");


              patient = new Patient( patientName, patientSurname, patientAge);



            }

        }catch(Exception e){

            e.printStackTrace();
        }



        return patient;
    }

    public List<Patient> searchPatients(String sql){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;
        List<Patient> patients = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            patients = new ArrayList<>();
            while (rst.next()) {
                int patientId = rst.getInt("id");
                String patientName = rst.getString("name");
                String patientSurname = rst.getString("surname");
                int patientAge = rst.getInt("age");

                Patient patient = new Patient(patientId, patientName, patientSurname,patientAge);
                patients.add(patient);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return patients;
    }


}
