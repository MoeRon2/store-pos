package com.nimetfidan.pos.logic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nimetfidan.pos.db.SaleItemsDAO;
import com.nimetfidan.pos.db.SalesDAO;
import com.nimetfidan.pos.model.Sale;
import com.nimetfidan.pos.model.SaleItem;


public class ExcelSalesExport {
	public static void createExcelFile(String fileName) {
        Workbook workbook = new XSSFWorkbook();

        createSalesSheet(workbook);
        createSaleItemsSheet(workbook);

        try (FileOutputStream fileOut = new FileOutputStream(fileName + "_" + LocalDate.now() +  ".xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("✅ Sales export complete: " + fileName + "_" + LocalDate.now() +  ".xlsx");
        } catch (IOException e) {
            System.out.println("❌ Failed to export Excel: " + e.getMessage());
        }
    }

    private static void createSalesSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Sales");
        List<Sale> salesList = SalesDAO.getSalesFromDB();

        // Header row
        Row header = sheet.createRow(0);
        String[] columns = { "ID", "Timestamp", "Total Amount", "Payment Type" };
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data rows
        int rowNum = 1;
        for (Sale sale : salesList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sale.getId());
            row.createCell(1).setCellValue(sale.getTimestamp());
            row.createCell(2).setCellValue(sale.getTotalAmount());
            row.createCell(3).setCellValue(sale.getPaymentType());
        }
    }

    private static void createSaleItemsSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("SaleItems");
        List<SaleItem> itemList = SaleItemsDAO.getAllSaleItems();

        // Header row
        Row header = sheet.createRow(0);
        String[] columns = { "Sale ID", "Barcode", "Name", "Price", "Quantity", "Total Price" };
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Data rows
        int rowNum = 1;
        for (SaleItem item : itemList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getSaleId());
            row.createCell(1).setCellValue(item.getBarcode());
            row.createCell(2).setCellValue(item.getName());
            row.createCell(3).setCellValue(item.getPrice());
            row.createCell(4).setCellValue(item.getQuantity());
            row.createCell(5).setCellValue(item.getPrice() * item.getQuantity());
        }
    }
}

