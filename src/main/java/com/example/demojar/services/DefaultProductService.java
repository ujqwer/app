package com.example.demojar.services;

import com.example.demojar.entities.Product;

import java.util.List;
import java.util.Optional;

public interface DefaultProductService {
    Product save(Product user);
    Optional<Product> findProductById(Integer id);
    List<Product> findAllProducts();
}
