package com.teamtwo.backendtheater.service;

import com.teamtwo.backendtheater.model.Purchase;
import com.teamtwo.backendtheater.repository.projection.PurchaseTicket;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);

    List<PurchaseTicket> findPurchaseItemsOfUser(String email);
}
