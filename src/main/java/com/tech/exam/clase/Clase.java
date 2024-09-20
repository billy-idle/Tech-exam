package com.tech.exam.clase;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Clase {
    private @NotEmpty(message = "{clase.nombre.empty}") String nombre;
    private @NotEmpty(message = "{clase.horario.empty}") String horario;
    private @NotEmpty(message = "{clase.aula.empty}") String aula;

    public Clase(String nombre, String horario, String aula) {
        this.nombre = nombre.trim().toUpperCase();
        this.horario = horario.trim().toUpperCase();
        this.aula = aula.trim().toUpperCase();
    }

    public void setNombre(@NotEmpty(message = "{clase.nombre.empty}") String nombre) {
        this.nombre = nombre.trim().toUpperCase();
    }

    public void setHorario(@NotEmpty(message = "{clase.horario.empty}") String horario) {
        this.horario = horario.trim().toUpperCase();
    }

    public void setAula(@NotEmpty(message = "{clase.aula.empty}") String aula) {
        this.aula = aula.trim().toUpperCase();
    }
}
