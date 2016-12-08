package by.gsu.repository.city;

import by.gsu.model.City;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author vasiliy.pispanen.
 */
@Component
public class CityRowMapperImpl implements CityRowMapper {
    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();
        city.setId(resultSet.getLong("id"));
        city.setName(resultSet.getString("name"));
        return city;
    }
}
