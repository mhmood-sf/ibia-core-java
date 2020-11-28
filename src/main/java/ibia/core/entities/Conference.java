package ibia.core.entities;

import java.util.Date;

import ibia.core.utils.Id;

/**
 * Represents an MUN conference.
 */
public class Conference implements Entity {
    private final EntityType type = EntityType.CON;
    private String id;
    private String name;
    private boolean ongoing;
    private Date created;

    /**
     * This constructor is used internally by Hibernate
     * and MUST NOT be used in client-facing code.
     */
    public Conference() {}

    public Conference(String name) {
        this.id = Id.generate(type);
        this.name = name;
        this.ongoing = true;
        this.created = new Date();
    }

    public EntityType getType() {
        return type;
    }

    /**
     * Whether the conference is ongoing (active) or not.
     * The default value is true when a conference is
     * instantiated.
     * 
     * @return true if the conference is active, otherwise false.
     */
    public boolean isOngoing() {
        return ongoing;
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

    public boolean getOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
