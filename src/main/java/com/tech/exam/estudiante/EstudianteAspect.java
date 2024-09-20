package com.tech.exam.estudiante;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class EstudianteAspect {

    @AfterReturning(value = "execution(* com.tech.exam.estudiante.EstudianteService.saveEstudiante(*))", returning = "estudiante")
    void logSavedAttempt(JoinPoint joinPoint, Estudiante estudiante) {
        var methodName = joinPoint.getSignature().getName();
        log.info("Method Called: {}, Estudiante={}", methodName, estudiante);
    }
}
