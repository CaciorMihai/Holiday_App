package utils;

public class Date implements Comparable<Date>{
    private short day;
    private short month;

    /**
     * This constructor parses a string into a date
     * @param date The string format should be: "day:month"
     */
    public Date(String date) {
        String[] tokens = date.split(":");
        if (tokens.length == 2) {
            day = (short) Integer.parseInt(tokens[0].trim());
            month = (short) Integer.parseInt(tokens[1].trim());
        } else {
            System.out.println("Format error in Date: " + date + " " + tokens.length);
        }
    }

    /**
     * A bigger date is the one closer to the next new year.
     * @param date the compared object.
     * @return the compare result.
     */
    @Override
    public int compareTo(Date date) {
        if (this.month < date.getMonth()) {
            return -1;
        }
        if (this.month > date.getMonth()) {
            return 1;
        }
        return Short.compare(day, date.getDay());
    }

    public short getDay() {
        return day;
    }

    public short getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return day + ":" + month;
    }
}
