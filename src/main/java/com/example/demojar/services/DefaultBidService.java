package com.example.demojar.services;

import com.example.demojar.entities.Bid;

import java.util.List;
import java.util.Optional;

public interface DefaultBidService {
    Bid save(Bid bid);
    Optional<Bid> findBidById(Integer id);
    List<Bid> findAllBids();
}
