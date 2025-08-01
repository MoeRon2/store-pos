package com.nimetfidan.pos.logic;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	        Object[][] productData = {
	            {"1234567891011", "Apple", 5.50, 20},
	            {"1002", "Banana", 3.25, 30},
	            {"1003", "Orange", 4.75, 25}
	        };

	        for (int i = 0; i < productData.length; i++) {
	            Row row = sheet.createRow(i + 1);
	            Object[] product = productData[i];
	            for (int j = 0; j < product.length; j++) {
	            	 if (product[j] instanceof String) {
	                     row.createCell(j).setCellValue((String) product[j]);
	                 } else if (product[j] instanceof Double) {
	                     row.createCell(j).setCellValue((Double) product[j]);
	                 } else if (product[j] instanceof Integer) {
	                     row.createCell(j).setCellValue((Integer) product[j]);
	                 }
	            }
	        }

	        // Auto-size columns
	        for (int i = 0; i < 4; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the workbook to a file
	        try (FileOutputStream fos = new FileOutputStream(fileName + ".xlsx")) {
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
