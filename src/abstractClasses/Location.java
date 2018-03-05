package abstractClasses;

import classes.Activity;
import interfaces.LocationInterface;
import utils.DateManager;

import java.util.LinkedHashMap;

public abstract class Location implements LocationInterface{
    protected String name;
    protected boolean isDestination;
    protected LinkedHashMap<String, LocationInterface> subdivisions;
    protected LinkedHashMap<String, Activity> activities;
    protected int dailyPrice;
    private DateManager dateManager;

    public Location (String setName, int setDailyPrice, String dates, String activities) {
        this(setName);
        this.isDestination = true;
        // todo
    }

    public Location (String setName) {
        this.name = setName;
        this.isDestination = false;
        this.dailyPrice = 0;
        this.subdivisions = null;
        this.activities = null;
        subdivisions = new LinkedHashMap<>();
    }

    @Override
    public void makeDestination(int setDailyPrice, String dates, String activities) {
        this.isDestination = true;
        // todo
    }

    @Override
    public LinkedHashMap<String, Activity> getActivities() {
        return activities;
    }

    @Override
    public LinkedHashMap<String, LocationInterface> getSubdivisions() {
        return subdivisions;
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
