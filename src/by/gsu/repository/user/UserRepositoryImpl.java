package by.gsu.repository.user;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vasili on 22.11.2016.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<User> userRowMapper;

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

    @Component
    public static class UserRowMapperImpl implements RowMapper<User> {

        @Autowired
        private CityRepository cityRepository;

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long cityId = resultSet.getLong("city_id");

            City city = cityRepository.findById(cityId);

            return new User(id, name, city);
        }
    }
}
