package com.example.demojar.services.impl;

import com.example.demojar.entities.Product;
import com.example.demojar.repositories.ProductRepository;
import com.example.demojar.services.DefaultProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultProductServiceImpl implements DefaultProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
