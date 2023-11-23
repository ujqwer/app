package com.example.demojar.services.impl;

import com.example.demojar.entities.Bid;
import com.example.demojar.repositories.BidRepository;
import com.example.demojar.services.DefaultBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultBidServiceImpl implements DefaultBidService {

    @Autowired
    private BidRepository bidRepository;
    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public Optional<Bid> findBidById(Integer id) {
        return bidRepository.findById(id);
    }

    @Override
    public List<Bid> findAllBids() {
        return bidRepository.findAll();
    }
}
