package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import jm.task.core.jdbc.model.User;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456";
    
    public  static Connection getConnect() {
    	return getConnect(URL, NAME, PASSWORD);
    }
    
    public static Connection getConnect(String url, String user, String pass) {
    	Connection conn = null;
    	
    	try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	return conn;
    }
    
    public static SessionFactory getSessionFactory() throws Exception{
    	Configuration configuration = new Configuration();
		Properties settings = new Properties();
		settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		settings.put(Environment.URL, URL + "?useSSL=false&serverTimezone=Asia/Yekaterinburg");
		settings.put(Environment.USER, NAME);
		settings.put(Environment.PASS, PASSWORD);
		settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		settings.put(Environment.HBM2DDL_AUTO, "update");
		
		configuration.setProperties(settings);
		configuration.addAnnotatedClass(User.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
				
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		return factory;
    }

}
