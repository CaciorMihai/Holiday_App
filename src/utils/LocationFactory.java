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

        LocationInterface parent;
        String cityName;
        String countyName;
        String countryName;

        switch (tokens[1]) {
            default:
                break;
            case "Oras":
                cityName = tokens[0];
                countyName = tokens[2].split(" ")[1];
                countryName = tokens[2].split(" ")[0];
                if (world.getCountries().containsKey(countryName)) {
                    parent = world.getCountries().get(countryName);
                } else {
                    parent = new Country(countryName);
                    world.getCountries().put(countryName, parent);
                }

                if (parent.getSubdivisions().containsKey(countyName)) {
                    parent = parent.getSubdivisions().get(countyName);
                } else {
                    LocationInterface aux = new Country(countyName);
                    parent.getSubdivisions().put(countyName, aux);
                    parent = aux;
                }

                if (parent.getSubdivisions().containsKey(cityName)) {
                    parent.getSubdivisions().get(cityName).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new City(name, dailyPrice, dates, activities);
                    parent.getSubdivisions().put(cityName, locationInstance);
                }
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
                    country.getSubdivisions().get(name).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new County(name, dailyPrice, dates, activities);
                    country.getSubdivisions().put(name, locationInstance);
                }
                break;
            case "Tara":
                if (world.getCountries().containsKey(name)) {
                    world.getCountries().get(name).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new Country(name, dailyPrice, dates, activities);
                    world.getCountries().put(name, locationInstance);
                }
                break;
        }
    }
}
