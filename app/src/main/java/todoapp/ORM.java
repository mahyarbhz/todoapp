package todoapp;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

/**
 * Provides Hibernate setup and creates a session for added convenience; using the Singleton design pattern.
 * @see <a href="https://refactoring.guru/design-patterns/singleton/">Singleton pattern</a>
 *
 * @author Mahyar Behzadi, mahyarbhz@gmail.com
 */
public class ORM {
    private static ORM instance;
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;

    /**
     * Returns the single instance of the ORM class.
     *
     * @return the single instance of the ORM class
     */
    public static ORM getInstance() {
        if (instance == null) {
            instance = new ORM();
        }
        return instance;
    }

    /**
     * The constructor class of ORM object.
     *
     */
    public ORM() {
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Todo.class);
    }

    /**
     * Returns the created session for doing transactions.
     *
     * @return Session
     */
    public Session getSession() {
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    /**
     * Closes session and sessionFactory.
     *
     */
    public void closeSession() {
        session.close();
        sessionFactory.close();
    }
}
