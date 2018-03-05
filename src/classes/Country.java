package classes;

import abstractClasses.Location;

public class Country extends Location {
    public Country(String setName, int setDailyPrice, String activities) {
        this(setName);
        this.isDestination = true;
    }

    public Country(String setName) {
        this.name = setName;
        this.isDestination = false;
        this.dailyPrice = 0;
        this.subdivisions = null;
        this.activities = null;
    }
}
