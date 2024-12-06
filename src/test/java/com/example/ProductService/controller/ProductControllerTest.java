package com.example.ProductService.controller;

import com.example.ProductService.controller.ProductController;
import com.example.ProductService.model.Product;
import com.example.ProductService.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(
            Arrays.asList(
                new Product(1L, "Test Product 1", 50.0),
                new Product(2L, "Test Product 2", 75.0)
            )
        );

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product 1"))
                .andExpect(jsonPath("$[1].price").value(75.0));
    }

    @Test
public void testCreateProduct() throws Exception {
    Product product = new Product(null, "New Product", 99.99);
    Product savedProduct = new Product(1L, "New Product", 99.99);

    when(productRepository.save(product)).thenReturn(savedProduct);

    mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"New Product\", \"price\": 99.99}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("New Product"))
            .andExpect(jsonPath("$.price").value(99.99));
}


    @Test
    public void testUpdateProduct() throws Exception {
        Product existingProduct = new Product(1L, "Old Product", 50.0);
        Product updatedProduct = new Product(1L, "Updated Product", 75.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Product\", \"price\": 75.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(75.0));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product existingProduct = new Product(1L, "Product to Delete", 50.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));

        mockMvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));
    }
}
