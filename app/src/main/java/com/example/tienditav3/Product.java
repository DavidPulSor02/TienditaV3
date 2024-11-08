package com.example.tienditav3;

public class Product {
    private String name;
    private double price;
    private int imageResource;
    private int quantity;

    // Constructor
    public Product(String name, double price, int quantity, int imageResource) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }


    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
