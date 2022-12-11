package web.service;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
	
	List<User> userList();
	
	User getUser(long id);
	void addUser(User user);
	
	void updateUser(User user);
	
	void removeUser(long id);

}
