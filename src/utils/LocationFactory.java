package utils;

import classes.City;
import classes.Country;
import classes.County;
import classes.World;
import interfaces.LocationInterface;

public class LocationFactory {
    private static LocationFactory ourInstance = new LocationFactory();

    public static LocationFactory getInstance() {
        return ourInstance;
    }

    private LocationFactory() {
    }

    public void factory(String location) {
        World world = World.getInstance();
        String[] tokens = location.split("[|]");
        LocationInterface locationInstance;

        if (tokens.length != 6) {
            System.out.println("Input error");
            return;
        }
        String name = tokens[0];
        String dates = tokens[3];
        int dailyPrice = Integer.parseInt(tokens[4].trim());
        String activities = tokens[5];

        switch (tokens[1]) {
            default:
                break;
            case "Oras":
                locationInstance = new City(name, dailyPrice, dates, activities);
                break;
            case "Judet":
                LocationInterface country;
                if (!world.getCountries().containsKey(tokens[2])) {
                    country = new Country(tokens[2]);
                    world.getCountries().put(tokens[2], country);
                } else {
                    country = world.getCountries().get(tokens[2]);
                }
                if (country.getSubdivisions().containsKey(name)) {
                    // TODO
                } else {
                    locationInstance = new County(name, dailyPrice, dates, activities);
                    country.getSubdivisions().put(name, locationInstance);
                }
                break;
            case "Tara":
                if (world.getCountries().containsKey(name)) {
                    // TODO
                } else {
                    locationInstance = new Country(name, dailyPrice, dates, activities);
                    world.getCountries().put(name, locationInstance);
                }
                break;
        }
    }
}
