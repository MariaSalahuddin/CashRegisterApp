package com.example.cashregisterapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProduct extends AppCompatActivity {
    EditText name;
    EditText price;
    EditText quantity;
    Button add;
    Button cancel;
    ServiceClass serviceClass;
    interface NewProductListener {
        void productAdded();
    }
    NewProductListener newProductListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        serviceClass = ((MyApp) getApplication()).myservice;
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        add = findViewById(R.id.addButton);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            finish();
        });
        add.setOnClickListener(v -> {
            if (!name.getText().toString().isEmpty() && !quantity.getText().toString().isEmpty() && !price.getText().toString().isEmpty()) {
                if(isDouble(price.getText().toString()) && isInteger(quantity.getText().toString())){
                    serviceClass.addNewProduct(name.getText().toString(), quantity.getText().toString(), Double.parseDouble(quantity.getText().toString()));
                    name.setText("");
                    quantity.setText("");
                    price.setText("");
                    Toast.makeText(this, "New Product Added Successfully!", Toast.LENGTH_SHORT).show();
                    if (newProductListener != null) {
                        newProductListener.productAdded();
                    }
                }
               else {
                    Toast.makeText(this, "Please enter a valid Input.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}