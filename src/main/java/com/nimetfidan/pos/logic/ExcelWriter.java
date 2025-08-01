package com.nimetfidan.pos.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

public class ExcelWriter {
	 public static void createExcelFile(String fileName){
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Product Data");

	        // Header row
	        Row header = sheet.createRow(0);
	        header.createCell(0).setCellValue("Barcode");
	        header.createCell(1).setCellValue("Name");
	        header.createCell(2).setCellValue("Price");
	        header.createCell(3).setCellValue("Stock");

	     // Set cell style for text (e.g., barcode)
	        CellStyle textStyle = workbook.createCellStyle();
	        DataFormat format = workbook.createDataFormat();
	        textStyle.setDataFormat(format.getFormat("@")); // "@" means plain text

	        // Apply the style to the entire barcode column (first column)
	        sheet.setDefaultColumnStyle(0, textStyle); // Optional: applies to whole col
	        
	        
	     // 2. Text format (or just default) for Name column
	     // Optional: leave default for column 1

	        
	        
	     // 3. Decimal number format for Price column
	        CellStyle priceStyle = workbook.createCellStyle();
	        priceStyle.setDataFormat(format.getFormat("0.00"));
	        sheet.setDefaultColumnStyle(2, priceStyle); // Column 2 = Price

	        // 4. Integer number format for Stock column
	        CellStyle stockStyle = workbook.createCellStyle();
	        stockStyle.setDataFormat(format.getFormat("0"));
	        sheet.setDefaultColumnStyle(3, stockStyle); // Column 3 = Stock
	        
	        // Sample data
	        List<Product> products = ProductDAO.getProductsFromDB();

	        int rowIndex = 1;
	        for (Product product : products) {
	                     Row row = sheet.createRow(rowIndex++);
	                     row.createCell(0).setCellValue(product.getBarcode());
	                     row.createCell(1).setCellValue(product.getName());
	                     row.createCell(2).setCellValue(product.getPrice());
	                     row.createCell(3).setCellValue(product.getStock());
	                 }		
	        		

	        // Auto-size columns
	        for (int i = 0; i < 4; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the workbook to a file
	        try (FileOutputStream fos = new FileOutputStream(fileName + "_" + LocalDate.now() +  ".xlsx")) {
	            workbook.write(fos);
	            System.out.println("✅ Excel file created successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Clean up
	        try {
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
