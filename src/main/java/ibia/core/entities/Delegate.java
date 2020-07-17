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
    private String delegation; // An alpha2 country code OR a custom delegation. The country code is used to fetch the flag icon.

    public Delegate() {}

    public Delegate(String name, String delegation, String committee) {
        this.id = Id.generate("del");
        this.name = name;
        this.committee = committee;
        this.delegation = delegation;
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

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }
}
