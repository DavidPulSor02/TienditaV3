package com.example.tienditav3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private final ArrayList<Product> cartItems;
    private final Context context;
    private final OnCartItemQuantityChangedListener listener; // Interfaz para escuchar los cambios

    public interface OnCartItemQuantityChangedListener {
        void onQuantityChanged();
    }

    public CartItemAdapter(Context context, ArrayList<Product> cartItems, OnCartItemQuantityChangedListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.itemName.setText(product.getName());
        holder.itemPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.itemQuantity.setText(String.valueOf(product.getQuantity()));
        holder.itemImage.setImageResource(product.getImageResource());

        holder.buttonIncrement.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            holder.itemQuantity.setText(String.valueOf(product.getQuantity()));
            listener.onQuantityChanged(); // Notifica que la cantidad ha cambiado
        });

        holder.buttonDecrement.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                holder.itemQuantity.setText(String.valueOf(product.getQuantity()));
                listener.onQuantityChanged(); // Notifica que la cantidad ha cambiado
            } else {
                Toast.makeText(context, "Cantidad mínima alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQuantity;
        ImageView itemImage;
        Button buttonIncrement, buttonDecrement;

        // Constructor del ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.productImage);
            itemName = itemView.findViewById(R.id.productName);
            itemPrice = itemView.findViewById(R.id.productPrice);
            buttonIncrement = itemView.findViewById(R.id.button_increment_item);
            buttonDecrement = itemView.findViewById(R.id.button_decrement_item);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
        }
    }
}
