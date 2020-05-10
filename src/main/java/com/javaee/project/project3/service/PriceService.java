package com.javaee.project.project3.service;

import com.javaee.project.project3.model.Price;
import com.javaee.project.project3.model.Product;
import com.javaee.project.project3.model.Store;
import com.javaee.project.project3.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepo priceRepo;

    public List<Price> getPricesByProduct(Long productId) {
        return priceRepo.getPriceByProductId(productId);
    }

    public Price createPrice(Double priceDouble, Product product, Store store) {
        Price price=new Price();
        price.setPrice(priceDouble);
        price.setProduct(product);
        price.setStore(store);
        return priceRepo.save(price);
    }

    public boolean isPriceExists(Long productId,Long storeId){
        return priceRepo.isPriceExists(productId,storeId);
    }

    public Price getPriceById(Long priceId) {
       return priceRepo.findById(priceId).orElseThrow(()->new RuntimeException());
    }
}
