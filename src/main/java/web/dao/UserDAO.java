package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
	
	List<User> userList();
	
	User getUser(Long id);
	void addUser(User user);
	
	void updateUser(User user);
	
	void removeUser(Long id);
}