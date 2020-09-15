package ibia.core.entities;

import ibia.core.utils.Country;
import ibia.core.utils.Id;

/**
 * Represents a delegate within a(n) MUN committee.
 */
public class Delegate implements Entity {
    private final EntityType type = EntityType.DEL;
    private String id;
    private String name;
    private String delegation; // An alpha2 country code OR a custom delegation. The country code is used to fetch the flag icon.
    private String committeeId;

    /**
     * This constructor is used internally by Hibernate
     * and MUST NOT be used in client-facing code.
     */
    public Delegate() {}

    public Delegate(String name, String delegation, String committeeId) {
        this.id = Id.generate(type);
        this.name = name;
        this.delegation = delegation;
        this.committeeId = committeeId;
    }

    public EntityType getType() {
        return type;
    }

    /**
     * Check whether the delegate has a custom delegation
     * or not. A delegation is considered custom if and
     * only if its value is a valid alpha2 territory code.
     * This check should be used to determine whether or
     * not a flag icon is available for a delegation.
     * 
     * @return true if the delegation is custom, otherwise false.
     */
    public boolean hasCustomDelegation() {
        return !Country.listOfCodes().contains(delegation);
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

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }
}
