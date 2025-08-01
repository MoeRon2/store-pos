package com.nimetfidan.pos.logic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nimetfidan.pos.model.Product;


public class ExcelImporter {
    public static List<Product> readProductsFromExcel(File file) {
        List<Product> productList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheetAt(0); // First sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String barcode = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                double price = row.getCell(2).getNumericCellValue();
                int stock = (int) row.getCell(3).getNumericCellValue();

                Product product = new Product(name, price, stock, barcode);
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }
    
}

