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
         try (Statement stmt = conn.createStatement()) {
             stmt.execute(createProductsTable);
         } catch (SQLException e) {
 			System.out.println("❌ Failed to connect: " + e.getMessage());
 			throw e;
 		}
     }
}
