package com.example.ProductService;




import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ProductService.repository")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
