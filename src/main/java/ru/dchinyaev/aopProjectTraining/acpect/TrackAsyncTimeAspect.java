package ru.dchinyaev.aopProjectTraining.acpect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.dchinyaev.aopProjectTraining.entity.StatisticsMethodTime;
import ru.dchinyaev.aopProjectTraining.service.StatisticsService;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TrackAsyncTimeAspect {
    private final StatisticsService statisticsService;
    @Pointcut("@annotation(ru.dchinyaev.aopProjectTraining.annotation.TrackAsyncTime)")
    public void asyncRunnerPointcut() {}

    @Around("asyncRunnerPointcut()")
    public Object asyncRunnerAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        return CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();

            String methodName = proceedingJoinPoint.getSignature().getName();

            log.info("Выполнение метода {}", methodName);

            try {
                Object result = proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            StatisticsMethodTime statistics = StatisticsMethodTime.builder()
                    .methodName(methodName)
                    .executionTime(executionTime)
                    .additionalInfo("Метод %s выполнился за %d мс".formatted(methodName, executionTime))
                    .build();
            statisticsService.save(statistics);

            log.info("Метод {} выполнился за {} мс", methodName, endTime - startTime);
        });
    }
}

