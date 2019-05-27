package practice.service;

import practice.dao.DoctorDAOIMPL;
import practice.model.Doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 05.10.2018.
 */
public class DoctorService {

    DoctorDAOIMPL doctorDAOIMPL = new DoctorDAOIMPL();


    public Map<Integer,Doctor> getDoctorsWithHospital(int id){

        List<Doctor> doctorList = doctorDAOIMPL.getDoctorsByHospitalId(id);

        Map<Integer, Doctor> map = new HashMap<Integer, Doctor>();

        for(Doctor d :doctorList){

            map.put(d.getId(),d);
        }

        Map<Integer, Doctor> finalMap = new HashMap<Integer, Doctor>();
        for(Integer key :map.keySet()){
            if(map.get(key).isMatichen() && map.get(key).getAge()<=40 && map.get(key).getSurName().length() %2 !=0 ){
                finalMap.put(key, map.get(key));
                System.out.println(" Doktorot " + map.get(key).getName() + " ne e postar od 40 godini i prezimeto mu e sostaveno od neparen broj bukvi (karakteri) ");
            }

        }
        return finalMap;
    }

}
