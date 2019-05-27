package practice.model;

/**
 * Created by User on 20.09.2018.
 */
public class Doctor {

    private int id;
    private String name;
    private String surName;
    private String jobPosition;
    private int age;
    private boolean isMatichen;
    private int hospitalId;

    public Doctor(int id, String name, String surName, String jobPosition, int age, boolean isMatichen, int hospitalId) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.jobPosition = jobPosition;
        this.age = age;
        this.isMatichen = isMatichen;
        this.hospitalId = hospitalId;
    }

    public int getId() {return id;
    }

    public void setId(int id) {this.id = id;
    }

    public String getName() {return name;
    }

    public void setName(String name) {this.name = name;
    }

    public String getSurName() {return surName;
    }

    public void setSurName(String surName) {this.surName = surName;
    }

    public String getJobPosition() {return jobPosition;
    }

    public void setJobPosition(String jobPosition) {this.jobPosition = jobPosition;
    }

    public int getAge() {return age;
    }

    public void setAge(int age) {this.age = age;
    }

    public boolean isMatichen() {return isMatichen;
    }

    public void setMatichen(boolean isMatichen) {this.isMatichen = isMatichen;
    }

    public int getHospitalId() {return hospitalId;
    }

    public void setHospitalId(int hospitalId) {this.hospitalId = hospitalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;

        Doctor doctor = (Doctor) o;

        if (age != doctor.age) return false;
        if (hospitalId != doctor.hospitalId) return false;
        if (id != doctor.id) return false;
        if (isMatichen != doctor.isMatichen) return false;
        if (jobPosition != null ? !jobPosition.equals(doctor.jobPosition) : doctor.jobPosition != null) return false;
        if (name != null ? !name.equals(doctor.name) : doctor.name != null) return false;
        if (surName != null ? !surName.equals(doctor.surName) : doctor.surName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (jobPosition != null ? jobPosition.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (isMatichen ? 1 : 0);
        result = 31 * result + hospitalId;
        return result;
    }
}
