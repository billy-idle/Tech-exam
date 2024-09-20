package com.tech.exam.clase;

import com.tech.exam.common.EntityBase;
import com.tech.exam.inscripcion.InscripcionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity(name = "Clase")
@Table(name = "clase")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClaseEntity extends EntityBase {
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String horario;
    @Column(nullable = false)
    private String aula;
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<InscripcionEntity> inscripciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClaseEntity that = (ClaseEntity) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(horario, that.horario) && Objects.equals(aula, that.aula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombre, horario, aula);
    }
}
