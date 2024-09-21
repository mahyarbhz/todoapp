package todoapp;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {
    SessionFactory sessionFactory;
    Session session;

    /**
     * Sets up the test environment by creating a new SessionFactory and Session.
     * Loads the Hibernate configuration from "test-hibernate.cfg.xml" and adds the Todo class to the mapping.
     */
    @BeforeEach
    public void setUp() {
        Configuration cfg = new Configuration().configure("test-hibernate.cfg.xml");
        cfg.addAnnotatedClass(Todo.class);

        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
    }

    /**
     * Tears down the test environment by closing the Session and SessionFactory.
     */
    @AfterEach
    public void tearDown() {
        session.close();
        sessionFactory.close();
    }

    /**
     * This test verifies that the createMultipleTodos method correctly persists some new Todo objects to the database.
     * This test verifies that we can correctly persist some new Todo objects to the database.
     */
    @Test
    public void testCreateMultipleTodos() throws Exception {
        Todo todo1 = new Todo(1, "Todo 1", '0', false);
        Todo todo2 = new Todo(2, "Todo 2", '1', true);

        try {
            session.beginTransaction();

            session.persist("Todo", todo1);
            session.persist("Todo", todo2);

            session.getTransaction().commit();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);
            Root<Todo> todoRoot = cq.from(Todo.class);

            Predicate predicate1 = cb.equal(todoRoot.get("todo"), todo1.getTodo());
            Predicate predicate2 = cb.equal(todoRoot.get("todo"), todo2.getTodo());
            Predicate predicate = cb.or(predicate1, predicate2);

            cq.where(predicate);

            List<Todo> results = session.createQuery(cq).getResultList();

            assertEquals(2, results.size());
            assertTrue(results.contains(todo1));
            assertTrue(results.contains(todo2));
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            throw new Exception(e);
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
