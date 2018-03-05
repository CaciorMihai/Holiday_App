import classes.World;
import interfaces.LocationInterface;
import utils.Date;
import utils.DateManager;
import utils.LocationFactory;
import utils.Reader;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        dateManagerTest();
//        readerTest();
        hierarchyTest("./test_input_1");
        hierarchyTest("./test_input_2");
        hierarchyTest("./test_input_3");
    }

    private static void hierarchyTest(final String filePath) {
        ArrayList<String> locations;
        locations = Reader.getInstance().read(filePath);
        for (String str : locations) {
            LocationFactory.getInstance().factory(str);
        }
        LinkedHashMap<String, LocationInterface> world = World.getInstance().getCountries();

        System.out.println("Testing " + filePath);
        for (Map.Entry<String, LocationInterface> country : world.entrySet()) {
            System.out.print("-> " + country.getKey());
            if (country.getValue().isDestination()) {
                System.out.println(" -d");
            } else {
                System.out.println("");
            }
            for (Map.Entry<String, LocationInterface> county : country.getValue().getSubdivisions().entrySet()) {
                System.out.print("\t-> " + county.getKey());
                if (county.getValue().isDestination()) {
                    System.out.println(" -d");
                } else {
                    System.out.println("");
                }
                for (Map.Entry<String, LocationInterface> city : county.getValue().getSubdivisions().entrySet()) {
                    System.out.print("\t\t-> " + city.getKey());
                    if (city.getValue().isDestination()) {
                        System.out.println(" -d");
                    } else {
                        System.out.println("");
                    }
                }
            }
        }
        world.clear();
        System.out.println("");
    }

    private static void readerTest() {
        ArrayList<String> locations;
        locations = Reader.getInstance().read("/home/cacior/AB4_Holiday_App/test_input_1");
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
