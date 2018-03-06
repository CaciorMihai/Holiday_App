import classes.Activity;
import classes.World;
import interfaces.LocationInterface;
import utils.Date;
import utils.DateManager;
import utils.LocationFactory;
import utils.Reader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class that contains all the test methods.
 */
public class TestClass {
    private static TestClass ourInstance = new TestClass();
    public static TestClass getInstance() {
        return ourInstance;
    }
    private TestClass() {
    }

    public void queryTopLocationsTest(int top, String str, Date start, Date end) {
        String[] tokens = str.split(" ");
        System.out.println(World.getInstance().queryTopLocations(top, tokens, start, end));
    }

    public void queryLocationTest(String str) {
        String[] tokens = str.split(" ");
        System.out.println(World.getInstance().queryLocationInfo(tokens));
    }

    public void hierarchyTest() {
        LinkedHashMap<String, LocationInterface> world = World.getInstance().getCountries();

        for (Map.Entry<String, LocationInterface> country : world.entrySet()) {
            System.out.print("-> " + country.getKey());
            printActivities(country.getValue());
            for (Map.Entry<String, LocationInterface> county : country.getValue().getSubdivisions().entrySet()) {
                System.out.print("\t-> " + county.getKey());
                printActivities(county.getValue());
                for (Map.Entry<String, LocationInterface> city : county.getValue().getSubdivisions().entrySet()) {
                    System.out.print("\t\t-> " + city.getKey());
                    printActivities(city.getValue());
                }
            }
        }
        System.out.println("");
    }

    public void printActivities(LocationInterface loc) {
        if (loc.isDestination()) {
            System.out.print(": ");
            for (Map.Entry<String, Activity> a : loc.getActivities().entrySet()) {
                System.out.print(a.getKey() + ", ");
            }
            System.out.print(" Interval:" + loc.getDateManager() + " Price:" + loc.getDailyPrice());
        }
        System.out.println("");
    }

    public void readerTest(final String filePath) {
        ArrayList<String> locations;
        locations = Reader.getInstance().read(filePath);
        for (String str : locations) {
            LocationFactory.getInstance().factory(str);
            System.out.println(str);
        }
    }

    public void dateManagerTest() {
        DateManager m;
        Date d;
        int i = 0;

        m = new DateManager("12:04-15:05");
        d = new Date("12:05");
        test("" + i++, m.contains(d));
        d = new Date("12:04");
        test("" + i++, m.contains(d));
        d = new Date("12:03");
        test("" + i++, !m.contains(d));
        d = new Date("13:04");
        test("" + i++, m.contains(d));
        d = new Date("12:04");
        test("" + i++, m.contains(d));
        d = new Date("11:04");
        test("" + i++, !m.contains(d));
        d = new Date("14:05");
        test("" + i++, m.contains(d));
        d = new Date("15:05");
        test("" + i++, m.contains(d));
        d = new Date("16:05");
        test("" + i++, !m.contains(d));
        d = new Date("15:04");
        test("" + i++, m.contains(d));
        d = new Date("15:05");
        test("" + i++, m.contains(d));
        d = new Date("15:06");
        test("" + i++, !m.contains(d));

        m = new DateManager("15:05 - 12:04");
        d = new Date("12:05");
        test("" + i++, !m.contains(d));
        d = new Date("12:04");
        test("" + i++, m.contains(d));
        d = new Date("12:03");
        test("" + i++, m.contains(d));
        d = new Date("13:04");
        test("" + i++, !m.contains(d));
        d = new Date("12:04");
        test("" + i++, m.contains(d));
        d = new Date("11:04");
        test("" + i++, m.contains(d));
        d = new Date("14:05");
        test("" + i++, !m.contains(d));
        d = new Date("15:05");
        test("" + i++, m.contains(d));
        d = new Date("16:05");
        test("" + i++, m.contains(d));
        d = new Date("15:04");
        test("" + i++, !m.contains(d));
        d = new Date("15:05");
        test("" + i++, m.contains(d));
        d = new Date("15:06");
        test("" + i++, m.contains(d));

    }

    public void test(String test, Boolean bool) {
        if (bool) {
            System.out.println(test + " Passed");
        } else {
            System.out.println(test + " Failed");
        }
    }
}
