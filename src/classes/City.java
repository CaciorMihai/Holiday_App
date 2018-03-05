package classes;

import abstractClasses.Location;

public class City extends Location {
    public City(String setName) {
        super(setName);
    }
    public City(String setName, int setDailyPrice, String dates, String activities) {
        super(setName, setDailyPrice, dates, activities);
    }
}
