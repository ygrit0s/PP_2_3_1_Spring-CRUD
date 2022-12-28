package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import web.dao.UserDAO;
import web.model.User;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	public static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
	
	private final UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public List<User> userList() {
		var result = userDAO.userList();
		result.forEach(user -> LOGGER.info(user.toString() + "->"
				+ user.toString().indent(8)));
		return result;
	}
	
	@Override
	public User getUser(Long id) {
		LOGGER.log(Level.INFO, "User под номером {0} был успешно извлечён%n", new Object[]{id});
		return userDAO.getUser(id);
	}
	
	@Override
	public void addUser(@Valid User user) {
		LOGGER.log(Level.INFO, "User {0} добавлен в базу данных%n", new Object[]{user});
		userDAO.addUser(user);
	}
	
	@Override
	public void updateUser(@Valid User user) {
		LOGGER.log(Level.INFO, "User под текущим номером был изменён на {0}%n", new Object[]{user});
		userDAO.updateUser(user);
	}
	
	@Override
	public void removeUser(Long id) {
		LOGGER.log(Level.INFO, "User под номером {0} удалён из базы данных%n", new Object[]{id});
		userDAO.removeUser(id);
	}
}
