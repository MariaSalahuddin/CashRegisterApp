package com.example.cashregisterapp;

public class ProductModel {
    String productName;
    String quantity;
     Double amount;
    public ProductModel(String productName, String quantity, Double amount) {
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
    }
}
