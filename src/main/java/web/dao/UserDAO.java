package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
	
	List<User> userList();
	
	User getUser(long id);
	void addUser(User user);
	
	
	void updateUser(User user);
	
	void removeUser(long id);
}
