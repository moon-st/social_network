package by.gsu;

import by.gsu.model.City;
import by.gsu.model.User;
import by.gsu.repository.city.CityRepository;
import by.gsu.repository.city.CityRepositoryImpl;
import by.gsu.repository.user.UserRepository;
import by.gsu.repository.user.UserRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        CityRepository cityRepository = new CityRepositoryImpl();

        System.out.println(cityRepository.findAll());
        City city = cityRepository.findById(1L);
        System.out.println(city);

        UserRepository userRepository = new UserRepositoryImpl();

        List<User> all = userRepository.findAll();
        System.out.println("before create");
        System.out.println(all);

        User userToCreate = new User(null, "Sam", city);
        userRepository.create(userToCreate);

        all = userRepository.findAll();
        System.out.println("after create");
        System.out.println(all);

        User lastUser = all.get(all.size() - 1);
        lastUser.setName("Jon");
        userRepository.update(lastUser);

        all = userRepository.findAll();
        System.out.println("after update");
        System.out.println(all);

        User userById = userRepository.getUserById(lastUser.getId());
        System.out.println("get by id");
        System.out.println(userById);

        userRepository.delete(lastUser.getId());

        all = userRepository.findAll();
        System.out.println("after delete");
        System.out.println(all);
    }
}
