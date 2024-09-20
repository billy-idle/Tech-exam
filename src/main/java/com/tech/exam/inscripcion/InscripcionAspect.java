package com.tech.exam.inscripcion;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class InscripcionAspect {

    @AfterReturning(value = "execution(* com.tech.exam.inscripcion.InscripcionService.saveInscripcion(*))", returning = "inscripcion")
    void logSavedAttempt(JoinPoint joinPoint, Inscripcion inscripcion) {
        var methodName = joinPoint.getSignature().getName();
        log.info("Method Called: {}, Inscripcion={}", methodName, inscripcion);
    }
}
