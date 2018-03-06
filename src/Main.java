import classes.World;
import utils.Date;

public class Main {

    public static void main(String[] args) {
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
