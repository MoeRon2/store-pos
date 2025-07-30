package com.nimetfidan.pos.app;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

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
//        
        for (Product product : ProductDAO.getProducts()) {
            System.out.println(product);  // This will call product.toString()
        }
    }
}
