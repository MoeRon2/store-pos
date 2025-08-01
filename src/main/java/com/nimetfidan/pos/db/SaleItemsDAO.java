package com.nimetfidan.pos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;
import com.nimetfidan.pos.model.SaleItem;

public class SaleItemsDAO {

	public static void addSaleItemsToDB(int saleId, Cart cart) {
		String sql = "INSERT INTO sale_items (sale_id, barcode, name, price, quantity) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
				Product p = entry.getKey();
				int quantity = entry.getValue();
				stmt.setInt(1, saleId);
				stmt.setString(2, p.getBarcode());
				stmt.setString(3, p.getName());
				stmt.setDouble(4, p.getPrice());
				stmt.setInt(5, quantity); // Assuming you added a quantity field in Product
				stmt.executeUpdate(); // Execute the insert statement
			}

		} catch (SQLException e) {
			System.out.println("❌ Failed to insert sale items: " + e.getMessage());
		}
	}
	

	public static List<SaleItem> getSaleItemsForSaleId(int saleId) {
		String sql = "SELECT * FROM sale_items WHERE sale_id = ?";
		List<SaleItem> saleItemsList = new ArrayList<>();

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, saleId); // Set the sale_id parameter
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Extracting data from the result set
				int saleIdFromDB = rs.getInt("sale_id");
				String barcode = rs.getString("barcode");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");

				// Create a SaleItem object and add it to the list
				SaleItem saleItem = new SaleItem(saleIdFromDB, barcode, name, price, quantity);
				saleItemsList.add(saleItem);
			}

		} catch (SQLException e) {
			System.out.println("❌ Failed to get sale items: " + e.getMessage());
		}

		return saleItemsList;
	}
	
	public static List<SaleItem> getAllSaleItems() {
		String sql = "SELECT * FROM sale_items ORDER by sale_id DESC;";
		List<SaleItem> saleItemsList = new ArrayList<>();

		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// Extracting data from the result set
				int saleId = rs.getInt("sale_id");
				String barcode = rs.getString("barcode");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");

				// Create a SaleItem object and add it to the list
				SaleItem saleItem = new SaleItem(saleId, barcode, name, price, quantity);
				saleItemsList.add(saleItem);
			}

		} catch (SQLException e) {
			System.out.println("❌ Failed to get all sale items: " + e.getMessage());
		}

		return saleItemsList;
	}

}
