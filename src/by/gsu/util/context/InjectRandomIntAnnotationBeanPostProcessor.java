package by.gsu.util.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by Administrator on 05.12.16.
 */
@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization");

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(InjectRandomInt.class) != null) {
                field.setAccessible(true);
                try {
                    Random random = new Random();
                    field.set(o, random.nextInt());
                } catch (IllegalAccessException e) {
                    throw new BeanInitializationException("something wrong", e);
                }
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return o;
    }
}
