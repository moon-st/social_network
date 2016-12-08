package by.gsu.repository.user;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vasiliy.pispanen.
 */
@Component
public class UserRowMapperImpl implements UserRowMapper {

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
