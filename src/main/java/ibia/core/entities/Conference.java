package ibia.core.entities;

import ibia.core.utils.Id;

/*
 * Represents a(n) MUN conference.
 */
public class Conference implements Entity {
    private final String type = "CON";
    private String id;
    private String name;
    private int size;

    public Conference() {}

    public Conference(String name, int size) throws Exception {
        this.id = Id.generate("con");
        this.name = name;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
