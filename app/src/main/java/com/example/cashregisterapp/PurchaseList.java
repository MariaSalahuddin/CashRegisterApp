package com.example.cashregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

public class PurchaseList extends AppCompatActivity {
    RecyclerView recyclerView;
    PurchaseRecyclerAdapter adapter;
    ListView historyList;
    ServiceClass serviceClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceClass = ((MyApp) getApplication()).myservice;
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.historyListView);

        // Set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set Adapter
        adapter = new PurchaseRecyclerAdapter(serviceClass.purchasedList, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((item, position) -> {
            Intent intent = new Intent(this, PurchaseDetail.class);
            intent.putExtra("selectedPurchase", position);
            startActivity(intent);
        });

    }

}