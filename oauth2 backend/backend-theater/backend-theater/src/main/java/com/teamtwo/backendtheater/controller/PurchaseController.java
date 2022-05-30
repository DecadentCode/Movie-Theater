package com.teamtwo.backendtheater.controller;

import com.teamtwo.backendtheater.model.Purchase;
import com.teamtwo.backendtheater.repository.PurchaseRepository;
import com.teamtwo.backendtheater.repository.projection.PurchaseTicket;
import com.teamtwo.backendtheater.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController
{
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;


    @PostMapping("/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        purchaseService.savePurchase(purchase);
        System.out.println("great success");
        return purchase;
    }

//    @GetMapping("/purchase")
//    public List<PurchaseTicket> getAllPurchasesOfUser(@RequestBody String email) {
//        return purchaseRepository.findAllPurchasesOfUser(email);
//    }

}
