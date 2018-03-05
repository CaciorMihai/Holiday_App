package classes;

import interfaces.LocationInterface;

import java.util.LinkedHashMap;

public class World {
    private static World ourInstance = new World();
    private LinkedHashMap<String, LocationInterface> countries;

    public static World getInstance() {
        return ourInstance;
    }

    private World() {
        countries = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, LocationInterface> getCountries() {
        return countries;
    }
}
