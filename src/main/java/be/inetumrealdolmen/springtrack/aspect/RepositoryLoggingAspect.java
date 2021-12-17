package be.inetumrealdolmen.springtrack.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class RepositoryLoggingAspect {

    @After("springRepositoryPointCut()")
    public void whenWeShouldLog(JoinPoint joinPoint) {
        log.info("aspect based logging activated for method: " + joinPoint.getSignature().toShortString());
    }


    @Pointcut("within(org.springframework.data.repository.Repository+)")
    public void springRepositoryPointCut() {
    }
}
