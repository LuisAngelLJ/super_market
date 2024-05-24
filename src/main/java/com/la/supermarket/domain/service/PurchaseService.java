package com.la.supermarket.domain.service;

import com.la.supermarket.domain.Purchase;
import com.la.supermarket.domain.repository.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        List<Purchase> purchases = purchaseRepository.getAll();
        logger.info("Listado de compras", purchases);
        return purchases;
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
