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

    /**
     *
     * @param location A string that contains all the information about a location.
     */
    public void factory(String location) {
        World world = World.getInstance();
        String[] tokens = location.split("[|]");

        if (tokens.length != 6) {
            System.out.println("Input error");
            return;
        }
        String dates = tokens[3];
        int dailyPrice = Integer.parseInt(tokens[4].trim());
        String activities = tokens[5];

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
                    country = world.getCountries().get(countryName);
                } else {
                    country = new Country(countryName);
                    world.getCountries().put(countryName, country);
                }

                /*
                 * Check if the county of the city exists, create it if it dose not.
                 */
                if (country.getSubdivisions().containsKey(countyName)) {
                    county = country.getSubdivisions().get(countyName);
                } else {
                    county = new Country(countyName);
                    country.getSubdivisions().put(countyName, county);
                }

                /*
                 * Check if the city exists, create it if it dose not.
                 */
                if (county.getSubdivisions().containsKey(cityName)) {
                    county.getSubdivisions().get(cityName).makeDestination(dailyPrice, dates,
                            activities);
                } else {
                    city = new City(cityName, dailyPrice, dates, activities);
                    county.getSubdivisions().put(cityName, city);
                    county.getSortedSubdivisions().put(city.getDailyPrice(), city);
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
                    county = new County(countyName, dailyPrice, dates, activities);
                    country.getSubdivisions().put(countyName, county);
                    country.getSortedSubdivisions().put(county.getDailyPrice(), county);
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
                    country = new Country(countryName, dailyPrice, dates, activities);
                    world.getCountries().put(countryName, country);
                    world.getSortedSubdivisions().put(country.getDailyPrice(), country);
                }
                break;
        }
    }
}
