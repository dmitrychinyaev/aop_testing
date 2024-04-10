package ru.dchinyaev.aopProjectTraining.acpect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.dchinyaev.aopProjectTraining.entity.StatisticsMethodTime;
import ru.dchinyaev.aopProjectTraining.service.StatisticsService;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackTimeAspect {
    private final StatisticsService statisticsService;
    @Pointcut("@annotation(ru.dchinyaev.aopProjectTraining.annotation.TrackTime)")
    public void trackTimerPointcut() {
    }
    @Pointcut("execution(* ru.dchinyaev.aopProjectTraining.controller.EmployeeController.getClient(..)) ")
    public void checkIdPointcut(){}
    @Around("trackTimerPointcut()")
    public Object trackTimeAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();

        log.info("Выполнение метода {}", methodName);

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        StatisticsMethodTime statistics = StatisticsMethodTime.builder()
                .methodName(methodName)
                .executionTime(executionTime)
                .additionalInfo("Метод %s выполнился за %d мс".formatted(methodName, executionTime))
                .build();
        statisticsService.save(statistics);

        log.info("Метод {} выполнился за {} мс", methodName, endTime - startTime);

        return result;
    }
    @AfterThrowing(pointcut = "checkIdPointcut()", throwing = "exception")
    public void afterThrowingGetByIdLoggingAdvice(RuntimeException exception){
        log.error("Произошла ошибка. Запрашиваемый id не существует");
        StatisticsMethodTime statistics = StatisticsMethodTime.builder()
                .methodName("getClientById()")
                .executionTime(null)
                .additionalInfo("Произошла ошибка. Запрашиваемый id не существует")
                .build();
        statisticsService.save(statistics);
    }
}
