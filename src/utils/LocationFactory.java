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
        String dates = tokens[3];
        int dailyPrice = Integer.parseInt(tokens[4].trim());
        String activities = tokens[5];

        LocationInterface parent;
        String cityName;
        String countyName;
        String countryName;

        LocationInterface city;
        LocationInterface county;
        LocationInterface country;

        switch (tokens[1]) {
            default:
                break;
            case "Oras":
                cityName = tokens[0];
                countryName = tokens[2].split(" ")[0];
                countyName = tokens[2].split(" ")[1];

                /*
                 * Check if the country of the city exists, create it if it dose not.
                 */
                if (world.getCountries().containsKey(countryName)) {
                    parent = world.getCountries().get(countryName);
                } else {
                    parent = new Country(countryName);
                    world.getCountries().put(countryName, parent);
                }

                /*
                 * Check if the county of the city exists, create it if it dose not.
                 */
                if (parent.getSubdivisions().containsKey(countyName)) {
                    parent = parent.getSubdivisions().get(countyName);
                } else {
                    LocationInterface aux = new Country(countyName);
                    parent.getSubdivisions().put(countyName, aux);
                    parent = aux;
                }

                /*
                 * Check if the city exists, create it if it dose not.
                 */
                if (parent.getSubdivisions().containsKey(cityName)) {
                    parent.getSubdivisions().get(cityName).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new City(cityName, dailyPrice, dates, activities);
                    parent.getSubdivisions().put(cityName, locationInstance);
                    parent.getSortedSubdivisions().put(locationInstance.getDailyPrice(),
                            locationInstance);
                }
                break;
            case "Judet":
                countryName = tokens[2];
                countyName = tokens[0];

                /*
                 * Check if the country of the county exists, create it if it dose not.
                 */
                if (!world.getCountries().containsKey(countryName)) {
                    country = new Country(countryName);
                    world.getCountries().put(countryName, country);
                } else {
                    country = world.getCountries().get(countryName);
                }

                /*
                 * Check if the county exists, create it if it dose not.
                 */
                if (country.getSubdivisions().containsKey(countyName)) {
                    country.getSubdivisions().get(countyName).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new County(countyName, dailyPrice, dates, activities);
                    country.getSubdivisions().put(countyName, locationInstance);
                    country.getSortedSubdivisions().put(locationInstance.getDailyPrice(),
                            locationInstance);
                }
                break;
            case "Tara":
                countryName = tokens[0];
                /*
                 * Check if the country exists, create it if it dose not.
                 */
                if (world.getCountries().containsKey(countryName)) {
                    world.getCountries().get(countryName).makeDestination(dailyPrice, dates, activities);
                } else {
                    locationInstance = new Country(countryName, dailyPrice, dates, activities);
                    world.getCountries().put(countryName, locationInstance);
                    world.getSortedSubdivisions().put(locationInstance.getDailyPrice(),
                            locationInstance);
                }
                break;
        }
    }
}
