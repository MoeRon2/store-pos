package com.nimetfidan.pos.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cartItems;

    public Cart() {
        this.cartItems = new HashMap<>();
    }

    // Add product to the cart
    public void addProduct(Product product, int quantity) {
        // Check if product is already in the cart
        if (cartItems.containsKey(product)) {
            // Update the quantity
            cartItems.put(product, cartItems.get(product) + quantity);
        } else {
            // Add new product to the cart
            cartItems.put(product, quantity);
        }
    }

    // Remove product from the cart
    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    // Clear the entire cart
    public void clearCart() {
        cartItems.clear();
    }

    // Get the total price of all products in the cart
    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    // Get the quantity of a specific product in the cart
    public int getQuantity(Product product) {
        return cartItems.getOrDefault(product, 0);
    }

    // Print all cart items (for debugging or testing purposes)
    public void printCart() {
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            System.out.println(entry.getKey() + " x " + entry.getValue());
        }
    }
}

