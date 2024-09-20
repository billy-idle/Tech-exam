package com.tech.exam.inscripcion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InscripcionController {
    private final InscripcionService service;

    @PostMapping("/inscripcion")
    public ResponseEntity<Void> saveInscripcion(@RequestBody Inscripcion request) {
        service.saveInscripcion(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                             .buildAndExpand()
                                             .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/inscripcion")
    public ResponseEntity<List<Inscripcion>> findAllClases(Pageable pageable) {
        Page<Inscripcion> page = service.findAllInscripcion(pageable);
        return ResponseEntity.ok(page.getContent());
    }
}
