package com.tech.exam.clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {
    void deleteByNombre(String nombre);

    @Modifying
    @Query("update Clase e set e.horario=:horario where e.nombre=:nombre")
    Integer updateHorarioByNombre(String horario, String nombre);

    Optional<ClaseEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
