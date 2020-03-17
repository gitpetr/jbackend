package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("FROM " + User.class.getName(), User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        final User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User findByEmail(String email) {
        final TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :email", User.class);
        query.setParameter("email", email);
        final List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User findByUsername(String username) {

        final TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        final List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
