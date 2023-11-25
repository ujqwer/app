package com.example.demojar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userCreatedEmailId="";
    private int forWhichProductId;
    private double priceOfBid;
    private String timestampOfCreation="";
    private String timestampOfAccepting="";
    private int isExpired=0;


}