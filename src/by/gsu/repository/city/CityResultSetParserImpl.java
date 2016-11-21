package by.gsu.repository.city;

import by.gsu.exption.RepositoryException;
import by.gsu.model.City;
import by.gsu.repository.city.CityResultSetParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
public class CityResultSetParserImpl implements CityResultSetParser {
    @Override
    public List<City> parse(ResultSet rs) {
        try {
            List<City> result = new ArrayList<>();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");

                City city = new City(id, name);
                result.add(city);
            }

            return result;

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
