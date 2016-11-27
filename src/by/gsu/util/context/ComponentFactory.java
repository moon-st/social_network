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
                result = enableProfiling(result, type);

            } catch (Exception e) {
                System.out.println("ignored error: " + e.getMessage());
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <T> T enableProfiling(final T original, Class<? extends T> cls) {
        if (cls.getAnnotation(Profiling.class) != null) {

            return (T) Proxy.newProxyInstance(
                    cls.getClassLoader(),
                    cls.getInterfaces(),
                    new ProfilingInvocationHandler<>(original, cls));
        }
        return original;
    }

    public static class ProfilingInvocationHandler<T> implements InvocationHandler {

        private final T original;
        private final Class<? extends T> cls;

        public ProfilingInvocationHandler(T original, Class<? extends T> cls) {
            this.original = original;
            this.cls = cls;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            System.out.println("start profiling: " + cls.getSimpleName() + " " + method.getName());
            Object invokeResult = method.invoke(original, args);
            long end = System.currentTimeMillis();
            System.out.println("end profiling:" + cls.getSimpleName() + " " + method.getName() + " " + (start - end) + "ms");

            return invokeResult;
        }
    }

}
