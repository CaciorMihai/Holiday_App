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
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
//        dateManagerTest();
//        readerTest();

        System.out.println("\n====== Hierarchy Test ======");
        hierarchyTest("./input");

        System.out.println("\n==== Location Query Test ====");
        queryLocationTest("Romania Brasov Predeal");
        queryLocationTest("Romania Brasov");
        queryLocationTest("Romania Olt");

        System.out.println("\n==== Activity Query Test ====");
        String str;
        str = World.getInstance().queryActivityLocations(5,"Gratar",
                new Date("10:02"), new Date("15:02"));
        System.out.println(str);
    }

    private static void queryLocationTest(String str) {
        String[] tokens = str.split(" ");
        System.out.println(World.getInstance().queryLocationInfo(tokens));
    }

    private static void hierarchyTest(final String filePath) {
        ArrayList<String> locations;
        locations = Reader.getInstance().read(filePath);
        for (String str : locations) {
            LocationFactory.getInstance().factory(str);
        }
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

    private static void printActivities(LocationInterface loc) {
        if (loc.isDestination()) {
            System.out.print(": ");
            for (Map.Entry<String, Activity> a : loc.getActivities().entrySet()) {
                System.out.print(a.getKey() + ", ");
            }
            System.out.print(" Interval:" + loc.getDateManager() + " Price:" + loc.getDailyPrice());
        }
        System.out.println("");
    }

    private static void readerTest() {
        ArrayList<String> locations;
        locations = Reader.getInstance().read("./test_input_1");
        for (String str : locations) {
            System.out.println(str);
        }
    }

    private static void dateManagerTest() {
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

    private static void test(String test, Boolean bool) {
        if (bool) {
            System.out.println(test + " Passed");
        } else {
            System.out.println(test + " Failed");
        }
    }
}
