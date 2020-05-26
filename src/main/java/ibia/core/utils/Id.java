package ibia.core.utils;

import java.util.Date;

public class Id {
    public static String generate(String type) throws Exception {
        String ts = Long.toString(System.currentTimeMillis());
        switch (type.toLowerCase()) {
            case "committee":
            case "com":
                return "COM" + ts;
            case "conference":
            case "con":
                return "CON" + ts;
            case "delegate":
            case "del":
                return "DEL" + ts;
            case "entity":
            case "ent":
                return "ENT" + ts;
            default:
                throw new Exception("Invalid ID type provided.");
        }
    }

    public static Date createdAt(String id) throws Exception {
        if (verify(id)) {
            Long ts = Long.parseLong(id.substring(3));
            return new Date(ts);
        } else {
            throw new Exception("Invalid ID provided.");
        }
    }

    public static Boolean verify(String id) {
        // handle verification
        return true;
    }
}
