package interfaces;

import classes.Activity;
import utils.DateManager;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public interface LocationInterface {
    String getName();
    Boolean isDestination();
    int getDailyPrice();
    DateManager getDateManager();
    LinkedHashMap<String, LocationInterface> getSubdivisions();
    LinkedHashMap<String, Activity> getActivities();
    TreeMap<Integer, LocationInterface> getSortedSubdivisions();
    void makeDestination(int setDailyPrice, String dates, String activities);
}
