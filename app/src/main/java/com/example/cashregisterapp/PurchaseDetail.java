package com.example.cashregisterapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class PurchaseDetail extends AppCompatActivity {
    ServiceClass serviceClass;
    TextView nameText;
    TextView priceText;
    TextView dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);
        serviceClass = ((MyApp) getApplication()).myservice;
        nameText = findViewById(R.id.name);
        priceText = findViewById(R.id.price);
        dateText = findViewById(R.id.date);
        int number = getIntent().getIntExtra("selectedPurchase", 0);
        getPurchaseDetail(number);
    }


    @SuppressLint("SetTextI18n")
    void getPurchaseDetail(int index) {
        PurchaseModel purchase = serviceClass.getPurchaseDetail(index);
        nameText.setText("Product: "+ purchase.product.productName);
        priceText.setText("Price: "+purchase.totalAmount);
        dateText.setText("Purchase Date: "+purchase.purchasedDate);
    }
}