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

public class ImportPartialStockDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8132683718477801963L;

	public ImportPartialStockDialog(JFrame parent) {
//        super(parent, "Import Partial Stock", true);

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Import Partial Stock (Add/Update)");

        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
        	
        	 // Let's first make a backup
        	String timestamp = java.time.LocalDateTime.now().toString()
        	    .replace(":", "-").replace("T", "_").substring(0, 16); // yyyy-MM-dd_HH-mm
        	String backupFileName = "backup_before_import_" + timestamp;

        	ExcelWriter.createExcelFile(backupFileName);
        	
            File selectedFile = fileChooser.getSelectedFile();
            List<Product> products = ExcelImporter.readProductsFromExcel(selectedFile);

            for (Product p : products) {
                System.out.println(p);
                // Instead of clearing, we update or insert each product
                ProductDAO.addProductsToDB(p);
            }

            JOptionPane.showMessageDialog(parent, "Partial stock update completed.");
        }

        pack();
        setVisible(true);
    }
}
