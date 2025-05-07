package com.example.inventory.client;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.Map;

@Component
public class ProductClient {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${product.service.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ProductClient() {
        this.restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(3))
                .setReadTimeout(Duration.ofSeconds(3))
                .build();
    }

    public String fetchProductInfo(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = productServiceUrl + "/products/" + id;
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, request, Map.class);
            return response.getBody().toString(); // O puedes mapearlo si lo necesitas
        } catch (HttpClientErrorException.NotFound e) {
            return "Producto no encontrado";
        } catch (Exception e) {
            return "Error al conectar con product-service";
        }
    }
}
