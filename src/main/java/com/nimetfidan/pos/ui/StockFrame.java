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

public class StockFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2137355969123915171L;
	DefaultTableModel stockModel;
	JTable stockTable;
	
	public StockFrame() {
		setTitle("View Stock - Store POS");
        setSize(800, 600); // Set size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose when closing

        // Set layout for the frame
        setLayout(new BorderLayout());
		
		// Add components like tables, buttons, etc. to manage stock
		// For example:
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		stockModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"Name", "Quantity", "Price per Item", "Barcode"
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
		
	
		stockTable = new JTable(stockModel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < stockTable.getColumnCount(); i++) {
		    stockTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(stockTable);
		
		
	    add(scrollPane, BorderLayout.CENTER);
		
		// add(new StockPanel());
	    
	    loadStockData();
		
		// Make the frame visible
		setVisible(true);		
		System.out.println("StockFrame initialized.");
	}
	
	private void loadStockData() {
        // Get the list of products from the DB
        List<Product> productList = ProductDAO.getProductsFromDB();

        // Add data to the table model
        for (Product product : productList) {
            // Create an Object[] from the Product data
            Object[] row = new Object[] {
                product.getName(),
                product.getStock(),
                String.format("$%.2f", product.getPrice()),
                product.getBarcode()
            };
            
            // Add the row to the table model
            stockModel.addRow(row);
        }
    }
}
