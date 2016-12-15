package by.gsu.service;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import by.gsu.repository.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author vasiliy.pispanen.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private UserServiceImpl testee;

    private City existingCity = new City(1L, "Test");
    private City newCity = new City(null, "Test");


    @Before
    public void setUp() throws Exception {
        when(cityRepository.findByName(anyString())).thenReturn(existingCity);

    }

    @Test
    public void registerWhenCityIsExist() throws Exception {
        User test = new User(null, "Test", existingCity);
        testee.register(test);

        verify(userRepository).create(eq(test));
        verify(cityRepository, times(0)).crate(any(City.class));
        verify(cityRepository, times(0)).findByName(anyString());
    }

    @Test
    public void registerWhenCityIsNotExist() throws Exception {
        User test = new User(null, "Test", newCity);
        testee.register(test);

        verify(userRepository).create(eq(test));
        verify(cityRepository, times(1)).crate(any(City.class));
        verify(cityRepository, times(1)).findByName(anyString());
    }

}