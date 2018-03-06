package classes;

import interfaces.ActivityInterface;
import interfaces.LocationInterface;

import java.util.TreeMap;

/**
 * A activity with a unique name. It holds all the locations where it can be practiced in a treemap
 * sorted after the location price.
 */
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
