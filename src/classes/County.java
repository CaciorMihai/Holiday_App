package classes;

import abstractClasses.Location;

public class County extends Location {
    public County(String setName) {
        super(setName);
    }
    public County(String setName, int setDailyPrice, String dates, String activities) {
        super(setName, setDailyPrice, dates, activities);
    }
}
