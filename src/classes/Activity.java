package classes;

import interfaces.ActivityInterface;
import interfaces.LocationInterface;

import java.util.TreeMap;

public class Activity implements ActivityInterface {
    private String name;
    private TreeMap<Integer, LocationInterface> locations;

    public Activity(String setName) {
        this.name = setName;
        locations = new TreeMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TreeMap<Integer, LocationInterface> getLocations() {
        return locations;
    }
}
