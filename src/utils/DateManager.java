package utils;

/**
 * This class manages time intervals.
 */
public class DateManager {
    private Date start;
    private Date end;

    /**
     * @param dates A string with the format "day:month-day:month"
     */
    public DateManager(String dates) {
        String[] tokens = dates.trim().split("-");
        start = null;
        end = null;
        if (tokens.length == 2) {
            start = new Date(tokens[0]);
            end = new Date(tokens[1]);
        } else {
            System.out.println("Format error in DateManager: " + dates + " " + tokens.length);
        }
    }

    public Boolean contains(Date date) {
        if (start.compareTo(end) == 0) {
            return (start.compareTo(date) == 0);
        }
        if (start.compareTo(end) < 0) {
            if (date.compareTo(start) >= 0 && end.compareTo(date) >= 0) {
                return true;
            }
            return false;
        } else { // If start is bigger than end then new year is in between them.
            if (date.compareTo(start) >= 0) {
                return true;
            }
            if (date.compareTo(end) <= 0) {
                return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + start + "-" + end + ")";
    }
}
