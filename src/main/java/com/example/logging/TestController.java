package com.example.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/poc")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/echo/{name}")
    public ResponseEntity<String> echo(@PathVariable String name) {
        String answer = "Hello %s".formatted(name);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testRestTemplate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("X-COM-PERSIST", "NO");
        headers.set("X-COM-LOCATION", "USA");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://www.google.com", HttpMethod.GET, entity, String.class);
        return response;
    }
}
