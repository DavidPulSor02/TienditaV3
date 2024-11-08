package com.example.tienditav3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarritoActivity extends AppCompatActivity implements CartItemAdapter.OnCartItemQuantityChangedListener {

    private RecyclerView cartItemsListView;
    private CartItemAdapter adapter;
    private ArrayList<Product> cartItems;
    private EditText etPromoCode;
    private TextView tvTotalAmount;
    private Button btnCheckout;
    private double totalAmount = 0.0;
    private final double discountPercentage = 0.10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Inicialización de vistas
        cartItemsListView = findViewById(R.id.cartItemsListView);
        cartItemsListView.setLayoutManager(new LinearLayoutManager(this));

        etPromoCode = findViewById(R.id.etPromoCode);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Inicializar los productos del carrito
        cartItems = new ArrayList<>();
        cartItems.add(new Product("Cocacola", 32.00, 1, R.drawable.cocacola));
        cartItems.add(new Product("Monster", 26.00, 1, R.drawable.monster));

        // Calcular el total
        calculateTotalAmount();

        // Configurar el adaptador para el RecyclerView
        adapter = new CartItemAdapter(this, cartItems, this);
        cartItemsListView.setAdapter(adapter);

        // Mostrar el total inicial
        updateTotalAmount(totalAmount);

        // Lógica para aplicar el código promocional
        etPromoCode.setOnEditorActionListener((v, actionId, event) -> {
            String promoCode = etPromoCode.getText().toString().trim();
            if (isValidPromoCode(promoCode)) {
                double discountedAmount = totalAmount - (totalAmount * discountPercentage);
                updateTotalAmount(discountedAmount);
            } else {
                updateTotalAmount(totalAmount);
            }
            return true;
        });

        // Lógica para el botón de pago
        btnCheckout.setOnClickListener(v -> processPayment());
    }

    private void calculateTotalAmount() {
        totalAmount = 0.0;
        for (Product product : cartItems) {
            totalAmount += product.getPrice() * product.getQuantity();
        }
    }

    private void updateTotalAmount(double amount) {
        tvTotalAmount.setText("Total: $" + String.format("%.2f", amount));
    }

    private boolean isValidPromoCode(String promoCode) {
        return promoCode.equals("DESCUENTO10");
    }

    private void processPayment() {
        String confirmationMessage = "Pago realizado con éxito.";
        Toast.makeText(this, confirmationMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuantityChanged() {
        // Recalcular el total cuando la cantidad de cualquier producto cambie
        calculateTotalAmount();
        updateTotalAmount(totalAmount);
    }
}
