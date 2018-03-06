package abstractClasses;

import classes.Activity;
import classes.World;
import interfaces.LocationInterface;
import sun.reflect.generics.tree.Tree;
import utils.DateManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class Location implements LocationInterface{
    protected String name;
    protected boolean isDestination;
    protected LinkedHashMap<String, LocationInterface> subdivisions;
    protected LinkedHashMap<String, Activity> activities;
    protected TreeMap<Integer, LocationInterface> sortedSubdivisions;
    protected int dailyPrice;
    private DateManager dateManager;

    public Location (String setName, int setDailyPrice, String dates, String activities) {
        this(setName);
        makeDestination(setDailyPrice, dates, activities);
    }

    public Location (String setName) {
        this.name = setName;
        this.isDestination = false;
        this.dailyPrice = 0;
        this.subdivisions = null;
        this.activities = null;
        subdivisions = new LinkedHashMap<>();
        sortedSubdivisions = new TreeMap<>();
    }

    private void parseActivities(String activitiesString) {
        String[] tokens = activitiesString.split("[,]");
        for (String activity : tokens) {
            activity = activity.trim();
            if (!World.getInstance().getActivities().containsKey(activity)) {
                World.getInstance().getActivities().put(activity, new Activity(activity));
            }
            activities.put(activity, World.getInstance().getActivities().get(activity));
            World.getInstance().getActivities().get(activity).getLocations().put(this.dailyPrice,this);
        }
    }

    @Override
    public TreeMap<Integer, LocationInterface> getSortedSubdivisions() {
        return sortedSubdivisions;
    }

    @Override
    public void makeDestination(int setDailyPrice, String dates, String activitiesString) {
        this.isDestination = true;
        this.dailyPrice = setDailyPrice;
        dateManager = new DateManager(dates);
        activities = new LinkedHashMap<>();
        parseActivities(activitiesString);
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

    @Override
    public DateManager getDateManager() {
        return dateManager;
    }

    @Override
    public int getDailyPrice() {
        return dailyPrice;
    }

    @Override
    public String toString() {
        if (!isDestination) {
            return name + " is not a touristic destination!";
        }
        String info = "";
        info += name;
        info += " " + dateManager;
        info += " " + dailyPrice + "/day ";
        for (Map.Entry<String, Activity> a : activities.entrySet()) {
            info += a.getKey() + ";";
        }
        return info;
    }
}
