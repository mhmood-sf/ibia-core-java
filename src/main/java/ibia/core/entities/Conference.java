package ibia.core.entities;

import ibia.core.utils.Id;

/**
 * Represents a(n) MUN conference.
 */
public class Conference implements Entity {
    private final EntityType type = EntityType.CON;
    private String id;
    private String name;
    private int size;
    private boolean status;

    /**
     * This constructor is used internally by Hibernate
     * and MUST NOT be used in client-facing code.
     */
    public Conference() {}

    public Conference(String name) {
        this.id = Id.generate(type);
        this.name = name;
        this.size = 0;
        this.status = true;
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
