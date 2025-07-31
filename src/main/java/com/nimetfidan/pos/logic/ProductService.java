package com.nimetfidan.pos.logic;


import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

public class ProductService {
	public static void increaseStock(String barcode) {
		Product productInQuestion = ProductDAO.getItemFromDB(barcode);
		ProductDAO.updateStock(barcode, productInQuestion.getStock() + 1);
	}
	
	public static void increaseStock(String barcode, int incrementAmount) {
		Product productInQuestion = ProductDAO.getItemFromDB(barcode);
		ProductDAO.updateStock(barcode, productInQuestion.getStock() + incrementAmount);
	}
	
	public static void decreaseStock(String barcode) {
		Product productInQuestion = ProductDAO.getItemFromDB(barcode);
		int stock = productInQuestion.getStock();
		if (stock == 0) {
			System.out.println("Not in stock");
			return;
		}
		ProductDAO.updateStock(barcode, productInQuestion.getStock() - 1);
	}
	public static void decreaseStock(String barcode, int decrementAmount) {
		Product productInQuestion = ProductDAO.getItemFromDB(barcode);
		int stock = productInQuestion.getStock();
		if (stock - decrementAmount < 0) {
			System.out.println("Not enough products in stock");
			return;
		}
		ProductDAO.updateStock(barcode, productInQuestion.getStock() - decrementAmount);
	}
}
