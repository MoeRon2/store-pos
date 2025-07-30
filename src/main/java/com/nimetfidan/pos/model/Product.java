package com.nimetfidan.pos.model;

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
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getBarcode() { return barcode; }

}
