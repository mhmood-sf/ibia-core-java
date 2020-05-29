package ibia.core.entities;

import ibia.core.utils.Id;

/*
 * Represents a delegate within a(n) MUN committee.
 */
public class Delegate implements Entity {
    public final String type = "DEL";
    private String id;
    private String name;

    public Delegate() {}

    public Delegate(String name) throws Exception {
        this.id = Id.generate("del");
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
