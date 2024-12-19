package com.example.cashregisterapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseRecyclerAdapter extends RecyclerView.Adapter<PurchaseRecyclerAdapter.ViewHolder> {

    private ArrayList<PurchaseModel> list;
    private Context context;
    private OnItemClickListener clickListener;
    public interface OnItemClickListener {
        void onItemClick(PurchaseModel item, int position);
    }

    public PurchaseRecyclerAdapter(ArrayList<PurchaseModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    // ViewHolder class to hold the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView amount;
        TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            amount = itemView.findViewById(R.id.amount);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current item
        PurchaseModel history = list.get(position);

        // Bind the data to the views
        holder.name.setText(history.product.productName);
        holder.quantity.setText(history.purchasedQuantity);
        holder.amount.setText(String.valueOf(history.totalAmount));

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(history, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}