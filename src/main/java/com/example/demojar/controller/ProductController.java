package com.example.demojar.controller;

import com.example.demojar.entities.Product;
import com.example.demojar.entities.User;
import com.example.demojar.services.DefaultProductService;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private DefaultProductService defaultProductService;


    @GetMapping("/hello")
    public String method() {
        return "Hello ujjman!!";
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return defaultProductService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getProductsOfUserFromEmail")
    public List<Product> getProductsOfUserFromEmail(@RequestParam String email) {
        List<Product> list = defaultProductService.findAllProducts();
        List<Product> products = new LinkedList<>();
        try {
            for (Product p : list) {
                if (p.getUserCreatedEmailId().equals(email)) {
                    products.add(p);
                }
            }
            return products;
        }
        catch(Exception e)
        {
            return null;
        }

    }

 @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return defaultProductService.findAllProducts();
    }

    @GetMapping("/getAllUnsoldProducts")
    public List<Product> getAllUnsoldProducts() {
        List<Product> list=  defaultProductService.findAllProducts();
        List<Product> products = new ArrayList<>();
        for(Product p : list)
        {
            if(!p.isSold())
            {
                products.add(p);
            }
        }
        return products;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Boolean> createProduct(@RequestBody Product product)
    {
        try {
            this.defaultProductService.save(product);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(false,HttpStatus.CREATED);
        }
    }
}

