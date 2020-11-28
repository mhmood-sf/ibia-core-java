package ibia.core.utils;

import java.util.Date;

import ibia.core.entities.EntityType;

/**
 * Utility class for dealing with IDs used
 * for entities throughout the application.
 */
public class Id {

    /**
     * Generates an ID based on the provided type,
     * used as a prefix for the ID.
     * 
     * @param type - The EntityType for which to generate an ID.
     * @return An ID string
     */
    public static String generate(EntityType type) {
        String ts = Long.toString(System.currentTimeMillis());
        switch (type) {
            case COM:
                return "COM" + ts;
            case CON:
                return "CON" + ts;
            case DEL:
                return "DEL" + ts;
            default:
                return "ENT" + ts;
        }
    }

    /**
     * Obtain the creation Date from a given ID.
     * 
     * @param id A valid ID string
     * @return The Date when the ID was generated.
     * @throws IllegalArgumentException - if an invalid ID is provided
     */
    public static Date createdAt(String id) throws IllegalArgumentException {
        if (verify(id)) {
            Long ts = Long.parseLong(id.substring(3));
            return new Date(ts);
        } else {
            throw new IllegalArgumentException("Invalid ID provided.");
        }
    }

    /**
     * Verifies IDs based on the following checks:<br><br>
     * 
     * - The prefix of the id is one of COM, CON, DEL or ENT.<br><br>
     * - The suffix can be parsed into a valid Date object.<br><br>
     * - The parsed Date is between January 1, 2020 and the current time.<br><br>
     * 
     * @param id - The ID string to verify.
     * @return true if all the checks passed, otherwise false.
     */
    public static boolean verify(String id) {
        String prefix = id.substring(0, 3);
        String ts = id.substring(3);

        try {
            // Make sure ID was created between NOW and January 1, 2020
            // otherwise its obviously not a properly generated ID.
            Date created = new Date(Long.parseLong(ts));

            Date current = new Date(System.currentTimeMillis());
            Date epoch = new Date(1577836800000L); // January 1, 2020

            if (current.compareTo(created) < 0) return false;
            if (epoch.compareTo(created) > 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return prefix.equals("COM")
            || prefix.equals("CON")
            || prefix.equals("DEL")
            || prefix.equals("ENT");
    }
}
