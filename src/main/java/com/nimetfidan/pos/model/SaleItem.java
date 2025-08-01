package com.nimetfidan.pos.model;

import java.util.List;

public class SaleItem {
	    private String barcode;
	    private String name;
	    private double price;
	    private int quantity;

	    // Constructor
	    public SaleItem(String barcode, String name, double price, int quantity) {
	        this.barcode = barcode;
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Getters and setters
	    public String getBarcode() {
	        return barcode;
	    }
	
	    
	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    @Override
	    public String toString() {
	        return "SaleItem [barcode=" + barcode + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	    }
	}


