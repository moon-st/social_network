package by.gsu.repository.city;

import by.gsu.exption.RepositoryException;
import by.gsu.model.City;
import by.gsu.repository.ConnectionManager;
import by.gsu.repository.ConnectionManagerImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        cnManager = new ConnectionManagerImpl();
        cityRSParser = new CityResultSetParserImpl();
    }

    @Override
    public List<City> findAll() {
        Connection conn = cnManager.getConnection();

        try (Statement st = conn.createStatement()) {

            ResultSet resultSet = st.executeQuery(FIND_ALL);

            List<City> result = cityRSParser.parse(resultSet);
            resultSet.close();

            return result;
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public City findById(long id) {
        Connection conn = cnManager.getConnection();

        try (Statement st = conn.createStatement()) {

            ResultSet resultSet = st.executeQuery(String.format(FIND_BY_ID, id));

            List<City> result = cityRSParser.parse(resultSet);
            resultSet.close();

            return result.get(0);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}
