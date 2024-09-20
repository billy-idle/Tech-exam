package com.tech.exam.clase;

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
public class ClaseService {
    private final ClaseRepository repository;
    private final ClaseMapper mapper;

    public Clase saveClase(@Valid ClaseRequest request) {
        ClaseEntity entity = mapper.toClaseEntity(mapper.toClase(request));
        return mapper.toClase(repository.save(entity));
    }

    public Page<Clase> findClases(Pageable pageable) {
        return mapper.toClasePage(repository.findAll(PageRequest.of(pageable.getPageNumber(),
                                                                    pageable.getPageSize(),
                                                                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nombre")))));
    }

    public boolean deleteClase(String nombre) {
        repository.deleteByNombre(nombre);
        return !repository.existsByNombre(nombre);
    }

    @Transactional
    public Clase updateHorarioByNombre(String horario, String nombre) {
        Integer output = repository.updateHorarioByNombre(horario, nombre);
        if (output.equals(0)) {
            return null;
        }
        return findClaseByNombre(nombre);
    }

    public Clase findClaseByNombre(String nombre) {
        return mapper.toClase(repository.findByNombre(nombre).orElse(null));
    }
}
