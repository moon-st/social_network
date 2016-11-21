package by.gsu.repository.user;

import by.gsu.model.User;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Vasili on 22.11.2016.
 */
public interface UserResultSetParser {
    List<User> parse(ResultSet rs);
}
