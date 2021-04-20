package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
	private Connection conn;
	
    public UserDaoJDBCImpl() {
    	this.conn = Util.getConnect();
    }

    public void createUsersTable() {
    	String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, "
    									+ "name VARCHAR(45) NOT NULL,  "
    									+ "lastName VARCHAR(45) NOT NULL, "
    									+ "age INTEGER(3) UNSIGNED NOT NULL,  "
    									+ "PRIMARY KEY (id))";
    	
    	try {
			PreparedStatement stat = this.conn.prepareStatement(sql);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void dropUsersTable() {
    	String sql = "DROP TABLE IF EXISTS users";
    	
    	try {
			PreparedStatement stat = this.conn.prepareStatement(sql);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

    public void saveUser(String name, String lastName, byte age) {
    	String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    	try {
			PreparedStatement stat = this.conn.prepareStatement(sql);
			stat.setString(1, name);
			stat.setString(2, lastName);
			stat.setByte(3, age);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void removeUserById(long id) {
    	String sql = "DELETE FROM users WHERE id=?";
    	try {
			PreparedStatement stat = this.conn.prepareStatement(sql);
			stat.setLong(1, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

    public List<User> getAllUsers() {
    	List<User> listOfUsers = new LinkedList<User>();
    	try {
    		String sql = "SELECT * FROM users";
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet resultSet = stat.executeQuery();
			
			while(resultSet.next()) {
				listOfUsers.add(new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return listOfUsers;
    }

    public void cleanUsersTable() {
    	String sql = "DELETE FROM users";
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
