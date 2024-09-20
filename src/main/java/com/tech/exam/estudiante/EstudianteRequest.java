package com.tech.exam.estudiante;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record EstudianteRequest(@Positive(message = "{estudiante.codigo.positive}") Long codigo,
                                @NotEmpty(message = "{estudiante.nombre.empty}") String nombre,
                                @NotEmpty(message = "{estudiante.especialidad.empty}") String especialidad,
                                @NotEmpty(message = "{estudiante.grado.empty}") String grado) {
}
