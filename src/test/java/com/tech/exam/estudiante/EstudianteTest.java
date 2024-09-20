package com.tech.exam.estudiante;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstudianteTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldCreateEstudiante() {
        EstudianteRequest request = new EstudianteRequest(4004L, "biLLy    ", "proGramacióN", "sR");
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/estudiante", request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationNewEstudiante = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationNewEstudiante, String.class);
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

    @Test
    @DirtiesContext
    void shouldUpdateEstudiante() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/estudiante/{codigo}/{nombre}", HttpMethod.PUT, null, Void.class, 150L, "  BRucE   ");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldReturnAllEstudiantes() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/estudiante", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int estudiantesCount = documentContext.read("$.length()");
        assertThat(estudiantesCount).isEqualTo(8);

        JSONArray codigos = documentContext.read("$..codigo");
        assertThat(codigos).containsExactlyInAnyOrder(100, 150, 200, 250, 300, 350, 400, 450);
    }

    @Test
    void shouldNotReturnForUnknownCodigo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/estudiante/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnEstudiantesPage() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/estudiante?page=0&size=10&sort=codigo,asc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page).hasSize(8);
    }
}