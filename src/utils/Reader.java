package utils;

public class Reader {
    private static Reader ourInstance = new Reader();

    public static Reader getInstance() {
        return ourInstance;
    }

    private Reader() {
    }

}
