package by.gsu.repository.city;

import by.gsu.model.City;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 01.12.16.
 */
@Component("cityRepo2")
public class CityRepositoryImpl2 implements CityRepository {

    @Override
    public List<City> findAll() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:social_network.db");

            List<City> result = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM City");

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");

                City city = new City(id, name);
                result.add(city);
            }

            rs.close();
            statement.close();
            connection.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public City findById(long id) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:social_network.db");

            City result = null;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM City WHERE id = " + id);

            while (rs.next()) {
                String name = rs.getString("name");

                result = new City(id, name);
                break;
            }

            rs.close();
            statement.close();
            connection.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
