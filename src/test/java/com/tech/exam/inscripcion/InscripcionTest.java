package com.tech.exam.inscripcion;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.tech.exam.estudiante.EstudianteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InscripcionTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldCreateInscripcion() {
        Inscripcion inscripcion = new Inscripcion(null, null, 100L, null, "HISTORIA", 5 );
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/estudiante", inscripcion, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationNewInscripcion = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationNewInscripcion, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        String nombre = documentContext.read("$.nombre");
        Integer codigo = documentContext.read("$.codigo");
        String especialidad = documentContext.read("$.especialidad");
        String grado = documentContext.read("$.grado");

        assertThat(nombre).isEqualTo("BILLY");
        assertThat(codigo).isEqualTo(4004);
        assertThat(especialidad).isEqualTo("PROGRAMACIÓN");
        assertThat(grado).isEqualTo("SR");
    }
}