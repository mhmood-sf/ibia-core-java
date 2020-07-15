package ibia.core.db;

import java.util.function.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ibia.core.entities.Entity;

/*
 * Handles all database-related operations.
 * All methods are static.
 * Uses an embedded H2 database under the hood,
 * through the Hibernate ORM.
 */
public class DbClient {
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

    // TODO: Proper transaction err handling (see docs for hover:Session)
    public static void insertOne(Entity entity) {
        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public static void insertAll(Entity[] entities) {
        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        for (Entity entity : entities) {
            session.save(entity);
        }
        session.getTransaction().commit();
        session.close();
    }

    // TODO: unimplemented
    public static void updateOne() {

    }

    public static void updateAll() {

    }

    // TODO: unimplemented
    public static void deleteOne() {

    }

    public static void deleteAll() {
        
    }

    // TODO: test this + add proper err handling
    public static <T> T findOne(Class<T> entityClass, String id) {
        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        T entity = (T)session.find(entityClass, id);
        return entity;
    }

    // TODO: unimplemented
    // fetches all rows for the given entity
    public static <T> void findAll(Class<T> entityClass) {

    }

    // TODO: unimplemented
    // fetches all rows for the given entity,
    // and filters them with the given predicate
    // use predicate like filter.test(entity)
    public static <T> void findAll(Class<T> entityClass, Predicate<T> filter) {

    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
