package abstractClasses;

import classes.Activity;
import interfaces.LocationInterface;

import java.util.LinkedHashMap;

public abstract class Location implements LocationInterface{
    protected String name;
    protected boolean isDestination;
    protected LinkedHashMap<String, LocationInterface> subdivisions;
    protected LinkedHashMap<String, Activity> activities;
    protected int dailyPrice;

    @Override
    public LinkedHashMap<String, Activity> getActivities() {
        return activities;
    }

    @Override
    public LinkedHashMap<String, LocationInterface> getSubdivisions() {
        if (isDestination) {
            return subdivisions;
        }
        return null;
    }

    @Override
    public Boolean isDestination() {
        return isDestination;
    }

    @Override
    public String getName() {
        return name;
    }
}
