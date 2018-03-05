package interfaces;

import classes.Activity;

import java.util.LinkedHashMap;

public interface LocationInterface {
    String getName();
    Boolean isDestination();
    LinkedHashMap<String, LocationInterface> getSubdivisions();
    LinkedHashMap<String, Activity> getActivities();
    void makeDestination(int setDailyPrice, String dates, String activities);
}
