package com.tech.exam.inscripcion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {

    InscripcionEntity toInscripcionEntity(Inscripcion inscripcion);

    default Page<Inscripcion> toInscripcionPage(Page<InscripcionEntity> inscripcionPage) {
        List<Inscripcion> inscripcionList = new ArrayList<>(inscripcionPage.getSize());
        for (InscripcionEntity inscripcionEntity : inscripcionPage.getContent()) {
            inscripcionList.add(toInscripcion(inscripcionEntity));
        }
        return new PageImpl<>(inscripcionList, inscripcionPage.getPageable(), inscripcionPage.getTotalElements());
    }

    @Mapping(source = "estudiante.codigo", target = "codigoEstudiante", ignore = true)
    @Mapping(source = "clase.nombre", target = "nombreClase")
    Inscripcion toInscripcion(InscripcionEntity inscripcionEntity);
}

