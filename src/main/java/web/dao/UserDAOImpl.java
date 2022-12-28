package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
	public User getUser(Long id) {
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
	public void removeUser(Long id) {
		entityManager.remove(getUser(id));
	}
}
