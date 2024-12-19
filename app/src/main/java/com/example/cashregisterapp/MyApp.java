package com.example.cashregisterapp;

import android.app.Application;

public class MyApp extends Application {
    ServiceClass myservice = new ServiceClass();
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the array or ArrayList here
        myservice.productList.add(new ProductModel("Pants", "10", 12.0));
        myservice.productList.add(new ProductModel("Shirts", "15", 20.0));
        myservice.productList.add(new ProductModel("Scarfs", "5", 8.0));
    }
}
