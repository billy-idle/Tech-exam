package com.tech.exam.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    void deleteByCodigo(Long codigo);

    @Modifying
    @Query("update Estudiante e set e.nombre=:nombre where e.codigo=:codigo")
    Integer updateNombreByCodigo(Long codigo, String nombre);

    Optional<EstudianteEntity> findByCodigo(Long codigo);

    boolean existsByCodigo(Long codigo);
}
