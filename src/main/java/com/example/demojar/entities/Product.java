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
    private String userCreatedEmailId;
    private String photosUrls="";
    private String name;
    private double price;
    private double sellingPrice;
    private String createdOn="";
    private String deadline="";
    private int quantity;
    private String details="";
    private int isSold=0;     //0 is false and 1 is true
    private String soldDate="";
    private String soldToUserName="";
    private String soldToUserEmail="";
    private String bidsOnThisProduct="";

    public int isSold(){
        return this.isSold;
    }

    public List<String> getBidsOnThisProduct() {
        return Arrays.asList(bidsOnThisProduct.split(","));
    }

    public void setBidsOnThisProduct(List<String> bidsOnThisProduct) {
        this.bidsOnThisProduct = String.join(",", bidsOnThisProduct);
    }
public List<String> getPhotoUrlsOnThisProduct() {
        return Arrays.asList(photosUrls.split(","));
    }

    public void setPhotoUrlsOnThisProduct(List<String> photoUrlsOnThisProduct) {
        this.photosUrls = String.join(",", photoUrlsOnThisProduct);
    }



}