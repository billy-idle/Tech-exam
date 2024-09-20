package com.tech.exam.inscripcion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tech.exam.clase.Clase;
import com.tech.exam.estudiante.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Estudiante estudiante;
    private Long codigoEstudiante;
    @JsonIgnore
    private Clase clase;
    private String nombreClase;
    private Integer posicion;

    public Inscripcion(Integer posicion) {
        this.posicion = posicion;
    }
}
