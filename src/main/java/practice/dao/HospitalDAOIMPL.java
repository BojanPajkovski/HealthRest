package practice.dao;

import practice.dbconnection.ConnectionWithPattern;
import practice.model.Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.09.2018.
 */
public class HospitalDAOIMPL {

    public void delete(int id) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = "delete from hospital where id = ";
            sqlQuery += id;

            stmt.execute(sqlQuery);


        } catch (Exception e) {


            e.printStackTrace();
        }




    }

    public void update(Hospital hospital) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;

        try {


            String sqlQuery = "update hospital set name =?, location = ?, type =?, cityId = ? where id = ?";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getLocation());
            stmt.setString(3, hospital.getType());
            stmt.setInt(4, hospital.getCityId());
            stmt.setInt(5, hospital.getId());

            stmt.executeUpdate();


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public void insert(Hospital hospital) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        PreparedStatement stmt = null;

        try {

            String sqlQuery = " insert into hospital (name, location, type, cityId) values (?,?,?,?)";

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setString(1, hospital.getName());
            stmt.setString(2, hospital.getLocation());
            stmt.setString(3, hospital.getType());
            stmt.setInt(4, hospital.getCityId());

            stmt.executeUpdate();

        } catch (Exception e) {


        }



    }

    public List<Hospital> getAll() {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Hospital> hospitals = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = "select * from hospital";

            rst = stmt.executeQuery(sqlQuery);

            hospitals = new ArrayList<Hospital>();

            while (rst.next()) {

                int hospitalId = rst.getInt("id");
                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");
                String hospitalType = rst.getString("type");
                int hospitalCityId = rst.getInt("cityId");

                Hospital hospital = new Hospital(hospitalId, hospitalName, hospitalLocation, hospitalType, hospitalCityId);

                hospitals.add(hospital);


            }


        } catch (Exception e) {


        }



        return hospitals;

    }

    public Hospital getById(int id) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        Hospital hospital = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = "select * from hospital where id = ";
            sqlQuery += id;

            rst = stmt.executeQuery(sqlQuery);

            while (rst.next()) {

                int hospitalId = rst.getInt("id");
                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");
                String hospitalType = rst.getString("type");

                hospital = new Hospital(hospitalId,hospitalName,hospitalLocation,hospitalType);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return hospital;
    }

    public List<Hospital> getHospitalsByLocation(String location) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Hospital> hospitals = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = "SELECT h.name, h.location FROM health.hospital as h where h.location = ' ";
            sqlQuery += location + "'";

            rst = stmt.executeQuery(sqlQuery);

            hospitals = new ArrayList<Hospital>();

            while (rst.next()) {

                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");

                Hospital hospital = new Hospital(hospitalName, hospitalLocation);
                hospitals.add(hospital);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return hospitals;
    }

    public List<Hospital> searchHospitalByText(String text) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Hospital> hospitals = null;


        try {


            stmt = conn.createStatement();

            String sqlQuery = "SELECT * FROM health.hospital as h where h.name like '";

            sqlQuery += text + "%' ";

            sqlQuery += "or h.location like '";

            sqlQuery += text + "%'";

            rst = stmt.executeQuery(sqlQuery);

            hospitals = new ArrayList<Hospital>();


            while (rst.next()) {

                int hospitalId = rst.getInt("id");
                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");
                String hospitalType = rst.getString("type");
                int hospitalCityId = rst.getInt("cityId");

                Hospital hospital = new Hospital(hospitalId, hospitalName, hospitalLocation, hospitalType, hospitalCityId);

                hospitals.add(hospital);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return hospitals;

    }

    public List<Hospital>  getHospitalsByCity(int cityId) {

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;

        List<Hospital> hospitals = null;

        try {

            stmt = conn.createStatement();

            String sqlQuery = "SELECT * FROM health.hospital as h where h.cityId = ";
            sqlQuery += cityId;

            rst = stmt.executeQuery(sqlQuery);

            hospitals = new ArrayList<Hospital>();

            while (rst.next()) {

                int hospitalId = rst.getInt("id");
                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");
                String hospitalType = rst.getString("type");
                int hospitalCityId = rst.getInt("cityId");

                Hospital hospital = new Hospital(hospitalId, hospitalName, hospitalLocation, hospitalType, hospitalCityId);

                hospitals.add(hospital);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return hospitals;


    }

    public List<Hospital> searchHospitals(String sql){

        Connection conn = ConnectionWithPattern.getConnectionWithPattern();
        Statement stmt = null;
        ResultSet rst = null;
        List<Hospital> hospitals = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            hospitals = new ArrayList<>();
            while (rst.next()) {
                int hospitalId = rst.getInt("id");
                String hospitalName = rst.getString("name");
                String hospitalLocation = rst.getString("location");
                Hospital hospital = new Hospital(hospitalId, hospitalName, hospitalLocation);
                hospitals.add(hospital);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hospitals;
    }
}
