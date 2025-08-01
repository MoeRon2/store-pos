package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

public class SalesFrame extends JFrame {
	DefaultTableModel salesModel;
	JTable salesTable;
	
	public SalesFrame() {
		setTitle("View Sales - Store POS");
        setSize(800, 600); // Set size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closing

        // Set layout for the frame
        setLayout(new BorderLayout());
		
		// Add components like tables, buttons, etc. to manage stock
		// For example:
		
		JScrollPane scrollPane = new JScrollPane();
		
//		CREATE TABLE IF NOT EXISTS sales (
//	            id INTEGER PRIMARY KEY AUTOINCREMENT,
//	            timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
//	            total_amount REAL NOT NULL,
//	            payment_type TEXT
//	        );
		
		
		salesModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"id", "timestamp", "total_amount", "payment_type"
		    }) {
		    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Disables editing for all cells
		    }
		};
		
	
		salesTable = new JTable(salesModel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < salesTable.getColumnCount(); i++) {
		    salesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(salesTable);
		
		
	    add(scrollPane, BorderLayout.CENTER);
		
		// add(new StockPanel());
	    
//	    loadSalesData();
		
		// Make the frame visible
		setVisible(true);		
		System.out.println("SalesFrame initialized.");
	}
	
//	private void loadSalesData() {
//        // Get the list of products from the DB
//        List<Product> productList = ProductDAO.getProductsFromDB();
//
//        // Add data to the table model
//        for (Product product : productList) {
//            // Create an Object[] from the Product data
//            Object[] row = new Object[] {
//                product.getName(),
//                String.format("$%.2f", product.getPrice()),
//                product.getStock(),
//                product.getBarcode()
//            };
//            
//            // Add the row to the table model
//            stockModel.addRow(row);
//        }
//    }
}
