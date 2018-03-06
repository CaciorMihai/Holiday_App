package interfaces;

import classes.Activity;
import utils.DateManager;

import java.util.LinkedHashMap;

public interface LocationInterface {
    String getName();
    Boolean isDestination();
    int getDailyPrice();
    DateManager getDateManager();
    LinkedHashMap<String, LocationInterface> getSubdivisions();
    LinkedHashMap<String, Activity> getActivities();
    void makeDestination(int setDailyPrice, String dates, String activities);
}
