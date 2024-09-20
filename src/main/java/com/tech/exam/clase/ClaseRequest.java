package com.tech.exam.clase;

import jakarta.validation.constraints.NotEmpty;

public record ClaseRequest(@NotEmpty(message = "{clase.nombre.empty}") String nombre,
                           @NotEmpty(message = "{clase.horario.empty}") String horario,
                           @NotEmpty(message = "{clase.aula.empty}") String aula) {
}
