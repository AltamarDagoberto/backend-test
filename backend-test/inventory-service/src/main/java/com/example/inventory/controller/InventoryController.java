package com.example.inventory.controller;

import com.example.inventory.client.ProductClient;
import com.example.inventory.model.Inventory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final Map<Long, Inventory> inventoryStore = new HashMap<>();
    private final ProductClient productClient;

    public InventoryController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getInventory(@PathVariable Long productId) {
        Inventory inv = inventoryStore.get(productId);
        if (inv == null) return ResponseEntity.status(404).body(Map.of("errors", List.of(Map.of("detail", "No inventory for product"))));

        String productData = productClient.fetchProductInfo(productId);
        return ResponseEntity.ok(Map.of(
            "data", Map.of(
                "type", "inventory",
                "id", productId,
                "attributes", Map.of(
                    "quantity", inv.getQuantity(),
                    "product_info", productData
                )
            )
        ));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateQuantity(@PathVariable Long productId, @RequestParam int qty) {
        inventoryStore.put(productId, new Inventory(productId, qty));
        System.out.println("🔔 Inventario actualizado para producto " + productId);
        return ResponseEntity.ok(Map.of("data", Map.of("message", "Inventory updated")));
    }
}
