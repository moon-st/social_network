package by.gsu.util.context;

import by.gsu.util.Profiling;
import org.reflections.Reflections;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * Created by Administrator on 24.11.16.
 */
public class ComponentFactory {

    public static<T> T createComponent(Class<T> componentClass) {
        Reflections reflections = new Reflections("by.gsu");
        Set<Class<? extends T>> types = reflections.getSubTypesOf(componentClass);

        T result = null;

        for (Class<? extends T> type : types) {
            try {
                result = type.newInstance();
            } catch (Exception e) {}
        }

        return result;
    }



}
