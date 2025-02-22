package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS User (" +
                    "id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY, " +
                    "name VARCHAR(100), " +
                    "lastName VARCHAR(100), " +
                    "age TINYINT)").executeUpdate();
            session.getTransaction().commit();
        }
    }

        @Override
        public void dropUsersTable () {
            try (Session session = Util.getSessionFactory().getCurrentSession()) {
                session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
                session.getTransaction().commit();
            }

        }

        @Override
        public void saveUser (String name, String lastName,byte age){
            try (Session session = Util.getSessionFactory().getCurrentSession()) {
                session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                session.getTransaction().commit();
            }

        }

        @Override
        public void removeUserById ( long id){
            try (Session session = Util.getSessionFactory().getCurrentSession()) {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
            }

        }

        @Override
        public List<User> getAllUsers () {
            try (Session session = Util.getSessionFactory().getCurrentSession()) {
                session.beginTransaction();
                List<User> users = session.createQuery("from User").getResultList();
                session.getTransaction().commit();
                return users;
            }
        }

        @Override
        public void cleanUsersTable () {
            try (Session session = Util.getSessionFactory().getCurrentSession()) {
                session.beginTransaction();
                session.createQuery("delete User").executeUpdate();
                session.getTransaction().commit();
            }

        }
    }
