package com.tech.exam.geo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forma")
public class FormaController {

    @GetMapping("/triangulo")
    public ResponseEntity<Double> calculaAreaTriangulo(@RequestParam double base, @RequestParam double altura) {
        return ResponseEntity.ok(Forma.calculaAreaTriangulo(base, altura));
    }

    @GetMapping("/circulo")
    public ResponseEntity<Double> calculaAreaCirculo(@RequestParam double radio) {
        return ResponseEntity.ok(Forma.calculaAreaCirculo(radio));
    }
}
