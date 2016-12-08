package by.gsu.service;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import by.gsu.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vasiliy.pispanen.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void register(User user) {
        if (!isUserWithExistingCity(user)) {
            City city = registerCity(user.getCity());
            user.setCity(city);
        }
        userRepository.create(user);
    }

    private boolean isUserWithExistingCity(User user) {
        return user.getCity().getId() != null;
    }

    private City registerCity(City city) {
        cityRepository.crate(city);
        return cityRepository.findByName(city.getName());
    }
}
