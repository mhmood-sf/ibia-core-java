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
    private boolean status;

    public Conference() {}

    public Conference(String name, int size) {
        this.id = Id.generate("con");
        this.name = name;
        this.size = size;
        this.status = true;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
