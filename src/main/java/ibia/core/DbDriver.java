package ibia.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Handles all database-related operations.
 * All methods are static.
 * Uses an embedded H2 database under the hood,
 * through the Hibernate ORM.
 */
public class DbDriver {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }

                throw e;
            }
        }
        return sessionFactory;
    }

    private static Session openSession() {
        return getSessionFactory().openSession();
    }

    /**
     * Persist an entity to the database.
     * 
     * @param T - The type of Entity to be persisted.
     * @param entity - The entity to be persisted.
     */
    public static <T> void insertOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Persist a collection of entities
     * to the database.
     * 
     * @param T - The type of Entity to be persisted.
     * @param entities - The collection of entities to be persisted.
     */
    public static <T> void insertAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Update a persisted entity.
     * 
     * @param T - The type of Entity to be updated.
     * @param entity - The updated entity.
     */
    public static <T> void updateOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Update a collection of persisted entities.
     * 
     * @param T - The type of Entity to be updated.
     * @param entities - The collection of updated entities.
     */
    public static <T> void updateAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete an entity from the database.
     * 
     * @param T - The type of Entity to be deleted.
     * @param entity - The entity to be deleted.
     */
    public static <T> void deleteOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete an entity from the database
     * using the id
     * @param <T> type of entity to be deleted
     * @param entityClass
     * @param id
     */
    public static <T> void deleteById(Class<T> entityClass, Object id) {
        T entity = DbDriver.fetchOne(entityClass, id);
        deleteOne(entity);
    }

    /**
     * Delete a collection of entities from the database.
     * 
     * @param T - The type of Entity to be deleted.
     * @param entities - The collection of entities to be deleted.
     */
    public static <T> void deleteAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Fetch/read a persisted entity from the database.
     * 
     * @param T - The type of Entity to be fetched.
     * @param entityclass - The Class of the entity to be fetched.
     * @param id - The ID of the entity being fetched.
     * @return The fetched entity, or null if the entity does not exist.
     */
    public static <T> T fetchOne(Class<T> entityClass, Object id) {
        Session session = openSession();
        session.beginTransaction();
        T entity = (T)session.find(entityClass, id);
        session.close();
        return entity;
    }

    /**
     * Fetch/read all persisted entities of a particular
     * type from the database.
     * 
     * @param T - The type of entity to be fetched.
     * @param entityClass - The Class of the entities being fetched.
     * @return List of fetched entities, or null if none were found.
     */
    public static <T> ArrayList<T> fetchAll(Class<T> entityClass) {
        Session session = openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = cq.from(entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(all);
        ArrayList<T> results = (ArrayList<T>)allQuery.getResultList();
        session.close();
        return results.size() > 0 ? results : null;
    }

    /**
     * Fetches all entities of a particular type and
     * finds the first one satisfying a given predicate.
     * <br><br>
     * For example, to find a delegate with the name "ABC":
     * <pre>
     * Delegate abc = DbDriver.findOne(Delegate.class,
     *     del -> del.getName().equals("ABC"));
     * </pre>
     * 
     * @param <T> - The type of entity to be fetched.
     * @param entityClass - The Class of the entities being fetched.
     * @param filter - The predicate for searching through the fetched entities
     * @return The first entity found to satisfy the predicate, or null if none were found.
     */
    public static <T> T findOne(Class<T> entityClass, Predicate<T> filter) {
        ArrayList<T> entities = fetchAll(entityClass);
        if (entities != null) {
            for (T entity : entities) {
                if (filter.test(entity)) return entity;
            }
        }
        return null;
    }

    /**
     * Fetches all entities of a particular type and
     * finds all that satisfy a given predicate.
     * <br><br>
     * For example, to find all Delegates representing Antarctica:
     * <pre>
     * String code = Country.codeFromName("Antarctica");
     * ArrayList<Delegate> antarcticaDelegates = 
     *     DbDriver.findAll(Delegate.class,
     *         del -> del.getDelegation().equals(code)
     *     );
     * </pre>
     * 
     * @param T - The type of entity to be fetched.
     * @param entityClass - The Class of the entities being fetched
     * @param filter - The predicate for searching through the entities.
     * @return List of entities found to satisfy the predicate, or null if none were found.
     */
    public static <T> ArrayList<T> findAll(Class<T> entityClass, Predicate<T> filter) {
        ArrayList<T> found = new ArrayList<>();
        ArrayList<T> entities = fetchAll(entityClass);
        if (entities != null) {
            for (T entity : entities) {
                if (filter.test(entity)) found.add(entity);
            }
        }
        return found.size() > 0 ? found : null;
    }

    /**
     * Destroy database service registry.
     */
    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
