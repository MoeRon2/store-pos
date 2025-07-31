package com.nimetfidan.pos.app;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;
import com.nimetfidan.pos.logic.ProductService;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Product newProduct = new Product("firstItem", 55.5, 5, "1");
//        ProductDAO.addProducts(newProduct);
////        
//        for (Product product : ProductDAO.getProducts()) {
//            System.out.println(product);  // This will call product.toString()
//        }
//    }
    
//    Product product1 = new Product("Apple", 10.0, 50, "12345");
//    Product product2 = new Product("Banana", 5.0, 100, "67890");
//    Product product3 = new Product("Apple", 5.0, 100, "12345");
//    Cart cart = new Cart();
//    cart.addProduct(product1, 2);  // Add 2 Apples
//    cart.addProduct(product3, 2);  // Add 2 Apples
//    cart.addProduct(product2, 3);  // Add 3 Bananas
//
//    System.out.println("Total Price: " + cart.getTotalPrice());
//
//    cart.printCart();  // Prints all items in the cart
//    
//    System.out.println("----------------");
//    cart.removeProduct(product1);  // Remove Apples
//    cart.printCart();  // Prints remaining items
//    System.out.println("Total Price: " + cart.getTotalPrice());
//
//    cart.clearCart();  // Clear the cart
//    cart.printCart();  // Prints empty cart
//    System.out.println("Total Price: " + cart.getTotalPrice());
    
      ProductService.decreaseStock("1");
      System.out.println(ProductDAO.getItemFromDB("1"));
    }
    
}
