package com.tech.exam.clase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService service;

    @PostMapping("/clase")
    public ResponseEntity<Void> saveClase(@RequestBody ClaseRequest request) {
        Clase clase = service.saveClase(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                             .path("/{nombre}")
                                             .buildAndExpand(clase.getNombre())
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/clase/{nombre}")
    public ResponseEntity<Clase> findClaseByNombre(@PathVariable String nombre) {
        Clase clase = service.findClaseByNombre(nombre);
        if (Objects.isNull(clase)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clase);
    }

    @GetMapping("/clase")
    public ResponseEntity<List<Clase>> findAllClases(Pageable pageable) {
        Page<Clase> page = service.findClases(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @PutMapping("/clase/{nombre}/{horario}")
    public ResponseEntity<Void> updateHorarioByNombre(@PathVariable String nombre, @PathVariable String horario) {
        Clase clase = service.updateHorarioByNombre(horario, nombre);
        if (Objects.isNull(clase)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clase/{nombre}")
    public ResponseEntity<Void> deleteClase(@PathVariable String nombre) {
        if (service.deleteClase(nombre)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
