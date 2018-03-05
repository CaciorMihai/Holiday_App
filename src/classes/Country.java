package classes;

import abstractClasses.Location;

public class Country extends Location {
    public Country(String setName) {
        super(setName);
    }
    public Country(String setName, int setDailyPrice, String dates, String activities) {
        super(setName, setDailyPrice, dates, activities);
    }
}
