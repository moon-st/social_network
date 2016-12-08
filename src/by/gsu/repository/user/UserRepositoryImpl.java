package by.gsu.repository.user;

import by.gsu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vasili on 22.11.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;

    private static class Queries {
        private static final String FIND_ALL = "SELECT * FROM USER";
        private static final String FIND_BY_ID = "SELECT * FROM USER WHERE id = ?";
        private static final String DELETE = "DELETE FROM User WHERE id = ?";
        private static final String CREATE = "INSERT INTO User('name', 'city_id') values(?, ?)";
        private static final String UPDATE = "UPDATE User SET 'name' = ?, 'city_id' = ? WHERE id = ?";

    }

    @Override
    public void create(User user) {
        jdbcTemplate.update(Queries.CREATE, user.getName(), user.getCity().getId());

    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(Queries.UPDATE, user.getName(), user.getCity().getId(), user.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(Queries.DELETE, id);
    }

    @Override
    public User findById(long id) {
        return jdbcTemplate.queryForObject(Queries.FIND_BY_ID, new Object[]{id}, userRowMapper);

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(Queries.FIND_ALL, userRowMapper);
    }
}
