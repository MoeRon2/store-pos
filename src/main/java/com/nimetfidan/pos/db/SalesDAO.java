package com.nimetfidan.pos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nimetfidan.pos.model.Product;
import com.nimetfidan.pos.model.Sale;

// With this we will keep track of sales, and saled items.



public class SalesDAO {
	public static int addSalesToDB(double TotalAmount, String paymentType) {
		int saleId = -1; 
		String sql = "INSERT INTO sales (total_amount, payment_type) \r\n"
				+ "VALUES (?, ?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDouble(1, TotalAmount);
			stmt.setString(2, paymentType);
			stmt.executeUpdate();
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			 if (generatedKeys.next()) {
			        saleId = generatedKeys.getInt(1); // Get auto-incremented ID
			    } else {
			        throw new SQLException("Inserting sale failed, no ID obtained.");
			    }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saleId;
	

	}
	public static void clearSalesTable() {
		String sql = "DELETE FROM sales;";
		try (Connection conn = DB.getConnection();
			 Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("❌ Failed to clear sales table: " + e.getMessage());
		}
	}
	
	
	public static List<Sale> getSalesFromDB() {
		String sql = "SELECT * FROM sales ORDER by timestamp DESC;";
		List<Sale> saleList = new ArrayList<>();
		try (Connection conn = DB.getConnection();
			 Statement stmt = conn.createStatement()) {
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()) {
	            Sale sale = new Sale(
	            		 rs.getInt("id"),
	            		 rs.getTimestamp("timestamp").toString(),
	                     rs.getDouble("total_amount"),
	                     rs.getString("payment_type")
	                 );
	            saleList.add(sale);
             }
        } catch (SQLException e) {
			System.out.println("❌ Failed to connect: " + e.getMessage());
		}
	   return saleList;
	}
	

}
