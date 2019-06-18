package practice.model;

/**
 * Created by User on 20.09.2018.
 */
public class Hospital {

    private int id;
    private String name;
    private String location;
    private String type;
    private int cityId;

    public Hospital(){

    }

    public Hospital(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;

    }

    public Hospital(int id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;

    }

    public Hospital(int id, String name, String location, String type, int cityId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.cityId = cityId;
    }

    public Hospital(String hospitalName) {

        this.name =hospitalName;
    }

    public Hospital(String hospitalName, String hospitalLocation) {

        this.name = hospitalName;
        this.location = hospitalLocation;
    }

    public int getId() {return id;
    }

    public void setId(int id) {this.id = id;
    }

    public String getName() {return name;
    }

    public void setName(String name) {this.name = name;
    }

    public String getLocation() {return location;
    }

    public void setLocation(String location) {this.location = location;
    }

    public String getType() {return type;
    }

    public void setType(String type) {this.type = type;
    }

    public int getCityId() {return cityId;
    }

    public void setCityId(int cityId) {this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;

        Hospital hospital = (Hospital) o;

        if (cityId != hospital.cityId) return false;
        if (id != hospital.id) return false;
        if (location != null ? !location.equals(hospital.location) : hospital.location != null) return false;
        if (name != null ? !name.equals(hospital.name) : hospital.name != null) return false;
        if (type != null ? !type.equals(hospital.type) : hospital.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + cityId;
        return result;
    }
}
