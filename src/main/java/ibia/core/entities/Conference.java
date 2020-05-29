package ibia.core.entities;

import ibia.core.utils.Id;

/*
 * Represents a(n) MUN conference.
 */
public class Conference implements Entity {
    public final String type = "CON";
    private String id;
    private String name;

    public Conference() {}

    public Conference(String name) throws Exception {
        this.id = Id.generate("con");
        this.name = name;
    }

    public String getType() {
        return type;
    }

    /* GETTERS and SETTERS used by hibernate */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
