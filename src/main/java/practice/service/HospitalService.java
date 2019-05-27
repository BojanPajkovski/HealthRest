package practice.service;

import practice.dao.HospitalDAOIMPL;
import practice.model.Hospital;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 04.10.2018.
 */
public class HospitalService {

    HospitalDAOIMPL hospitalDAOIMPL = new HospitalDAOIMPL();


    public List<Hospital> getHospitalType(int cityId){

        List<Hospital> hospitals = hospitalDAOIMPL.getHospitalsByCity(cityId);

        List<Hospital> privateHospitals = new ArrayList<Hospital>();

        for(Hospital h :hospitals){

            if(h.getType().equals("privatna")){

                privateHospitals.add(h);

            }

        }
        for(Hospital h :privateHospitals){
                System.out.println("Bolnicata " + h.getName() + " e privatna");

        }

        return privateHospitals;
    }
}
