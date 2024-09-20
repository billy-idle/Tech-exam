package com.tech.exam.estudiante;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstudianteMapper {
    EstudianteEntity toEstudianteEntity(Estudiante estudiante);

    List<EstudianteEntity> toEstudianteEntities(List<Estudiante> estudiantes);

    List<Estudiante> toEstudiantes(List<EstudianteEntity> estudianteEntities);

    Estudiante toEstudiante(EstudianteRequest request);

    default Page<Estudiante> toEstudiantePage(Page<EstudianteEntity> pageRequest) {
        List<Estudiante> estudiantes = new ArrayList<>(pageRequest.getSize());
        for (EstudianteEntity entity : pageRequest.getContent()) {
            estudiantes.add(toEstudiante(entity));
        }
        return new PageImpl<>(estudiantes, pageRequest.getPageable(), pageRequest.getTotalElements());
    }

    Estudiante toEstudiante(EstudianteEntity estudianteEntity);
}
