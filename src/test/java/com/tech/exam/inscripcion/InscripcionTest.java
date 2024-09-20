package com.tech.exam.inscripcion;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
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

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InscripcionTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldCreateInscripcion() {
        Inscripcion inscripcion = new Inscripcion(null, null, 100L, null, "CS250", 5);
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/inscripcion", inscripcion, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationNewInscripcion = response.getHeaders().getLocation();
        System.out.println(locationNewInscripcion);
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationNewInscripcion, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        int inscripcionesCount = documentContext.read("$.length()");
        assertThat(inscripcionesCount).isEqualTo(10);

        JSONArray posiciones = documentContext.read("$..posicion");
        assertThat(posiciones).containsExactlyInAnyOrder(1, 1, 1, 1, 1, 2, 2, 2, 3, 5);
    }
}