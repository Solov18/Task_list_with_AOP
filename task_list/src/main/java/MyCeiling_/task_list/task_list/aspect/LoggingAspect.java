package MyCeiling_.task_list.task_list.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDateTime;
import java.util.Arrays;

//@Aspect
//public class LoggingAspect {
//    @Around("execution(* task_list.service.*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Method name: " + joinPoint.getSignature().getName());
//
//        // Получаем аргументы метода
//        Object[] args = joinPoint.getArgs();
//        String arguments = Arrays.toString(args);
//        System.out.println("Arguments: " + arguments);
//
//        // Время начала
//        System.out.println("Start time: " + LocalDateTime.now());
//
//        // Вызов оригинального метода
//        Object result;
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            System.out.println("Exception in method: " + throwable.getMessage());
//            throw throwable;
//        }
//
//        // Время окончания
//        System.out.println("End time: " + LocalDateTime.now());
//
//        return result;
//    }
//}