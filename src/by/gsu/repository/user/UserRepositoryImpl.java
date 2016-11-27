package by.gsu.repository.user;

import by.gsu.model.User;
import by.gsu.repository.ConnectionManager;
import by.gsu.util.context.ComponentFactory;

import java.util.List;

/**
 * Created by Vasili on 22.11.2016.
 */
public class UserRepositoryImpl implements UserRepository {

    private ConnectionManager cnManager;
    private UserResultSetParser userRSParser;

    private static class Queries {
        private static final String FIND_ALL = "SELECT * FROM USER";
        private static final String FIND_BY_ID = "SELECT * FROM USER WHERE id = %d";
        private static final String DELETE = "DELETE FROM User WHERE id = %d";
        private static final String CREATE = "INSERT INTO User('name', 'city_id') values('%s', %d)";
        private static final String UPDATE = "UPDATE User SET 'name' = '%s', 'city_id' = %d WHERE id = %d";

    }

    public UserRepositoryImpl() {
        cnManager = ComponentFactory.createComponent(ConnectionManager.class);
        userRSParser = ComponentFactory.createComponent(UserResultSetParser.class);
    }

    @Override
    public void create(User user) {
        String query = String.format(Queries.CREATE, user.getName(), user.getCity().getId());
        cnManager.executeQuery(query);
    }

    @Override
    public void update(User user) {
        String query = String.format(Queries.UPDATE, user.getName(), user.getCity().getId(), user.getId());
        cnManager.executeQuery(query);
    }

    @Override
    public void delete(long id) {
        String query = String.format(Queries.DELETE, id);
        cnManager.executeQuery(query);
    }

    @Override
    public User getUserById(long id) {
        String query = String.format(Queries.FIND_BY_ID, id);
        List<User> res = cnManager.executeQuery(query, rs -> userRSParser.parse(rs));
        return res.get(0);
    }

    @Override
    public List<User> findAll() {
        return cnManager.executeQuery(Queries.FIND_ALL, rs -> userRSParser.parse(rs));
    }
}
