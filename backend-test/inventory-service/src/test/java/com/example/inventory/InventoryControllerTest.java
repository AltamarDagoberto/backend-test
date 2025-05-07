package com.example.inventory;

import com.example.inventory.controller.InventoryController;
import com.example.inventory.client.ProductClient; // Asegúrate de que esta ruta sea correcta

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; // ← faltaba
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductClient productClient;

    @Test
    void testGetInventoryWithProductInfo() throws Exception {
        when(productClient.fetchProductInfo(1L)).thenReturn("{\"name\":\"Laptop\"}");

        mockMvc.perform(get("/inventories/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.attributes.product_info").value("{\"name\":\"Laptop\"}"));
    }
}
