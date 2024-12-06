package com.example.ProductService.controller;
import com.example.ProductService.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;


import com.example.ProductService.model.Product;
import com.example.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
public Product createProduct(@RequestBody Product product) {
    Product savedProduct = productRepository.save(product);
    return savedProduct;
}

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        System.out.println("PUT Request received for ID: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    
        System.out.println("Product found: " + product);
    
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        Product updatedProduct = productRepository.save(product);
    
        System.out.println("Updated Product: " + updatedProduct);
    
        return ResponseEntity.ok(updatedProduct);
    }
    
@DeleteMapping("/{id}")
public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

    productRepository.delete(product);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);

    return ResponseEntity.ok(response);
}

}
