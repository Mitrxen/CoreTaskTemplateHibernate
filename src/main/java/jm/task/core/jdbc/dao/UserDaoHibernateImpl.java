package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
	private SessionFactory sessionFactory;
	
    public UserDaoHibernateImpl() {
    	try {
			this.sessionFactory = Util.getSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Override
    public void createUsersTable() {
    	try {
    		Session session = sessionFactory.openSession();
    		session.getTransaction().begin();
	    	String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, "
					+ "name VARCHAR(45) NOT NULL,  "
					+ "lastName VARCHAR(45) NOT NULL, "
					+ "age INTEGER(3) UNSIGNED NOT NULL,  "
					+ "PRIMARY KEY (id))";
	    	session.createSQLQuery(sql).executeUpdate();
	    	session.getTransaction().commit();
	    	session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public void dropUsersTable() {
    	try {
    		Session session = sessionFactory.openSession();
	    	session.getTransaction().begin();
	    	String sql = "DROP TABLE IF EXISTS users";
	    	session.createSQLQuery(sql).executeUpdate();
	    	session.getTransaction().commit();
	    	session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
    	try {
    		Session session = sessionFactory.openSession();
    		session.getTransaction().begin();
	    	session.persist(new User(name, lastName, age));
	    	session.getTransaction().commit();
	    	session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public void removeUserById(long id) {
    	try {
    		Session session = sessionFactory.openSession();
	    	session.getTransaction().begin();
	    	
	    	session.delete(session.get(User.class, id));
	    	
	    	session.getTransaction().commit();
	    	session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }

    @Override
    public List<User> getAllUsers() {
    	List<User> users = null;
    	try {
    		Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			users = session.createQuery("FROM User").list();
			session.getTransaction().commit();
			session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
        return users;
    }

    @Override
    public void cleanUsersTable() {
    	try {
    		Session session = sessionFactory.openSession();
	    	session.getTransaction().begin();
			session.createQuery("DELETE FROM User").executeUpdate();
			session.getTransaction().commit();
			session.close();
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
