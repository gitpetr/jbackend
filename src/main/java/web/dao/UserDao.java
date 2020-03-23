package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void saveUser(User user);

    User getUser(long id);

    void deleteUser(long id);

    User findByEmail(String email);

    User findByUsername(String username);
}
