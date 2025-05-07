package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;


import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product) {
        Product saved = repository.save(product);
        return ResponseEntity.status(201).body(toJsonApi(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(p -> ResponseEntity.ok(toJsonApi(p)))
                .orElse(ResponseEntity.status(404).body(error("Producto no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return repository.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            return ResponseEntity.ok(toJsonApi(repository.save(existing)));
        }).orElse(ResponseEntity.status(404).body(error("Producto no encontrado")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repository.findById(id).map(p -> {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.status(404).body(error("Producto no encontrado")));
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        List<Map<String, Object>> data = repository.findAll()
                .stream().skip((long) page * size).limit(size).map(this::toJsonApi).toList();
        return ResponseEntity.ok(Map.of("data", data));
    }

    private Map<String, Object> toJsonApi(Product p) {
        return Map.of(
                "type", "product",
                "id", p.getId(),
                "attributes", Map.of("name", p.getName(), "price", p.getPrice())
        );
    }

    private Map<String, Object> error(String message) {
        return Map.of("errors", List.of(Map.of("detail", message)));
    }
    @PostConstruct
    public void init() {
        System.out.println(">>> ProductController cargado");
    }
}
