package by.gsu.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 01.12.16.
 */
public class ProxyMain {
    public static void main(String[] args) {
        Base b = get();

        b.doSomething();
    }

    public static Base get() {
        Base original = new Impl();

        ClassLoader classLoader = original.getClass().getClassLoader();
        Class<?>[] interfaces = {Base.class};

        Base result = (Base) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before doing...");
                return method.invoke(original, args);
            }
        });

        return result;
    }


    public interface Base {
        void doSomething();
    }

    public static class Impl implements Base{

        @Override
        public void doSomething() {
            System.out.println("i'm do!");
        }
    }
}


