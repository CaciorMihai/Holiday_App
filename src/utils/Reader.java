package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    private static Reader ourInstance = new Reader();

    public static Reader getInstance() {
        return ourInstance;
    }

    private Reader() {
    }

    public ArrayList<String> read(String filename) {
        ArrayList<String> locations = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ( (line = reader.readLine()) != null ) {
                locations.add(line);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return locations;
    }
}
