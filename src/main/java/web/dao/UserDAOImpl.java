package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<User> userList() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}
	
	@Override
	public User getUser(long id) {
		return entityManager.find(User.class, id);
	}
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}
	
	@Override
	public void removeUser(long id) {
		entityManager.remove(getUser(id));
	}
}
