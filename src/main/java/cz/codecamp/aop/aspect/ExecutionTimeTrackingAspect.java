package cz.codecamp.aop.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeTrackingAspect.class);

    @Around("@annotation(cz.codecamp.aop.aspect.annotation.TrackExecutionTime)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("about to start processing join point: " + proceedingJoinPoint);
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("It takes " + ((System.currentTimeMillis() - startTime))/1000 + " soconds");
        return result;
    }
}
