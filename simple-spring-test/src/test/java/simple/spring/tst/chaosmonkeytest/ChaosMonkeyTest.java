package simple.spring.tst.chaosmonkeytest;

import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpHead;
import de.codecentric.spring.boot.chaos.monkey.configuration.AssaultProperties;
import de.codecentric.spring.boot.chaos.monkey.configuration.WatcherProperties;
import de.codecentric.spring.boot.chaos.monkey.endpoints.dto.ChaosMonkeyStatusResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChaosMonkeyTest {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    transient RestTemplate restTemplate;

    @BeforeAll
    void beforeAll() {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Test
    void getStatus() {
        ResponseEntity<ChaosMonkeyStatusResponseDto> response =
                restTemplate.exchange(
                        URI.create("http://localhost:8080/actuator/chaosmonkey/status"),
                        HttpMethod.GET,
                        RequestEntity.EMPTY,
                        ChaosMonkeyStatusResponseDto.class
                );
        System.out.println(response.getBody());
    }

    @Test
    void enableChaosMonkey() {
        ResponseEntity<ChaosMonkeyStatusResponseDto> response =
                restTemplate.exchange(
                        URI.create("http://localhost:8080/actuator/chaosmonkey/enable"),
                        HttpMethod.POST,
                        RequestEntity.EMPTY,
                        ChaosMonkeyStatusResponseDto.class
                );
        System.out.println(response.getBody());
    }

    @Test
    void checkWatchers() {
        ResponseEntity<WatcherProperties> response =
                restTemplate.exchange(
                        URI.create("http://localhost:8080/actuator/chaosmonkey/watchers"),
                        HttpMethod.GET,
                        RequestEntity.EMPTY,
                        WatcherProperties.class
                );
        System.out.println(response.getBody());
    }

    @Test
    void checkAssert() {
        ResponseEntity<AssaultProperties> response =
                restTemplate.exchange(
                        URI.create("http://localhost:8080/actuator/chaosmonkey/assaults"),
                        HttpMethod.GET,
                        RequestEntity.EMPTY,
                        AssaultProperties.class
                );
        System.out.println(response.getBody());
    }

    @Test
    void setupAssaults() {
        HashMap<String, Object> body =new HashMap<String, Object>();
        body.put("latencyRangeStart", "1000");
        body.put("latencyRangeEnd", "2000");
        body.put("latencyActive", "true");
        body.put("level", "3");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Object> request = new RequestEntity<>(
                body,
                headers,
                HttpMethod.POST,
                URI.create("http://localhost:8080/actuator/chaosmonkey/assaults")
        );

        ResponseEntity response = restTemplate.exchange(
                request,
                Void.class
        );
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }


}
