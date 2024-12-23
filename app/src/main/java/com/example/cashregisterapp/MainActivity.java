package com.example.cashregisterapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Restock.RestockAnItemListener, AddProduct.NewProductListener {
    ProductListAdapter adapter;
    ListView productList;
    Button buyButton;
    TextView productTypeTextView;
    TextView totalTextView;
    TextView quantityTextview;
    String quantity = "";
    Integer selectedPosition;
    Button managerButton;
    Double total = 0.0;
    ProductModel selectedProduct;
    ServiceClass serviceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceClass = ((MyApp) getApplication()).myservice;
        productList = findViewById(R.id.productList);
        quantityTextview = findViewById(R.id.quantity);
        productTypeTextView = findViewById(R.id.productType);
        totalTextView = findViewById(R.id.total);
        buyButton = findViewById(R.id.buy);
        managerButton = findViewById(R.id.managerButton);
        findViewById(R.id.buttonClear).setOnClickListener(v -> {
            reset();
        });
        adapter = new ProductListAdapter(serviceClass.productList, this);
        productList.setAdapter(adapter);

        int[] numberIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9};

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(this::numberClicked);
        }

        productList.setOnItemClickListener((parentView, view, position, id) -> {
            adapter.setSelectedPosition(position);
            selectedProduct = serviceClass.productList.get(position);
            selectedPosition = position;
            productTypeTextView.setText(selectedProduct.productName);
            reset();
        });
        managerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ManagerPortal.class);
            startActivity(intent);

        });
        buyButton.setOnClickListener(v -> {
          if(selectedProduct == null || quantity.isEmpty())
              Toast.makeText(MainActivity.this, "All fields are Required!", Toast.LENGTH_SHORT).show();
         else if (Integer.parseInt(quantity) > Integer.parseInt(selectedProduct.quantity))
              Toast.makeText(MainActivity.this, "Not enough quantity of " + selectedProduct.productName + " in stock!", Toast.LENGTH_SHORT).show();
          else {
              serviceClass.purchased(selectedProduct,quantity,total);
              serviceClass.updateQuantity(selectedPosition, quantity);
              AlertDialog.Builder builder = new AlertDialog.Builder(this);

              // Set title, message, and button
              builder.setTitle("Thankyou For Your Purchase!");
              builder.setMessage("Your Purchase is "+ quantity+" "+ selectedProduct.productName +" for "+total);
              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @SuppressLint("SetTextI18n")
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      // Action when OK button is clicked
                      dialog.dismiss(); // Close the dialog
                      reset();
                      adapter.notifyDataSetChanged();
                  }
              });

              // Show the alert dialog
              AlertDialog alertDialog = builder.create();
              alertDialog.show();
          }
        });

    }


    @SuppressLint("SetTextI18n")
    private void numberClicked(View view) {
        Button button = (Button) view;
        quantity += button.getText().toString();
        quantityTextview.setText(quantity);
        if (selectedProduct != null) {
            total = Integer.parseInt(quantity) * selectedProduct.amount;
            if (Integer.parseInt(quantity) > Integer.parseInt(selectedProduct.quantity))
                Toast.makeText(MainActivity.this, "Not enough quantity of " + selectedProduct.productName + " in stock!", Toast.LENGTH_SHORT).show();
        }
        totalTextView.setText(total.toString());
    }

    @SuppressLint("SetTextI18n")
    private void reset() {
        quantity = "";
        total = 0.0;
        quantityTextview.setText("Quantity");
        totalTextView.setText("Total");
    }

    @Override
    public void itemRestocked() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void productAdded() {
        adapter.notifyDataSetChanged();
    }
}