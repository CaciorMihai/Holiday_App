package classes;

import interfaces.LocationInterface;
import utils.Date;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class World {
    private static World ourInstance = new World();
    private LinkedHashMap<String, LocationInterface> countries;
    private LinkedHashMap<String, Activity> activities;
    protected TreeMap<Integer, LocationInterface> sortedSubdivisions;

    public static World getInstance() {
        return ourInstance;
    }

    private World() {
        countries = new LinkedHashMap<>();
        activities = new LinkedHashMap<>();
    }

    private LocationInterface queryLocation(String[] query) {
        LinkedHashMap<String, LocationInterface> list = countries;
        LocationInterface location = new City("Filler City to make Java stop crying");
        int i = 0;
        while (i < query.length) {
            if (list.containsKey(query[i])) {
                location = list.get(query[i]);
                list = location.getSubdivisions();
                i++;
            } else {
                return null;
            }
        }
        return location;
    }

    public String queryTopLocations(int top, String[] query, Date start, Date end) {
        String str = "";
        LocationInterface location = queryLocation(query);

        if (location != null) {
            for (Map.Entry<Integer, LocationInterface> l : location.getSortedSubdivisions().entrySet()) {
                if (l.getValue().getDateManager().contains(start) &&
                        l.getValue().getDateManager().contains(end)) {
                    str += l.getValue().getName() + " " + l.getKey() + "/day; ";
                    top--;
                }
                if (top <= 0) {
                    break;
                }
            }
        }

        return str;
    }

    public String queryActivityLocations(int top, String activity, Date start, Date end) {
        String str = "";
        Activity a;
        if (activities.containsKey(activity)) {
            a = activities.get(activity);
            for (Map.Entry<Integer, LocationInterface> l : a.getLocations().entrySet()) {
                if (l.getValue().getDateManager().contains(start) &&
                        l.getValue().getDateManager().contains(end)) {
                    str += l.getValue().getName() + " " + l.getKey() + "/day; ";
                    top--;
                }
                if (top <= 0) {
                    break;
                }
            }
        }
        return str;
    }

    public String queryLocationInfo(String[] query) {
        LocationInterface location = queryLocation(query);
        if (location == null) {
            return "Destination not found!";
        }
        return location.toString();
    }

    public TreeMap<Integer, LocationInterface> getSortedSubdivisions() {
        return sortedSubdivisions;
    }

    public LinkedHashMap<String, LocationInterface> getCountries() {
        return countries;
    }

    public LinkedHashMap<String, Activity> getActivities() {
        return activities;
    }
}
