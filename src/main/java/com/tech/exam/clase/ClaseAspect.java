package com.tech.exam.clase;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ClaseAspect {

    @AfterReturning(value = "execution(* com.tech.exam.clase.ClaseService.saveClase(*))", returning = "clase")
    void logSavedAttempt(JoinPoint joinPoint, Clase clase) {
        var methodName = joinPoint.getSignature().getName();
        log.info("Method Called: {}, Clase={}", methodName, clase);
    }
}
