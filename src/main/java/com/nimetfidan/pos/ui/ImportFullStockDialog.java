package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.logic.ExcelImporter;
import com.nimetfidan.pos.model.Product;


public class ImportFullStockDialog extends JDialog {
	
	public ImportFullStockDialog(JFrame parent) {
//		super(parent, "Import Full Stock", true);
//		setPreferredSize(new Dimension(400, 300));
//        setLocationRelativeTo(parent);
//        
        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Update Full Stock (Replace & Clear)");
        int result = fileChooser.showOpenDialog(parent);
        
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            List<Product> products = ExcelImporter.readProductsFromExcel(selectedFile);
            
            // Let's clear our table
            ProductDAO.clearProductsTable();
            
            for (Product p : products) {
                System.out.println(p);
                // Add product to the databa
                ProductDAO.addProductsToDB(p);
                
                // Optional: call DAO to insert or update here
            }
        }
	}

}
