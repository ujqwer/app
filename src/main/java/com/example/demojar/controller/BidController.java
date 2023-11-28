package com.example.demojar.controller;

import com.example.demojar.entities.Bid;
import com.example.demojar.services.DefaultBidService;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/bid")
public class BidController {

    @Autowired
    UserController userController;
    @Autowired
    private DefaultBidService defaultBidService;
    @Autowired
    ProductController productController;



    @GetMapping("/hello")
    public String method() {
        return "Hello ujjman!!";
    }

    @PostMapping("/createBid")
    public ResponseEntity<Boolean> createBid(@RequestBody Bid bid) {


        this.defaultBidService.save(bid);
        Map<String,Integer> map=new HashMap<>();
        map.put("bidId",bid.getId());
        map.put("productId",bid.getForWhichProductId());
        ResponseEntity<Boolean> response1=productController.setBidsOnProduct(map);
        if(!Boolean.TRUE.equals(response1.getBody()))
        {
            return new ResponseEntity<>(false,HttpStatus.CREATED);
        }
        Map<String,String> map2=new HashMap<>();
        map2.put("bidId",bid.getId()+"");
        map2.put("email",bid.getUserCreatedEmailId());
        ResponseEntity<Boolean> response2=userController.setCurrentBids(map2);
        if(Boolean.TRUE.equals(response1.getBody()) && Boolean.TRUE.equals(response2.getBody())) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Bid> getBidById(@PathVariable Integer id) {
        return defaultBidService.findBidById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getBidsOfUserFromEmail")
    public List<Bid> getBidsOfUserFromEmail(@RequestParam String email) {
        List<Bid> list = defaultBidService.findAllBids();
        List<Bid> bids = new LinkedList<>();
        try {
            for (Bid p : list) {
                if (p.getUserCreatedEmailId().equals(email)) {
                    bids.add(p);
                }
            }
            return bids;
        } catch (Exception e) {
            return null;
        }

    }
    @GetMapping("/getBidsOnProduct")
    public List<Bid> getBidsOfUserFromEmail(@RequestParam Integer productId) {
        List<Bid> list = defaultBidService.findAllBids();
        List<Bid> bids = new LinkedList<>();
        try {
            for (Bid p : list) {
                if (p.getForWhichProductId()==productId) {
                    bids.add(p);
                }
            }
            return bids;
        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("/getAllBids")
    public List<Bid> getAllBids() {
        return defaultBidService.findAllBids();
    }

//    @GetMapping("/getAllUnsoldProducts")
//    public List<Bid> getAllUnsoldProducts() {
//        List<Bid> list = defaultProductService.findAllProducts();
//        List<Bid> products = new ArrayList<>();
//        for (Bid p : list) {
//            if (p.isSold() == 0) {
//                products.add(p);
//            }
//        }
//        return products;
//    }
//
//    @PostMapping("/createBid")
//    public ResponseEntity<Boolean> createBid(@RequestBody Bid bid) {
//
//        this.defaultBidService.save(bid);
//        Map<String,String> map=new HashMap<>();
//        map.put(bid.getUserCreatedEmailId(),bid.getId()+"");
//        userController.setItemsListed(map);
//
//
//        return new ResponseEntity<>(true, HttpStatus.CREATED);
//
//    }



}