package by.gsu.repository.city;

import by.gsu.model.City;
import by.gsu.repository.ConnectionManager;
import by.gsu.util.context.InjectRandomInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by Administrator on 21.11.16.
 */
@Component
@Primary
public class CityRepositoryImpl implements CityRepository {

    public static final String FIND_ALL = "SELECT * FROM City";
    public static final String FIND_BY_ID = "SELECT * FROM City WHERE id = %d ";

    @Autowired
    private ConnectionManager cnManager;
    @Autowired
    private CityResultSetParser cityRSParser;

    @InjectRandomInt
    public int number;

    public CityRepositoryImpl() {
        System.out.println(number);
    }

    @PostConstruct
    public void initCache() {
        List<City> cities = findAll();
        System.out.println(cities);
        System.out.println(number);
    }


    @PreDestroy
    public void writeCache() {
        System.out.println("write cache!");
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
