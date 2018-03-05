import utils.Date;
import utils.DateManager;
import utils.Reader;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        dateManagerTest();
        readerTest();
    }

    private static void readerTest() {
        ArrayList<String> locations = new ArrayList<>();
        locations = Reader.getInstance().read("/home/cacior/AB4_Holiday_App/input");
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
