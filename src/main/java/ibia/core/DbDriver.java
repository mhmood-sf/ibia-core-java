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

/*
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

    // TODO: test
    public static <T> void insertOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    // TODO: test
    public static <T> void insertAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    // TODO: test
    public static <T> void updateOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    // TODO: test
    public static <T> void updateAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    // TODO: test
    public static <T> void deleteOne(T entity) {
        Session session = openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> void deleteAll(Collection<T> entities) {
        Session session = openSession();
        session.beginTransaction();
        for (T entity : entities) session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    // TODO: test
    public static <T> T fetchOne(Class<T> entityClass, String id) {
        Session session = openSession();
        session.beginTransaction();
        T entity = (T)session.find(entityClass, id);
        return entity;
    }

    // TODO: test
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
        return results;
    }

    // TODO: test
    public static <T> T findOne(Class<T> entityClass, Predicate<T> filter) {
        ArrayList<T> entities = fetchAll(entityClass);
        for (T entity : entities) {
            if (filter.test(entity)) return entity;
        }
        return null;
    }

    // TODO: test
    public static <T> ArrayList<T> findAll(Class<T> entityClass, Predicate<T> filter) {
        ArrayList<T> found = new ArrayList<>();
        ArrayList<T> entities = fetchAll(entityClass);
        for (T entity : entities) {
            if (filter.test(entity)) found.add(entity);
        }
        return found.size() > 0 ? found : null;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
