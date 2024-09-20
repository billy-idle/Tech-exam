package com.tech.exam.clase;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClaseMapper {
    ClaseEntity toClaseEntity(Clase clase);

    List<ClaseEntity> toClaseEntities(List<Clase> clases);

    List<Clase> toClases(List<ClaseEntity> claseEntities);

    default Page<Clase> toClasePage(Page<ClaseEntity> pageRequest) {
        List<Clase> clases = new ArrayList<>(pageRequest.getSize());
        for (ClaseEntity entity : pageRequest.getContent()) {
            clases.add(toClase(entity));
        }
        return new PageImpl<>(clases, pageRequest.getPageable(), pageRequest.getTotalElements());
    }

    Clase toClase(ClaseEntity claseEntity);

    Clase toClase(ClaseRequest request);
}
