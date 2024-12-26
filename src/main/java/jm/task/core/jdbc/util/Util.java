package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class Util {
//   private static final String URL = "jdbc:postgresql://localhost:5432/userservicetest";
//    private static final String USER = "postgres";
//    private static final String PASS = "0000";
//
//    public static Connection open() {
//        try {
//            return DriverManager.getConnection(URL, USER, PASS);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Util() {
//    }
//}
public class Util {
    public Util() {
    }

    private static SessionFactory sessionFactory;

//    static {
//        try {
//            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
//            sessionFactory = configuration.buildSessionFactory();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ExceptionInInitializerError("Ошибка инициализации SessionFactory: " + e.getMessage());
//        }
//    }
// Создаем объект конфигурации Hibernate

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/HiberneteTest");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.current_session_context_class", "thread");

            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("SessionFactory initialization failed!" + e);
        }
    }

        public static SessionFactory getSessionFactory () {
            return sessionFactory;
        }

    }
