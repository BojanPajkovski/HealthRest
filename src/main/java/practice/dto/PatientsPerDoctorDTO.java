package practice.dto;

/**
 * Created by User on 24.09.2018.
 */
public class PatientsPerDoctorDTO {

     private String doctorName;

     private String patientName;

    private String dg;

    private int age;

    public PatientsPerDoctorDTO(String doctorName, String patientName, String dg, int patientAge) {

        this.doctorName = doctorName;
        this.patientName=patientName;
        this.dg = dg;
        this.age = patientAge;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDg() {

        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
    }

    public PatientsPerDoctorDTO(String doctorName, String patientName, String dg) {

        this.doctorName = doctorName;
        this.patientName = patientName;
        this.dg = dg;
    }

    public String getDoctorName() {return doctorName;
    }

    public void setDoctorName(String doctorName) {this.doctorName = doctorName;
    }

    public String getPatientName() {return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;}

    public PatientsPerDoctorDTO(String doctorName, String dg) {
        this.doctorName = doctorName;
        this.dg = dg;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientsPerDoctorDTO)) return false;

        PatientsPerDoctorDTO that = (PatientsPerDoctorDTO) o;

        if (dg != null ? !dg.equals(that.dg) : that.dg != null) return false;
        if (doctorName != null ? !doctorName.equals(that.doctorName) : that.doctorName != null) return false;
        if (patientName != null ? !patientName.equals(that.patientName) : that.patientName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doctorName != null ? doctorName.hashCode() : 0;
        result = 31 * result + (patientName != null ? patientName.hashCode() : 0);
        result = 31 * result + (dg != null ? dg.hashCode() : 0);
        return result;
    }
}
