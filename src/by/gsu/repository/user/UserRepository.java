package by.gsu.repository.user;

import by.gsu.model.User;

import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
public interface UserRepository {
    void create(User user);
    void update(User user);
    void delete(long id);

    User getUserById(long id);
    List<User> findAll();
}
