package by.gsu.repository.city;

import by.gsu.model.City;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
public interface CityResultSetParser {
    List<City> parse(ResultSet rs);
}
