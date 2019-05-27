package practice.service;

import practice.dao.CityDAOIMPL;
import practice.model.City;

import java.util.List;

/**
 * Created by User on 05.10.2018.
 */
public class CityService {

    CityDAOIMPL cityDAOIMPL = new CityDAOIMPL();

    public void  getCityPopulationMax(){

        double maxPopulation = 0;

        List<City> cities = cityDAOIMPL.getAll();

        for(City c:cities){

            if(c.getPopulation()>maxPopulation){

                maxPopulation = c.getPopulation();

            }

        }
        for(City c : cities){

            if(c.getPopulation() == maxPopulation ) {

                if(maxPopulation>= 50.000){

                    System.out.println(" Gradot so najgolem broj zhiteli e " + c.getName()+ ". Istiot e metropola");
            } else {

                    System.out.println(" Gradot so najgolem broj zhiteli e " + c.getName()+ ". Istiot e sreden grad po golemina");

                }
            }

            }
    }
}




