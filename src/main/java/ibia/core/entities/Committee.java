package ibia.core.entities;

import ibia.core.utils.Id;

/*
 * Represents a committee within a(n) MUN conference.
 */
public class Committee implements Entity {
    public final String type = "COM";
    private String id;
    private String name;

    public Committee() {}

    public Committee(String name) throws Exception {
        this.id = Id.generate("com");
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
