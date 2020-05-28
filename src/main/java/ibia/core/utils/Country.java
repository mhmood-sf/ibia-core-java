package ibia.core.utils;

// TODO: Instead of hardcoding countries like this, pull the data from a remote source
// like this: https://github.com/stefangabos/world_countries/blob/master/data/en/world.json
// and maybe have a packaged version of it in the jar, and then for updating it, pull the data from
// the repo when an internet connection is available. That, or have a button to let the user choose to update it.
// In this file, add a search function for finding a country from the world.json file, and return its data
// create a function searchCountry() that returns a Country instance.

// country icons, names, codes, etc.
public class Country {
    // all countries by full name
    public static String[] listbyName() {
        return new String[0];
    }

    // all countries by alpha-2 ISO codes
    public static String[] listByCode() {
        return new String[0];
    }

/*
    // all countries by alpha-3 ISO codes
    public static String[] listByAlpha3() {
        return new String[0];
    }
*/

    // get full name from alpha-2 code
    public static String codeToName(String code) {
        return "";
    }

    // get alpha-2 code from name
    public static String nameToCode(String name) {
        return "";
    }

    // get path to country flag from alpha-2 code
    // the flag should work with the FXMLLoader in Util.
    public static String flagPath(String code) {
        return "";
    }
}
