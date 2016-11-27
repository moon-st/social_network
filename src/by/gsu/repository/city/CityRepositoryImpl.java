package by.gsu.repository.city;

import by.gsu.model.City;
import by.gsu.repository.ConnectionManager;
import by.gsu.util.context.ComponentFactory;

import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
public class CityRepositoryImpl implements CityRepository {

    public static final String FIND_ALL = "SELECT * FROM City";
    public static final String FIND_BY_ID = "SELECT * FROM City WHERE id = %d ";

    private ConnectionManager cnManager;
    private CityResultSetParser cityRSParser;

    public CityRepositoryImpl() {
        cnManager = ComponentFactory.createComponent(ConnectionManager.class);
        cityRSParser = ComponentFactory.createComponent(CityResultSetParser.class);
    }

    @Override
    public List<City> findAll() {
       return cnManager.executeQuery(FIND_ALL, rs -> cityRSParser.parse(rs));
    }

    @Override
    public City findById(long id) {
        String query = String.format(FIND_BY_ID, id);
        List<City> list =  cnManager.executeQuery(query, rs -> cityRSParser.parse(rs));
        return list.get(0);
    }


}
