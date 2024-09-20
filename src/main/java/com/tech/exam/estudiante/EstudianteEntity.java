package com.tech.exam.estudiante;

import com.tech.exam.common.EntityBase;
import com.tech.exam.inscripcion.InscripcionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "Estudiante")
@Table(name = "estudiante")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteEntity extends EntityBase {
    @Column(nullable = false, unique = true)
    private Long codigo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String especialidad;
    @Column(nullable = false)
    private String grado;
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<InscripcionEntity> inscripciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EstudianteEntity that = (EstudianteEntity) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(especialidad, that.especialidad) && Objects.equals(grado, that.grado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombre, especialidad, grado);
    }
}
