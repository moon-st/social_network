package by.gsu.repository.user;

import by.gsu.exption.RepositoryException;
import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import by.gsu.util.Profiling;
import by.gsu.util.context.ComponentFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasili on 22.11.2016.
 */
@Profiling
public class UserResultSetParserImpl implements UserResultSetParser {

    private CityRepository cityRepository;

    public UserResultSetParserImpl() {
        cityRepository = ComponentFactory.createComponent(CityRepository.class);
    }

    @Override
    public List<User> parse(ResultSet rs) {
        try {
            List<User> result = new ArrayList<>();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                long cityId = rs.getLong("city_id");

                City city = cityRepository.findById(cityId);

                result.add(new User(id, name, city));
            }

            return result;
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }


}
