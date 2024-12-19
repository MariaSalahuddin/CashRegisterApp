package com.example.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;


public class ProductListAdapter extends BaseAdapter {

    ArrayList<ProductModel> list;
    Context context;
    private int selectedPosition = -1;
    ProductListAdapter(ArrayList<ProductModel> l, Context c) {
        list = l;
        context = c;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ViewHolder pattern for better performance
        ViewHolder viewHolder;

        // If the view is not reused, inflate a new view and create a ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
            viewHolder = new ViewHolder();

            // Initialize the views and set them to the ViewHolder
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.amount = convertView.findViewById(R.id.amount);
            viewHolder.quantity = convertView.findViewById(R.id.quantity);

            // Set the ViewHolder to the convertView
            convertView.setTag(viewHolder);
        } else {
            // If convertView is recycled, get the ViewHolder from the convertView
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get product for the current position
        ProductModel product = list.get(position);
        viewHolder.name.setText(product.productName);
        viewHolder.quantity.setText(product.quantity);
        viewHolder.amount.setText(String.valueOf(product.amount));
        // Highlight the selected item
        if (position == selectedPosition) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.aqua)); // Change to any color
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white)); // Default background color
        }


        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView amount;
        TextView quantity;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged(); // Refresh the view
    }

}