package com.example.app.service;

import com.example.app.model.ProductRecord;
import com.example.app.repo.ProductRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRecordService {
    @Autowired
    private ProductRecordRepo prodRecordRepo;

    public void logAction(int prodId, String action,String prodName, double price){
        ProductRecord record= new ProductRecord(prodId,action,prodName,price);

        prodRecordRepo.save(record);
    }
}
