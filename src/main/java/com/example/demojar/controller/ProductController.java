package com.example.demojar.controller;

import com.example.demojar.entities.Bid;
import com.example.demojar.entities.Product;
import com.example.demojar.entities.User;
import com.example.demojar.services.DefaultProductService;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    UserController userController;
    @Autowired
    private DefaultProductService defaultProductService;
    @Autowired
    private DefaultUserService defaultUserService;


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
        } catch (Exception e) {
            return null;
        }

    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return defaultProductService.findAllProducts();
    }

    @GetMapping("/getAllUnsoldProducts")
    public List<Product> getAllUnsoldProducts() {
        List<Product> list = defaultProductService.findAllProducts();
        List<Product> products = new ArrayList<>();
        for (Product p : list) {
            if (p.isSold() == 0) {
                products.add(p);
            }
        }
        return products;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Boolean> createProduct(@RequestBody Product product) {

        this.defaultProductService.save(product);
        Map<String,String> map=new HashMap<>();
        map.put("email",product.getUserCreatedEmailId());
        map.put("itemId",product.getId()+"");
        userController.setItemsListed(map);


        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }
    @PostMapping("/sellProduct")   // NOT COMPLETE
    public ResponseEntity<Boolean> sellProduct(@RequestBody Bid bid, @RequestParam  String buyerEmail) {
        int productId = bid.getForWhichProductId();
        Product product = this.defaultProductService.findProductById(productId).get();
        product.setIsSold(1);
        String sellerEmail = product.getUserCreatedEmailId();
        product.setSoldToUserName(userController.getUserFromEmail(bid.getUserCreatedEmailId()).getBody().getName());
        product.setSoldToUserEmail(bid.getUserCreatedEmailId());
        product.setSellingPrice(bid.getPriceOfBid());
        product.setSoldDate(bid.getTimestampOfAccepting());
        this.defaultProductService.save(product);

        Map<String,String> map=new HashMap<>();
        map.put("sellerEmail",sellerEmail);
        map.put("itemId",productId+"");
        userController.setItemsSold(map);
        userController.reduceItemsListed(map);

        map = new HashMap<>();
        map.put("sellerEmail",sellerEmail);
        map.put("buyerEmail",buyerEmail);
        map.put("priceOfProduct",bid.getPriceOfBid()+"");
        map.put("itemId",productId+"");
        userController.reduceAndAddMoneyToBuyerAndSeller(map);
        userController.addProductToBuyListOfBuyer(map);

        return new ResponseEntity<>(true, HttpStatus.CREATED);

    }

    @GetMapping("/getBidsOnProduct")
    public ResponseEntity<List<String>> getBidsOnProduct(@RequestParam Integer id) {
        try {
            Product product = this.defaultProductService.findProductById(id).get();
            return new ResponseEntity<>(product.getBidsOnThisProduct(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }

    }

    @PostMapping("/setBidsOnProduct")
    public ResponseEntity<Boolean> setBidsOnProduct(@RequestBody Map<String,Integer> request) {
        try {
            Product product = this.defaultProductService.findProductById(request.get("productId")).get();
            List<String> bids = new ArrayList<>(product.getBidsOnThisProduct());
            bids.add(request.get("bidId")+"");
            product.setBidsOnThisProduct(bids);
            this.defaultProductService.save(product);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getPhotosUrlsOnProduct")
    public ResponseEntity<List<String>> getPhotosUrlsOnProduct(@RequestParam Integer id) {
        try {
            Product product = this.defaultProductService.findProductById(id).get();
            return new ResponseEntity<>(product.getPhotoUrlsOnThisProduct(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }

    }

    @PostMapping("/setPhotosUrlsProduct")
    public ResponseEntity<Boolean> setPhotosUrlsOnProduct(@RequestBody Integer id, List<String> urls) {
        try {
            Product product = this.defaultProductService.findProductById(id).get();
            product.setPhotoUrlsOnThisProduct(urls);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }

    }
}

