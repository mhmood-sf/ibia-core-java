package ibia.core.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import ibia.core.entities.Entity;

import java.util.Date;

/*
 * Utility class for dealing with IDs used
 * for entities throughout the application.
 */
public class Id implements IdentifierGenerator {

    /*
     * Custom ID generator for Entities. This method
     * is only for use by hibernate. If, for some reason,
     * you need to generate IDs for an Entity, see the
     * static Id.generate method.
     */
    public String generate(SharedSessionContractImplementor ssci, Object obj) {
        Entity entity = (Entity)obj;
        return Id.generate(entity.getType());
    }

    /*
     * Generates an ID based on the provided type,
     * used as a prefix for the ID.
     * type may be one of the following:
     * "COM", for instances of a Committee
     * "CON", for instances of a Conference
     * "DEL", for instances of a Delegate.
     * 
     * If an invalid type is provided, "ENT"
     * is used as a prefix.
     */
    public static String generate(String type) {
        String ts = Long.toString(System.currentTimeMillis());
        switch (type) {
            case "COM":
                return "COM" + ts;
            case "CON":
                return "CON" + ts;
            case "DEL":
                return "DEL" + ts;
            default:
                return "ENT" + ts;
        }
    }

    /*
     * Obtain the creation Date from a given ID.
     */
    public static Date createdAt(String id) throws Exception {
        if (verify(id)) {
            Long ts = Long.parseLong(id.substring(3));
            return new Date(ts);
        } else {
            throw new Exception("Invalid ID provided.");
        }
    }

    /*
     * Verify IDs.
     * Verification is done by checking the prefix
     * of the id, the timestamp, and the date it was
     * generated. Only ids generated between
     * January 1, 2020 and the time this method is called
     * are considered valid.
     */
    public static Boolean verify(String id) {
        String prefix = id.substring(0, 3);
        String ts = id.substring(3);

        try {
            // Make sure ID was created between NOW and January 1, 2020
            // otherwise its obviously not a properly generated ID.
            Date created = new Date(Long.parseLong(ts));

            Date current = new Date(System.currentTimeMillis());
            Date epoch = new Date(1577836800000L); // January 1, 2020

            if (current.compareTo(created) <= 0) return false;
            if (epoch.compareTo(created) >= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return prefix.equals("COM")
            || prefix.equals("CON")
            || prefix.equals("DEL")
            || prefix.equals("ENT");
    }
}
