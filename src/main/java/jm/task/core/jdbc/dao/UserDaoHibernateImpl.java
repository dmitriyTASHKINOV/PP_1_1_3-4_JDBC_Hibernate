package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl  implements UserDao {
     public static SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction =null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id BIGINT AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255),age TINYINT, PRIMARY KEY (id))")
                    .executeUpdate();
            transaction.commit();
            }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
            }
        finally {
            session.close();
        }
        }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction =null;
        try {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS User")
                .executeUpdate();
        transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            session.close();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction =null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction =null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM user WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List <User> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            list = session.createQuery("FROM User", User.class).list();
        }catch (Exception e){
                e.printStackTrace();
            }
        finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction =null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
    }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            session.close();
        }
}
    }
