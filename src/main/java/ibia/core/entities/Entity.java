package ibia.core.entities;

/**
 * An interface representing any generic entity
 * that posesses a certain type and ID and represents
 * an aspect of a(n) MUN conference.
 */
public interface Entity {
    // get the type of entity
    public EntityType getType();

    // GETTERS and SETTERS used by hibernate
    public String getId();
    public void setId(String id);

    public String getName();
    public void setName(String name);
}
