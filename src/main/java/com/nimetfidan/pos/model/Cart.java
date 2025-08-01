package com.nimetfidan.pos.model;

import java.util.HashMap;
import java.util.Map;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.db.SaleItemsDAO;

public class Cart {
    private Map<Product, Integer> cartItems;
    private double cashDiscount = 0.0;
    private double cashDiscountPercentage = 0.0; // Percentage discount
    
    
    private double discount = 0.0;
    private double totalPrice;
    
    public Cart() {
        this.cartItems = new HashMap<>();
    }

    public double getDiscount() {
    	return discount;
    }
    
    
    
    public void applyCashDiscount(double discount) {
		this.cashDiscount = discount;
		this.cashDiscountPercentage = 0.0; // Reset percentage discount when cash discount is applied
		
    }
    
    public void applyDiscountPercentage(double discountPercentage) {
    	this.cashDiscount = 0.0; // Reset cash discount when percentage discount is applied
    	this.cashDiscountPercentage = discountPercentage;
    }
    
    // Add product to the cart
    public void changeProductQuantity(Product product, int quantity) {
        // Check if product is already in the cart
        if (cartItems.containsKey(product)) {
            // Update the quantity
        	if (product.getStock() < cartItems.get(product) + quantity) {
        		System.out.println("Not enough stock available for " + product.getName() + ". Available stock: " + product.getStock());
        		return; // Not enough stock available
        	}
            cartItems.put(product, cartItems.get(product) + quantity);
        } else {
            // Add new product to the cart
            cartItems.put(product, quantity);
        }
    }
    
    public void changeProductQuantity(Product product) {
    	changeProductQuantity(product, 1);
    }
    
    public void finishSale(int saleId) {
    	for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            ProductDAO.updateStock(product.getBarcode(), product.getStock() - quantity);
        }
    	
		SaleItemsDAO.addSaleItemsToDB(saleId, this); // Save sale items to database
		
	}
    

    public Map<Product, Integer> getItems() {
		return cartItems;
	}
    
    
    
    // Remove product from the cart
    public void removeProduct(Product product) {
        cartItems.remove(product);
    }

    // Clear the entire cart
    public void clearCart() {
        cartItems.clear();
        cashDiscount = 0.0; // Reset cash discount
        
    }

    // Get the total price of all products in the cart
    public double getTotalPrice() {
        totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        
        
        applyDiscount();
        
        return totalPrice;// Apply cash discount if any
    }
    
    
    public void applyDiscount() {
    	discount = 0.0;
    	
    	
    	
		if (cashDiscount > 0) {
			discount = cashDiscount; // Store the cash discount for reference
			if (discount > totalPrice) {
				System.out.println("Error ");; // Ensure discount does not exceed total price
			}
			totalPrice -= cashDiscount; // Apply cash discount
			
		}
		
		if (cashDiscountPercentage > 0) { // Apply percentage discount
			discount = (totalPrice * (cashDiscountPercentage / 100)); // Store the percentage discount for reference
			if (discount > totalPrice) {
				System.out.println("Error ");; // Ensure discount does not exceed total price
			}
			System.out.println("Discount: " + discount);
			totalPrice -= discount;
		}
		
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

