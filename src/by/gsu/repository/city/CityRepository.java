package by.gsu.repository.city;

import by.gsu.model.City;

import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
public interface CityRepository {
    List<City> findAll();

    City findById(long id);
}
