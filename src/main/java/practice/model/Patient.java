package practice.model;

/**
 * Created by User on 20.09.2018.
 */
public class Patient {

    private int id;
    private String name;
    private String surName;
    private int age;

    public Patient(int id, String name, String surName, int age) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
    }

    public Patient(String patientName, String patientSurname, int patientAge) {

        this.name = patientName;
        this.surName = patientSurname;
        this.age = patientAge;
    }

    public Patient(){


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

    public int getAge() {return age;
    }

    public void setAge(int age) {this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        if (age != patient.age) return false;
        if (id != patient.id) return false;
        if (name != null ? !name.equals(patient.name) : patient.name != null) return false;
        if (surName != null ? !surName.equals(patient.surName) : patient.surName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
