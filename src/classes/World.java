package classes;

import interfaces.LocationInterface;

import java.util.LinkedHashMap;

public class World {
    private static World ourInstance = new World();
    private LinkedHashMap<String, LocationInterface> countries;
    private LinkedHashMap<String, Activity> activities;

    public static World getInstance() {
        return ourInstance;
    }

    private World() {
        countries = new LinkedHashMap<>();
        activities = new LinkedHashMap<>();
    }

    public String querryLocation(String name, String type) {

    }

    public LinkedHashMap<String, LocationInterface> getCountries() {
        return countries;
    }

    public LinkedHashMap<String, Activity> getActivities() {
        return activities;
    }
}
