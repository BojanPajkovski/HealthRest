package practice.service;

import practice.dao.DoctorDAOIMPL;
import practice.dao.PatientDAOIMPL;
import practice.model.Patient;
 //import practice.model.PatientsPerDoctorDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 04.10.2018.
 */
public class PatientService {


    PatientDAOIMPL patientDAOIMPL = new PatientDAOIMPL();
    DoctorDAOIMPL doctorDAOIMPL = new DoctorDAOIMPL();


    public Patient printTheTheYoungestStudentIfEven() {


        Patient patient = patientDAOIMPL.getYoungestPatient();

        if (patient.getAge() % 2 == 0) {

            System.out.println(patient.getName());

        }
        return patient;
    }

    /*public Patient getOldestPatientDg(int id) {

        List<PatientsPerDoctorDTO> patientsByDoctors = doctorDAOIMPL.getPatientsByDoctor(id);

        int maxAge = 0;
        String patientName = "";
        String dg = "";

        for (PatientsPerDoctorDTO p : patientsByDoctors) {

            if (p.getAge() > maxAge) {

                maxAge = p.getAge();
            }

        }
        for(PatientsPerDoctorDTO p :patientsByDoctors){

            if( p.getAge() == maxAge){

                System.out.println(p.getPatientName() + " " + p.getDg());
            }

        }


        return null;
    }*/

    public List<Patient> getPatientsOver30() {

        List<Patient> patients = patientDAOIMPL.getAll();

        HashMap<Integer, Patient> patientHashMap = new HashMap<Integer, Patient>();

        for (Patient p : patients) {

            patientHashMap.put(p.getId(), p);

        }

        for (Integer key : patientHashMap.keySet()) {

        if(patientHashMap.get(key).getAge() >=30 ){

            System.out.println("pacientot " +patientHashMap.get(key).getName() + " ima godini " +patientHashMap.get(key).getAge() );
        }


        }

        return null;
    }

    public Patient getPatientCharacters (int id ){

        Patient patient = patientDAOIMPL.getById(id);

        if(patient.getName().length() %2 == 0){

            System.out.println("Imeto na pacientot e " + patient.getName() + " a brojot na karakteri vo negovoto ime e "
                    + patient.getName().length());

        }

        return null;
    }
}

