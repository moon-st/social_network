package by.gsu.repository.city;

import by.gsu.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 01.12.16.
 */
public class CityrepositoryImpl3 implements CityRepository {

    @Override
    public List<City> findAll()  {
        return executeQuery("SELECT * FROM City", new ResultSetProcessor<List<City>>() {
            @Override
            public List<City> process(ResultSet rs) {
                try {
                    return parse(rs);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public City findById(long id){
        return executeQuery("SELECT * FROM City WHERE id = " + id, new ResultSetProcessor<City>() {
            @Override
            public City process(ResultSet rs) {
                try {
                    return parse(rs).get(0);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private <T> T executeQuery(String query, ResultSetProcessor<T> resultSetProcessor)  {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:social_network.db");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            T result = resultSetProcessor.process(rs);

            rs.close();
            statement.close();
            connection.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static interface ResultSetProcessor<T> {
        T process(ResultSet rs);
    }

    private List<City> parse(ResultSet rs) throws SQLException {
        List<City> result = new ArrayList<>();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");

            City city = new City(id, name);
            result.add(city);
        }

        return result;
    }

}
