package com.example.tienditav3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> cart = new ArrayList<>(); // Lista para almacenar productos añadidos al carrito
    private TextView productPrice1, productPrice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas de precios y botones
        productPrice1 = findViewById(R.id.productPrice1);
        productPrice2 = findViewById(R.id.productPrice2);
        Button addToCartButton1 = findViewById(R.id.addToCartButton1);
        Button addToCartButton2 = findViewById(R.id.addToCartButton2);
        ImageButton cartButton = findViewById(R.id.carrito); // Inicializar el ImageButton de carrito

        // Establecer precios (solo ejemplos)
        productPrice1.setText("Precio: $20.0");
        productPrice2.setText("Precio: $25.0");

        // Evento para añadir el primer producto al carrito
        addToCartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product1 = new Product("Cocacola", 20.0);
                addToCart(product1);
            }
        });


        // Evento para añadir el segundo producto al carrito
        addToCartButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product2 = new Product("Monster", 25.0);
                addToCart(product2);
            }
        });

        // Evento para abrir CartActivity cuando se hace clic en el botón de carrito
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

        // Configuración del ViewPager2 para el carrusel de productos
        ViewPager2 productCarousel = findViewById(R.id.productCarousel);

        // Lista de imágenes (asegúrate de que los recursos existan en `res/drawable`)
        List<Integer> imageList = Arrays.asList(
                R.drawable.promo,
                R.drawable.promo2,
                R.drawable.promo3
        );

        // Configura el adaptador
        CarouselAdapter adapter = new CarouselAdapter(imageList);
        productCarousel.setAdapter(adapter);
    }

    private void addToCart(Product product) {
        cart.add(product);
        Toast.makeText(this, product.getName() + " añadido al carrito", Toast.LENGTH_SHORT).show();
    }

    private double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }

    // Método para mostrar el total del carrito
    private void showCartTotal() {
        double total = calculateTotal();
        Toast.makeText(this, "Total en carrito: $" + total, Toast.LENGTH_SHORT).show();
    }

    // Clase interna para los productos
    private class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

}
