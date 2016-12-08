package by.gsu;

import by.gsu.repository.city.CityRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Administrator on 01.12.16.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new FileSystemXmlApplicationContext("classpath:*context.xml");

        CityRepository cityRepository = context.getBean(CityRepository.class);

        System.out.println(cityRepository.findAll());

        context.registerShutdownHook();

    }
}
