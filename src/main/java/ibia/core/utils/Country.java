package ibia.core.utils;

/*
 * Utility class for dealing with country names and flags.
 * All methods are static.
 * All data is obtained from the repository at: https://github.com/stefangabos/world_countries
 */
public class Country {

    /*
     * Returns list of the full names for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
     */
    public static String[] listbyName() {
        return new String[0];
    }

    /*
     * Returns list of the alpha-2 codes for all 249 territories
     * that have an officially assigned ISO 3166-1 code.
     */
    public static String[] listByCode() {
        return new String[0];
    }

    /*
     * Return the full name for a territory,
     * given its alpha-2 code.
     */
    public static String codeToName(String code) {
        return "";
    }

    /*
     * Returns the alpha-2 code for a territory,
     * given its full name.
     */
    public static String nameToCode(String name) {
        return "";
    }

    /*
     * Returns the (relative) path to a .png file for
     * the flag of a territory, given its alpha-2 code.
     */
    public static String flagPath(String code) {
        return "";
    }
}
