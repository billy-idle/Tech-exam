package com.tech.exam.inscripcion;

import com.tech.exam.clase.ClaseEntity;
import com.tech.exam.common.EntityBase;
import com.tech.exam.estudiante.EstudianteEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Entity(name = "Inscripcion")
@Table(name = "inscripcion")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionEntity extends EntityBase {
    @Column(nullable = false)
    private Integer posicion;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    private EstudianteEntity estudiante;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    private ClaseEntity clase;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InscripcionEntity that = (InscripcionEntity) o;
        return Objects.equals(posicion, that.posicion) && Objects.equals(estudiante, that.estudiante) && Objects.equals(clase, that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posicion, estudiante, clase);
    }
}
