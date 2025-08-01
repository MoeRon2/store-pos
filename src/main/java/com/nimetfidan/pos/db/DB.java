package com.nimetfidan.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static final String url = "jdbc:sqlite:pos.db"; // Creates pos.db in project root if it doesn't exist
	private static boolean initialized = false;
	
    public static Connection getConnection() throws SQLException {
    	Connection conn = null;
    	try {
    		conn = DriverManager.getConnection(url);
    	} catch (SQLException e) {
			System.out.println("❌ Failed to connect: " + e.getMessage());
			throw e;
		}
    	
        if (!initialized) {
            initializeDatabase(conn);
            initialized = true;
        }
        return conn;
     }

     private static void initializeDatabase(Connection conn) throws SQLException {
    	 String createProductsTable = 
    			 """
    	            CREATE TABLE IF NOT EXISTS products (
    	                id INTEGER PRIMARY KEY AUTOINCREMENT,
    	                name TEXT NOT NULL,
    	                price REAL NOT NULL,
    	                stock INTEGER NOT NULL,
    	                barcode TEXT UNIQUE NOT NULL
    	            );
    	 		""";
         
    	 String createSalesTable = """
    		        CREATE TABLE IF NOT EXISTS sales (
    		            id INTEGER PRIMARY KEY AUTOINCREMENT,
    		            timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    		            total_amount REAL NOT NULL,
    		            payment_type TEXT
    		        );
    		    """;

         String createSaleItemsTable = """
    		        CREATE TABLE IF NOT EXISTS sale_items (
    		            id INTEGER PRIMARY KEY AUTOINCREMENT,
    		            sale_id INTEGER NOT NULL,
    		            barcode TEXT NOT NULL,
    		            name TEXT NOT NULL,
    		            price REAL NOT NULL,
    		            quantity INTEGER NOT NULL,
    		            FOREIGN KEY (sale_id) REFERENCES sales(id)
    		        );
    		    """;
    	 
    	 
    	 try (Statement stmt = conn.createStatement()) {
             stmt.execute(createProductsTable);
             stmt.execute(createSalesTable);
             stmt.execute(createSaleItemsTable);
         } catch (SQLException e) {
 			System.out.println("❌ Failed to connect: " + e.getMessage());
 			throw e;
 		}
     }
}
