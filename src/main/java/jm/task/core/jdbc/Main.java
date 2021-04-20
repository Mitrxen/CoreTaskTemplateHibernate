package jm.task.core.jdbc;

import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
	public static void main(String[] args) {
    	UserService userService = new UserServiceImpl();
        
    	userService.createUsersTable();
    	
    	userService.saveUser("Karl", "Marx", (byte)49);
    	userService.saveUser("Vladimir", "Ulyanov", (byte)47);
    	userService.saveUser("Ioseb", "Jughashvili", (byte)39);
    	userService.saveUser("Felix", "Dzerzhinsky", (byte)40);
    	
    	List<User> allUsersFromDB = userService.getAllUsers();
    	for(User user : allUsersFromDB) {
    		System.out.println(user);
    	}
    	
    	userService.cleanUsersTable();
    	userService.dropUsersTable();
    }
}
