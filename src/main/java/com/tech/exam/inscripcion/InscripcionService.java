package com.tech.exam.inscripcion;

import com.tech.exam.clase.Clase;
import com.tech.exam.clase.ClaseService;
import com.tech.exam.common.NoRecordFoundException;
import com.tech.exam.estudiante.Estudiante;
import com.tech.exam.estudiante.EstudianteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@Validated
@RequiredArgsConstructor
public class InscripcionService {
    private final InscripcionRepository repository;
    private final EstudianteService estudianteService;
    private final ClaseService claseService;
    private final InscripcionMapper mapper;

    @Transactional
    public Inscripcion saveInscripcion(@Valid Inscripcion inscripcion) throws NoRecordFoundException {
        Estudiante estudiante = estudianteService.findEstudianteByCodigo(inscripcion.getCodigoEstudiante());
        if (Objects.isNull(estudiante)) {
            throw new NoRecordFoundException("No se encontró ningún registro para el código del estudiante: " + inscripcion.getCodigoEstudiante());
        }

        Clase clase = claseService.findClaseByNombre(inscripcion.getNombreClase());
        if (Objects.isNull(clase)) {
            throw new NoRecordFoundException("No se encontró ningún registro para el nombre de clase: " + inscripcion.getNombreClase());
        }

        inscripcion.setEstudiante(estudiante);
        inscripcion.setClase(clase);

        InscripcionEntity entity = mapper.toInscripcionEntity(inscripcion);


        Inscripcion savedInscripcion = mapper.toInscripcion(repository.save(mapper.toInscripcionEntity(inscripcion)));

        return savedInscripcion;
    }

    public Page<Inscripcion> findAllInscripcion(Pageable pageable) {
        return mapper.toInscripcionPage(repository.findAll(PageRequest.of(pageable.getPageNumber(),
                                                                          pageable.getPageSize(),
                                                                          pageable.getSortOr(Sort.by(Sort.Direction.ASC,
                                                                                                     "nombreClase")))));
    }
}
