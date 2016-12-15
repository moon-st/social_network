package by.gsu;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import by.gsu.repository.user.UserRepository;
import by.gsu.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by Administrator on 01.12.16.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new FileSystemXmlApplicationContext("classpath:*context.xml");

        CityRepository cityRepository = context.getBean(CityRepository.class);

        System.out.println(cityRepository.findAll());
        City city = cityRepository.findById(1L);
        System.out.println(city);

        UserRepository userRepository = context.getBean(UserRepository.class);

        User user = new User(null, "Test", city);
        userRepository.create(user);

        List<User> users = userRepository.findAll();
        System.out.println(users);

        User user1 = users.get(0);
        user1 = userRepository.findById(user1.getId());

        user1.setName(user1.getName() + " 1");

        userRepository.update(user1);

        userRepository.delete(user1.getId());

        UserService userService = context.getBean(UserService.class);
        userService.register(new User(null, "TestReg", new City(null, city.getName() + System.currentTimeMillis())));

        users = userRepository.findAll();
        System.out.println(users.get(users.size() - 1));


        context.registerShutdownHook();

    }
}
