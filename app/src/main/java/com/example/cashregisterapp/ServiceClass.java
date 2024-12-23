package com.example.cashregisterapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ServiceClass {
    ArrayList<ProductModel> productList = new ArrayList<>(0){};
    ArrayList<PurchaseModel> purchasedList = new ArrayList<>(0){};
    void purchased(ProductModel product, String quantity, Double totalAmount){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM-dd-yyyy HH:mm", Locale.getDefault());
        purchasedList.add(new PurchaseModel(product,quantity,totalAmount,sdf.format(new Date())));
    }
    void restock(int index, Integer purchaseQuantity){
        int finalQuantity = Integer.parseInt(productList.get(index).quantity) + purchaseQuantity;
        productList.get(index).quantity = Integer.toString(finalQuantity);
    }
    void addNewProduct(String name, String quantity, Double price){
      productList.add(new ProductModel(name,quantity,price));
    }
    void updateQuantity(int index, String purchaseQuantity){
            int finalQuantity = Integer.parseInt(productList.get(index).quantity) - Integer.parseInt(purchaseQuantity);
            productList.get(index).quantity = Integer.toString(finalQuantity);

    }
    PurchaseModel getPurchaseDetail(int index){
        return purchasedList.get(index);
    }
}
