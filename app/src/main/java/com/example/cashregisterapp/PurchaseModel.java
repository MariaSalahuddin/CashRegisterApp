package com.example.cashregisterapp;

import java.util.Date;

public class PurchaseModel {
    ProductModel product;
    String purchasedQuantity;
    Double totalAmount;
    String purchasedDate;
    public PurchaseModel(ProductModel product, String purchasedQuantity, Double totalAmount, String purchasedDate) {
        this.product = product;
        this.purchasedQuantity = purchasedQuantity;
        this.totalAmount = totalAmount;
        this.purchasedDate = purchasedDate;
    }
}
