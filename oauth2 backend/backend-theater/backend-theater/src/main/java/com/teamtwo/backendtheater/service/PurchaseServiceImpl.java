package com.teamtwo.backendtheater.service;

import com.teamtwo.backendtheater.model.Purchase;
import com.teamtwo.backendtheater.repository.PurchaseRepository;
import com.teamtwo.backendtheater.repository.projection.PurchaseTicket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        purchase.setPurchaseTime(LocalDateTime.now());

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<PurchaseTicket> findPurchaseItemsOfUser(String email) {
        return purchaseRepository.findAllPurchasesOfUser(email);
    }

}
