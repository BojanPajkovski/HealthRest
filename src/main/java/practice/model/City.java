package practice.model;

/**
 * Created by User on 20.09.2018.
 */
public class City {

    private int id;

    private String name;

    private double population;

    public City() {
    }

    public City(String name, double population) {
        this.name = name;
        this.population = population;
    }

    public City(int id, String name, double population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (id != city.id) return false;
        if (Double.compare(city.population, population) != 0) return false;
        if (!name.equals(city.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(population);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
