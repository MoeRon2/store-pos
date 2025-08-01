package com.nimetfidan.pos.ui;

import java.io.File;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.logic.ExcelImporter;
import com.nimetfidan.pos.logic.ExcelWriter;
import com.nimetfidan.pos.model.Product;


public class ImportFullStockDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6881015576156580523L;

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

            // Let's first make a backup
        	String timestamp = java.time.LocalDateTime.now().toString()
        	    .replace(":", "-").replace("T", "_").substring(0, 16); // yyyy-MM-dd_HH-mm
        	String backupFileName = "backup_before_import_" + timestamp;

        	ExcelWriter.createExcelFile(backupFileName);
        	
        	
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
            dispose();
            JOptionPane.showMessageDialog(parent, "Stock updated successfully.");
        }
        
        
        pack();
        
        setVisible(true);
        
     
	}
	

}
