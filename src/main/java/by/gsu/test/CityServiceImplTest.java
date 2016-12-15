package by.gsu.test;

import by.gsu.model.City;
import by.gsu.repository.city.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author vasiliy.pispanen.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:context.xml"})
public class CityServiceImplTest {

    @Autowired
    private CityRepository cityRepository;

    @Before
    public void setUp() throws Exception {
        System.out.println("setup");
    }

    @Test
    public void testFindBuId() throws Exception {
        City city = cityRepository.findById(1L);

        assertNotNull(city);
        assertEquals(city.getId(), new Long(1L));
        assertEquals(city.getName(), "Gomel");
    }

}