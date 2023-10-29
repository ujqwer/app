package com.example.demojar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userCreatedId;
    private String name;
    private double price;
    private int quantity;
    private String details;
    private boolean isSold;
    private String soldDate;
    private String bidsOnThisProduct;

    public List<String> getBidsOnThisProduct() {
        return Arrays.asList(bidsOnThisProduct.split(","));
    }

    public void setBidsOnThisProduct(List<String> bidsOnThisProduct) {
        this.bidsOnThisProduct = String.join(",", bidsOnThisProduct);
    }



}