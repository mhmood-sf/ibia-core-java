package ibia.core.entities;

/**
 * Enum mapping over the three possible
 * types of entities used throughout 
 * ibia:
 * <ul>
 *   <li>COM: Committees</li>
 *   <li>CON: Conferenes</li>
 *   <li>DEL: Delegates</li>
 *   <li>ENT: Any generic entity that implements the Entity interface</li>
 * </ul>
 */
public enum EntityType {
    COM,
    CON,
    DEL,
    ENT
}
