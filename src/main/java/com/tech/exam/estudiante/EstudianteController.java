package com.tech.exam.estudiante;

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
public class EstudianteController {

    private final EstudianteService service;

    @PostMapping("/estudiante")
    public ResponseEntity<Void> saveEstudiante(@RequestBody EstudianteRequest request) {
        Estudiante estudiante = service.saveEstudiante(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                             .path("/{codigo}")
                                             .buildAndExpand(estudiante.getCodigo())
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/estudiante/{codigo}")
    public ResponseEntity<Estudiante> findEstudianteByCodigo(@PathVariable Long codigo) {
        Estudiante estudiante = service.findEstudianteByCodigo(codigo);
        if (Objects.isNull(estudiante)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/estudiante")
    public ResponseEntity<List<Estudiante>> findAllEstudiantes(Pageable pageable) {
        Page<Estudiante> page = service.findEstudiantes(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @PutMapping("/estudiante/{codigo}/{nombre}")
    public ResponseEntity<Void> updateNombreByCodigo(@PathVariable Long codigo, @PathVariable String nombre) {
        Estudiante estudiante = service.updateNombrebyCodigo(codigo, nombre);
        if (Objects.isNull(estudiante)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/estudiante/{codigo}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long codigo) {
        if (service.deleteEstudiante(codigo)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
