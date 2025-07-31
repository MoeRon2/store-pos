package com.nimetfidan.pos.model;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private int stock;
    private String barcode;

    public Product(String name, double price, int stock, String barcode) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.barcode = barcode;
    }

    
    @Override
    public String toString() {
        return name + " | " + price + "₺ | Stock: " + stock + " | Barcode: " + barcode;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return barcode.equals(product.barcode); // Assuming barcode is unique for a product
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(barcode); // Hash based on the barcode (since barcode is unique)
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getBarcode() { return barcode; }

}
