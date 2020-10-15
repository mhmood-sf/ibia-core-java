package ibia.core.entities;

/**
 * An interface representing any generic entity
 * that posesses a certain type and ID and represents
 * an aspect of a(n) MUN conference. NOTE that these
 * entities do NOT refer to Hibernate entities.
 */
public interface Entity {
    public EntityType getType();

    /* GETTERS and SETTERS used by hibernate */
    public String getId();
    public void setId(String id);

    public String getName();
    public void setName(String name);
}
