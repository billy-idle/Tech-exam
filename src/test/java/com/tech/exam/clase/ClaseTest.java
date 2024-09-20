package com.tech.exam.clase;

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
class ClaseTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void shouldCreateClase() {
        ClaseRequest request = new ClaseRequest("zO666  ", "  aBc4    ", "rT587");
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/clase", request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationNewClase = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationNewClase, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        String nombre = documentContext.read("$.nombre");
        String horario = documentContext.read("$.horario");
        String aula = documentContext.read("$.aula");

        assertThat(nombre).isEqualTo("ZO666");
        assertThat(horario).isEqualTo("ABC4");
        assertThat(aula).isEqualTo("RT587");
    }

    @Test
    @DirtiesContext
    void shouldUpdateHorario() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/clase/{nombre}/{horario}", HttpMethod.PUT, null, Void.class, "BD445", "  OPD2   ");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldReturnAllClases() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/clase", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int clasesCount = documentContext.read("$.length()");
        assertThat(clasesCount).isEqualTo(5);

        JSONArray nombres = documentContext.read("$..nombre");
        assertThat(nombres).containsExactlyInAnyOrder("BA200", "BD445", "BF410", "CS150", "CS250");
    }

    @Test
    void shouldNotReturnForUnknownCodigo() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/clase/QEP54", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldReturnClasesPage() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/clase?page=0&size=10&sort=horario,asc", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page).hasSize(5);
    }
}