package classes;

import interfaces.LocationInterface;
import utils.Date;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The root of the Hierarchy Tree.
 * Contains all the countries in a LinkedHashMap for easy searching and in a TreeMap, sorted after
 * the price.
 * Contains all the activities from all the locations in a LinkedHashMap for easy searching.
 */
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
        sortedSubdivisions = new TreeMap<>();
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

    /**
     * This method returns all the activities in the World for you to choose.
     * @return All the activities in a String, separated by comma.
     */
    public String displayAllActivities() {
        String str = "";
        for (Map.Entry<String, Activity> a : activities.entrySet()) {
            str += a.getKey() + ",";
        }
        return str;
    }

    /**
     * A method that returns the names of the cheapest locations.
     * @param top Number of returned locations
     * @param query The root of witch we search the top locations.
     * @param start The start date.
     * @param end The end date.
     * @return A string with the first top locations with their price separated by ";"
     */
    public String queryTopLocations(int top, String[] query, Date start, Date end) {
        String str = "";
        LocationInterface location = queryLocation(query);

        if (location != null) {
            for (Map.Entry<Integer, LocationInterface> l : location.getSortedSubdivisions().entrySet()) {
                if (start == null || end == null) {
                    str += l.getValue() + "\n";
                    top--;
                } else {
                    if (l.getValue().getDateManager().contains(start) &&
                            l.getValue().getDateManager().contains(end)) {
                        str += l.getValue() + "\n";
                        top--;
                    }
                }
                if (top <= 0) {
                    break;
                }
            }
        }

        return str;
    }
    public String parseQueryTopLocations(String input) {
        String[] tokens = input.split("[,]");
        int top = Integer.parseInt(tokens[0]);
        Date start, end;
        if (tokens[2].split("[-]").length != 2) {
            start = null;
            end = null;
        } else {
            start = new Date(tokens[2].split("[-]")[0]);
            end = new Date(tokens[2].split("[-]")[1]);
        }
        String[] location = tokens[1].split(" ");
        return queryTopLocations(top, location, start, end);
    }

    /**
     * A method that returns the names of the cheapest locations where we can to a certain activity.
     * @param top Number of locations to be returned.
     * @param activity The activity we want.
     * @param start The start date.
     * @param end The end date.
     * @return
     */
    public String queryActivityLocations(int top, String activity, Date start, Date end) {
        String str = "";
        Activity a;
        if (activities.containsKey(activity)) {
            a = activities.get(activity);
            for (Map.Entry<Integer, LocationInterface> l : a.getLocations().entrySet()) {
                if (start == null || end == null) {
                    str += l.getValue() + "\n";
                    top--;
                } else {
                    if (l.getValue().getDateManager().contains
                            (start) &&
                            l.getValue().getDateManager().contains(end)) {
                        str += l.getValue() + "\n";
                        top--;
                    }
                }
                if (top <= 0) {
                    break;
                }
            }
        }
        return str;
    }
    public String parseQueryActivityLocations(String input) {
        String[] tokens = input.split("[,]");
        int top = Integer.parseInt(tokens[0]);
        Date start, end;
        if (tokens[2].split("[-]").length != 2) {
            start = null;
            end = null;
        } else {
            start = new Date(tokens[2].split("[-]")[0]);
            end = new Date(tokens[2].split("[-]")[1]);
        }
        String activity = tokens[1];
        return queryActivityLocations(top, activity, start, end);
    }

    /**
     * A method that searches a certain location and returns all the info about it.
     * @param query The path to the location.
     * @return
     */
    public String queryLocationInfo(String[] query) {
        LocationInterface location = queryLocation(query);
        if (location == null) {
            return "Destination not found!";
        }
        return location.toString();
    }
    public String parseQueryLocationInfo(String input) {
        String[] tokens = input.split(" ");
        return queryLocationInfo(tokens);
    }

    public void clear() {
        sortedSubdivisions.clear();
        countries.clear();
        activities.clear();
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
