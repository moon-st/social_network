package by.gsu.repository.city;

import by.gsu.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
@Repository
public class CityRepositoryImpl implements CityRepository {

    public static final String FIND_ALL = "SELECT * FROM City";
    public static final String FIND_BY_ID = "SELECT * FROM City WHERE id = ? ";
    public static final String FIND_BY_NAME = "SELECT * FROM City WHERE name = ? ";
    public static final String CREATE = "INSERT INTO City(name) VALUES (?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<City> cityRowMapper;

    @PreDestroy
    public void writeCache() {
        System.out.println("write cache!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, cityRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public City findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {id}, cityRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public City findByName(String name) {
        return jdbcTemplate.queryForObject(FIND_BY_NAME, new Object[] {name}, cityRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void crate(City city) {
        jdbcTemplate.update(CREATE, city.getName());
    }

    @Component
    public static class CityRowMapperImpl implements RowMapper<City> {
        @Override
        public City mapRow(ResultSet resultSet, int i) throws SQLException {
            City city = new City();
            city.setId(resultSet.getLong("id"));
            city.setName(resultSet.getString("name"));
            return city;
        }
    }
}
