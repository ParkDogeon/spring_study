package config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(2)
@Aspect
public class ProxyCalculator2 {

    @Pointcut("execution(* exam02..*(..))")
    public void publicTarget(){}

    @Around("publicTarget()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable{

        long startTime = System.nanoTime();

        Object result = joinPoint.proceed(); // factorial 핵심기능 수행

        long endTime = System.nanoTime();

        System.out.printf("걸린시간 : %d%n", endTime - startTime);

        return result;

    }
}
