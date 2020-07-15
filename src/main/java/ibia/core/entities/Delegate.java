package ibia.core.entities;

import ibia.core.utils.Id;

/*
 * Represents a delegate within a(n) MUN committee.
 */
public class Delegate implements Entity {
    private final String type = "DEL";
    private String id;
    private String name;
    private String committee;

    public Delegate() {}

    public Delegate(String name, String committee) throws Exception {
        this.id = Id.generate("del");
        this.name = name;
        this.committee = committee;
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

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }
}
