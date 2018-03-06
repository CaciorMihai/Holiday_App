package interfaces;

import java.util.TreeMap;

public interface ActivityInterface {
    String getName();
    TreeMap<Integer, LocationInterface> getLocations();
}
