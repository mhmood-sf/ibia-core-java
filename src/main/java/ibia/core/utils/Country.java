package ibia.core.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.ArrayList;

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

    private static class CountryData {
        public int id;
        public String name;
        public String alpha2;
        public String alpha3;
    }

    // TODO: proper, graceful error handling.
    public static CountryData[] getData() {
        if (data == null) {
            try {
                URL resource = Country.class.getClassLoader().getResource(dataPath);
                File file = new File(resource.getFile());
                byte[] raw = Files.readAllBytes(file.toPath());
                String json = new String(raw);

                data = new Gson().fromJson(json, CountryData[].class);
            } catch (Exception e) {
                System.err.println(e);
                return null;
            }
        }
        return data;
    }

    /**
     * Returns list of the full names for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
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
     * Returns list of the alpha-2 codes for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
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
     * Return the full name for a territory,
     * given its alpha-2 code.
     */
    public static String codeFromName(String name) {
        if (getData() == null) return null;
        for (CountryData country : getData()) {
            if (country.name.equals(name)) return country.alpha2;
        }
        return null;
    }

    /**
     * Returns the alpha-2 code for a territory,
     * given its full name.
     */
    public static String nameFromCode(String code) {
        if (getData() == null) return null;
        for (CountryData country : getData()) {
            if (country.alpha2.equals(code)) return country.name;
        }
        return null;
    }

    /**
     * Returns the (relative) path to a .png file for
     * the flag of a territory, given its alpha-2 code.
     */
    public static Path flagPath(String code) {
        String fileName = code.toLowerCase() + ".png";
        return Paths.get("world-countries", "flags", "64x64", fileName);
    }
}
