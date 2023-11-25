package com.example.demojar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private int moneyLeft=10000;
    private String itemsPurchased = "";
    private String itemsListed = "";    //current listings
    private String visibleTo = "";
    private String itemsSold = "";   //past listings
    private String currentBids = "";
    private String pastBids = "";
    private int totalEarnings;
    private int moneySpent;
    private String password;



    public List<String> getItemsPurchased() {
        return Arrays.asList(itemsPurchased.split(","));
    }

    public void setItemsPurchased(List<String> itemsPurchased) {
        this.itemsPurchased = String.join(",", itemsPurchased);
    }

    public List<String> getItemsListed() {
        return Arrays.asList(itemsListed.split(","));
    }

    public void setItemsListed(List<String> itemsListed) {
        this.itemsListed = String.join(",", itemsListed);
    }

   public List<String> getVisibleTo() {
        return Arrays.asList(visibleTo.split(","));
    }

    public void setVisibleTo(List<String> visibleTo) {
        this.visibleTo = String.join(",", visibleTo);
    }
  public List<String> getItemsSold() {
        return Arrays.asList(itemsSold.split(","));
    }

    public void setItemsSold(List<String> itemsSold) {
        this.itemsSold= String.join(",", itemsSold);
    }
  public List<String> getCurrentBids() {
        return Arrays.asList(currentBids.split(","));
    }

    public void setCurrentBids(List<String> currentBids) {
        this.currentBids= String.join(",", currentBids);
    }












}