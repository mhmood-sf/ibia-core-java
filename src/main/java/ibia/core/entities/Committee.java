package ibia.core.entities;

import ibia.core.utils.Id;

/**
 * Represents a committee within a(n) MUN conference.
 */
public class Committee implements Entity {
    private final EntityType type = EntityType.COM;
    private String id;
    private String name;
    private String conferenceId;

    /**
     * This constructor is used internally by Hibernate
     * and MUST NOT be used in client-facing code.
     */
    public Committee() {}

    public Committee(String name, String conferenceId) {
        this.id = Id.generate(type);
        this.name = name;
        this.conferenceId = conferenceId;
    }

    public EntityType getType() {
        return type;
    }

    /** GETTERS and SETTERS used by hibernate */

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

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }
}
