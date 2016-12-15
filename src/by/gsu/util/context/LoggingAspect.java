package by.gsu.util.context;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author vasiliy.pispanen.
 */
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* by.gsu.service.UserServiceImpl.register(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround() is running!");
        System.out.println("method : " + joinPoint.getSignature().getName());
        System.out.println("arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        Object result = joinPoint.proceed();//continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");

        return result;
    }
}
