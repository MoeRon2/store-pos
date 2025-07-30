package com.nimetfidan.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:test.db"; // Creates test.db in project root if it doesn't exist

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				System.out.println("✅ Connection to SQLite has been established.");
			}
		} catch (SQLException e) {
			System.out.println("❌ Failed to connect: " + e.getMessage());
		}
	}
}
