package com.example.cashregisterapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProductServiceClass {
    ArrayList<ProductModel> productList = new ArrayList<>(0){};
    ArrayList<PurchaseModel> purchasedList = new ArrayList<>(0){};
    void purchased(ProductModel product, String quantity, Double totalAmount){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        purchasedList.add(new PurchaseModel(product,quantity,totalAmount,sdf.format(new Date())));
    }
    void updateQuantity(int index, String purchaseQuantity){
       int finalQuantity = Integer.parseInt(productList.get(index).quantity) - Integer.parseInt(purchaseQuantity);
        productList.get(index).quantity = Integer.toString(finalQuantity);
    }
}
