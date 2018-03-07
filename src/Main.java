import classes.World;
import utils.Date;
import utils.LocationFactory;
import utils.Reader;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> locations;
        locations = Reader.getInstance().read("./input");
        for (String str : locations) {
            LocationFactory.getInstance().factory(str);
        }

        String help = "\n" +
                "Enter one of the following:\n" +
                "1 to search a location.\n" +
                "2 to search a activity.\n" +
                "3 to get the cheapest locations.\n" +
                "4 to show this help.\n" +
                "5 to exit.\n" +
                "6 to show all locations.\n" +
                "7 to show all activities.\n" +
                "8 to run some tests\n" +
                "\n" +
                "1 - Searching a locations is done by entering the path to it\n" +
                "\t(\"Country,County,City\")\n" +
                "2 - Searching a activity is done by entering first the number\n" +
                "\tof locations you want to be shown, then the activity name\n" +
                "\tand finally the time interval separeted by a comma\n" +
                "\t(10,Activity,01:01-31:12).\n" +
                "3 - Searching for the top cheapest locations is done by first\n" +
                "\tentering the number of locations, then the path to the\n" +
                "\tregion you want to search and finally the time interval\n" +
                "\t(5,Romania Brasov,02:01-30:12)";
        System.out.println(help);
        Scanner s = new Scanner(System.in);
        String str;
        int option;
        World world = World.getInstance();

        while (true) {
            System.out.println("\nPlease select an option: ");
            str = s.nextLine();
            try {
                option = Integer.parseInt(str.trim());
            } catch (Exception e) {
                System.out.println("Invalid option");
                continue;
            }
            switch (option) {
                default:
                    System.out.println("Invalid option");
                    break;
                case 1:
                    System.out.println("Input format must be: " + "\"Country,County,City\"");
                    str = s.nextLine();
                    System.out.println(world.parseQueryLocationInfo(str));
                    break;
                case 2:
                    System.out.println("Input format must be: " + "\"10,Activity,01:01-31:12\"" +
                            " The time interval may be replaced by any char for any time interval");
                    str = s.nextLine();
                    System.out.println(world.parseQueryActivityLocations(str));
                    break;
                case 3:
                    System.out.println("Input format must be: " + "\"5,Country County,02:01-30:12\"" +
                            " The time interval may be replaced by any char for any time interval");
                    str = s.nextLine();
                    System.out.println(world.parseQueryTopLocations(str));
                    break;
                case 4:
                    System.out.println(help);
                    break;
                case 5:
                    return;
                case 6:
                    TestClass.getInstance().hierarchyTest();
                    break;
                case 7:
                    System.out.println(world.displayAllActivities());
                    break;
                case 8:
                    runTests();
                    break;
            }
        }
    }

    static void runTests() {
        World.getInstance().clear();
        TestClass test = TestClass.getInstance();

        System.out.println("==== Date Test ====");
        test.dateManagerTest();

        System.out.println("\n==== File Reader Test ====");
        test.readerTest("./input");

        System.out.println("\n==== Hierarchy Test ====");
        test.hierarchyTest();

        System.out.println("\n==== Location Query Test ====");
        test.queryLocationTest("Romania Brasov Predeal");
        test.queryLocationTest("Romania Brasov Brasov");
        test.queryLocationTest("Romania Olt");

        System.out.println("\n==== Activity Query Test ====");
        String str;
        str = World.getInstance().queryActivityLocations(5,"Gratar",
                new Date("10:02"), new Date("15:02"));
        System.out.println(str);

        System.out.println("\n==== Top Location Query Test ====");
        test.queryTopLocationsTest(2, "Romania Brasov", new Date("10:02"), new Date("15:02"));
        test.queryTopLocationsTest(1, "Romania Olt", new Date("10:02"), new Date("15:02"));
    }
}
