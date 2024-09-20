package com.tech.exam.inscripcion;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InscripcionController {
    private final InscripcionService service;

    @PostMapping("/inscripcion")
    public ResponseEntity<Void> saveInscripcion(@RequestBody Inscripcion request) {
        Inscripcion inscripcion = service.saveInscripcion(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                             .path("/{id}")
                                             .buildAndExpand(inscripcion.getEstudiante().getCodigo())
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }
}
