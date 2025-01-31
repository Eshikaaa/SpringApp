package com.example.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name="productRecord")
public class ProductRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    private int prodId;
    private String action;
    private String prodName;
    private double price;

    public ProductRecord(){

    }

//    public ProductRecord(int recordId, int prodId, String action, String prodName, double price) {
//        this.recordId = recordId;
//        this.prodId = prodId;
//        this.action = action;
//        this.prodName = prodName;
//        this.price = price;
//    }


    public ProductRecord(int prodId, String action, String prodName, double price) {
        this.prodId = prodId;
        this.action = action;
        this.prodName = prodName;
        this.price = price;

    }
}
