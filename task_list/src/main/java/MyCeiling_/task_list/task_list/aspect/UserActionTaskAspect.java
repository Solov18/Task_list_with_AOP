package MyCeiling_.task_list.task_list.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

import static java.sql.DriverManager.println;



@Aspect
@Component
public class UserActionTaskAspect {
    // Карта действий для отображения методов на описания
    private final Map<String, String> mapAction = Map.of(
           "getAllTasks", "Запрос всех пользователей",
            "getTaskById", "Запрос задачи по id",
            "saveTask", "Сохранение задачи",
            "updateTask", "Обновление задачи",
            "delTask", "Удаление задачи",
            "getTaskByStatus", "Запрос задачи по статусу",
            "addTask","добавить задачу",
            "updateStatus","Обновление статуса задачи"

    );

    // Аспект, отслеживающий методы, помеченные аннотацией TrackUserAction
    @Around("@annotation(TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получение имени метода
        String methodName = joinPoint.getSignature().getName();
        // Получение описания действия из карты, если метод не найден, используется имя метода
        String actionDescription = mapAction.getOrDefault(methodName, "Неизвестное действие");

        // Проверка наличия метода в карте
        if (!mapAction.containsKey(methodName)) {
            System.out.println("Метод " + methodName + " не найден в mapAction");
        }

        // Получаем аргументы метода
        Object[] args = joinPoint.getArgs();
        String arguments = Arrays.toString(args);

        // Логирование начала выполнения метода
        System.out.println("Время начала: " + LocalDateTime.now());
        System.out.println(actionDescription + " - Аргументы: " + arguments);

        // Измерение времени выполнения метода
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            // Вызов оригинального метода
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("Ошибка при выполнении метода: " + throwable.getMessage());
            throw throwable;
        }
        long runtime = System.currentTimeMillis() - startTime;

        // Логирование окончания выполнения метода
        System.out.println("Выполнено за " + runtime + " мс");
        System.out.println("Время окончания: " + LocalDateTime.now());

        return result;
    }
}