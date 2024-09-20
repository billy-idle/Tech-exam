package com.tech.exam.estudiante;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Estudiante {
    @JsonIgnore
    private Long id;
    private @Positive(message = "{estudiante.codigo.positive}") Long codigo;
    private @NotEmpty(message = "{estudiante.nombre.empty}") String nombre;
    private @NotEmpty(message = "{estudiante.especialidad.empty}") String especialidad;
    private @NotEmpty(message = "{estudiante.grado.empty}") String grado;

    public Estudiante(Long codigo, String nombre, String especialidad, String grado) {
        this.codigo = codigo;
        this.nombre = nombre.trim().toUpperCase();
        this.especialidad = especialidad.trim().toUpperCase();
        this.grado = grado.trim().toUpperCase();
    }

    public void setNombre(@NotEmpty(message = "{estudiante.nombre.empty}") String nombre) {
        this.nombre = nombre.trim().toUpperCase();
    }

    public void setEspecialidad(@NotEmpty(message = "{estudiante.especialidad.empty}") String especialidad) {
        this.especialidad = especialidad.trim().toUpperCase();
    }

    public void setGrado(@NotEmpty(message = "{estudiante.grado.empty}") String grado) {
        this.grado = grado.trim().toUpperCase();
    }
}
