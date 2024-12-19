package com.example.cashregisterapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class Restock extends AppCompatActivity {
    ProductListAdapter adapter;
    ListView productList;
    EditText restockText;
    ServiceClass serviceClass;
    Integer selectedProduct;
    Button okButton;
    Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        serviceClass = ((MyApp) getApplication()).myservice;
        productList = findViewById(R.id.productList);
        okButton = findViewById(R.id.ok);
        restockText = findViewById(R.id.restockQuantity);
        cancelButton = findViewById(R.id.cancel);
        adapter = new ProductListAdapter(serviceClass.productList, this);
        productList.setAdapter(adapter);
        productList.setOnItemClickListener((parentView, view, position, id) -> {
            adapter.setSelectedPosition(position);
            selectedProduct = position;
        });
        okButton.setOnClickListener(v -> {
            try {
                int restockQuantity = Integer.parseInt(restockText.getText().toString());
                if (selectedProduct == null)
                    Toast.makeText(Restock.this, "Please select Product to restock!", Toast.LENGTH_SHORT).show();
                else {
                    serviceClass.restock(selectedProduct, restockQuantity);
                    adapter.notifyDataSetChanged();
                    reset();
                    Toast.makeText(this, "Congratulations! Restock completed", Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                // Not a valid integer
                Toast.makeText(this, "Please enter a valid integer.", Toast.LENGTH_SHORT).show();
            }

        });
        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    void reset() {
        restockText.setText("");

    }

}