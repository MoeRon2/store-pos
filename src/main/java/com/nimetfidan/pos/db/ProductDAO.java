package com.nimetfidan.pos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nimetfidan.pos.model.Product;

public class ProductDAO {
	public static void addProducts(Product product) {
		String sql = "INSERT INTO products (name, price, stock, barcode) \r\n"
				+ "VALUES (?, ?, ?, ?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, product.getName());
			stmt.setDouble(2, product.getPrice());
			stmt.setInt(3, product.getStock());
			stmt.setString(4, product.getBarcode());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Product> getProducts() {
		String sql = "SELECT * FROM products;";
		List<Product> productList = new ArrayList<>();
		try (Connection conn = DB.getConnection();
			 Statement stmt = conn.createStatement()) {
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()) {
	            Product product = new Product(
	                     rs.getString("name"),
	                     rs.getDouble("price"),
	                     rs.getInt("stock"),
	                     rs.getString("barcode")
	                 );
	            productList.add(product);
             }
        } catch (SQLException e) {
			System.out.println("❌ Failed to connect: " + e.getMessage());
		}
	   return productList;
	}
	
	public static Product getItem(String barcode) {
		String sql = "SELECT * FROM products WHERE barcode = ?";
		try(Connection conn = DB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, barcode);
				
				// Execute the query and get the result set
		        ResultSet rs = stmt.executeQuery();
		        
		        // If a product is found, map the result to a Product object
		        if (rs.next()) {
		            String name = rs.getString("name");
		            double price = rs.getDouble("price");
		            int stock = rs.getInt("stock");
		            String barcodeFromDB = rs.getString("barcode");

		            // Create and return a Product object with the data
		            return new Product(name, price, stock, barcodeFromDB);
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
}
