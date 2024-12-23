package com.example.cashregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class ManagerPortal extends AppCompatActivity {
    Button historyButton;
    Button addProduct;
    Button restockButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_portal);
        historyButton = findViewById(R.id.history);
        addProduct = findViewById(R.id.addProduct);
        restockButton = findViewById(R.id.restock);
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PurchaseList.class);
            startActivity(intent);
        });
        restockButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Restock.class);
            startActivity(intent);
        });
        addProduct.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddProduct.class);
            startActivity(intent);
        });


    }
}