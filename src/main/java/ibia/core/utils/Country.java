package ibia.core.utils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import ibia.core.Log;
import com.google.gson.Gson;

/**
 * Utility class for dealing with country names and flags.
 * All methods are static.
 * All data is obtained from the repository at: https://github.com/stefangabos/world_countries
 */
public class Country {
    private static CountryData[] data;
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> codes = new ArrayList<>();
    private static String dataPath = "world-countries/data/en/world.json";

    /**
     * Data for countries read from a json file
     * is converted to instances of this class.
     */
    public static class CountryData {
        public int id;
        public String name;
        public String alpha2;
        public String alpha3;
    }

    /**
     * On the first call to this method, it
     * reads the raw data from a json file
     * and caches it into an array of 
     * CountryData. On subsequent calls, it 
     * returns the cached array. If the read
     * operation was unsuccessful, it returns
     * null and tries again on the next call.
     * 
     * @return An array containing data for each territory
     */
    public static CountryData[] getData() {
        if (data == null) {
            try {
                ClassLoader classLoader = Country.class.getClassLoader();
                InputStream stream = classLoader.getResourceAsStream(dataPath);
                
                int b;
                StringBuilder str = new StringBuilder();
                while ((b = stream.read()) != -1) {
                    str.append((char)b);
                }

                stream.close();
                String json = str.toString();
                data = new Gson().fromJson(json, CountryData[].class);
            } catch (Exception e) {
                Log.error(e.getMessage());
                e.printStackTrace();
                data = null; // Set data back to null to try again on the next call.
                return null;
            }
        }
        return data;
    }

    /**
     * Obtain a list of the full names for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
     * 
     * @return A list of names, or null if data is not available
     */
    public static ArrayList<String> listOfNames() {
        if (getData() == null) return null;
        if (names.size() == 0) {
            for (CountryData data : getData()) {
                names.add(data.name);
            }
        }
        return names;
    }

    /**
     * Obtain a list of the alpha-2 codes for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
     * 
     * @return A list of alpha2 codes, or null if data is not available
     */
    public static ArrayList<String> listOfCodes() {
        if (getData() == null) return null;
        if (codes.size() == 0) {
            for (CountryData data : getData()) {
                codes.add(data.alpha2);
            }
        }
        return codes;
    }

    /**
     * Returns the alpha-2 code for a territory,
     * given its full name.
     * 
     * @param name - The name of the country
     * @return The alpha2 associated with the given country name, or null if none is found.
     */
    public static String codeFromName(String name) {
        if (getData() == null) return null;
        for (CountryData country : getData()) {
            if (country.name.equals(name)) return country.alpha2;
        }
        return null;
    }

    /**
     * Return the full name for a territory,
     * given its alpha-2 code.
     * 
     * @param code - An alpha2 code
     * @return The name of the country associated with the given alpha2 code, or null if none is found.
     */
    public static String nameFromCode(String code) {
        if (getData() == null) return null;
        for (CountryData country : getData()) {
            if (country.alpha2.equals(code)) return country.name;
        }
        return null;
    }

    /**
     * Obtain a relative path to the .png file
     * for the country with the given alpha2 code.
     * 
     * @param code - An alpha2 code
     * @return The path to the flag, or null if an invalid code is given.
     */
    public static InputStream getFlag(String code) {
        if (listOfCodes().contains(code)) {
            String fileName = code.toLowerCase() + ".png";
            String resource = "world-countries/flags/64x64/" + fileName;
            InputStream flag = Country.class.getClassLoader().getResourceAsStream(resource);

            return flag;
        }
        return null;
    }
}
