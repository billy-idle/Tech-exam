package com.tech.exam.estudiante;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository repository;
    private final EstudianteMapper mapper;

    public Estudiante saveEstudiante(@Valid EstudianteRequest request) {
        EstudianteEntity entity = mapper.toEstudianteEntity(mapper.toEstudiante(request));
        return mapper.toEstudiante(repository.save(entity));
    }

    public Page<Estudiante> findEstudiantes(Pageable pageable) {
        return mapper.toEstudiantePage(repository.findAll(PageRequest.of(pageable.getPageNumber(),
                                                                         pageable.getPageSize(),
                                                                         pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre")))));
    }

    public boolean deleteEstudiante(Long codigo) {
        repository.deleteByCodigo(codigo);
        return !repository.existsByCodigo(codigo);
    }

    @Transactional
    public Estudiante updateNombrebyCodigo(Long codigo, String nombre) {
        Integer output = repository.updateNombreByCodigo(codigo, nombre);
        if (output.equals(0)) {
            return null;
        }
        return findEstudianteByCodigo(codigo);
    }

    public Estudiante findEstudianteByCodigo(Long codigo) {
        return mapper.toEstudiante(repository.findByCodigo(codigo).orElse(null));
    }
}
