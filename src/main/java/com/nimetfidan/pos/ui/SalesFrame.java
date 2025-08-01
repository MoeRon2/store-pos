package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.nimetfidan.pos.db.SalesDAO;
import com.nimetfidan.pos.model.Sale;

public class SalesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7700592277742106887L;
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
	    
	    loadSalesData();
		
	    salesTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                JTable target = (JTable) e.getSource();
	                int row = target.getSelectedRow();

	                if (row != -1) {
	                    int saleId = (int) target.getValueAt(row, 0); // assumes ID is in column 0
	                    System.out.println("Double clicked on sale ID: " + saleId);
	                    new SaleItemsDialog(SalesFrame.this, saleId);
	                }
	            }
	        }
	    });
	    
	    
		// Make the frame visible
		setVisible(true);		
		System.out.println("SalesFrame initialized.");
	}
	


	
	
	private void loadSalesData() {
        // Get the list of products from the DB
        List<Sale> saleList = SalesDAO.getSalesFromDB();

        // Add data to the table model
        for (Sale sale : saleList) {
            // Create an Object[] from the Product data
            Object[] row = new Object[] {
                sale.getId(),
                sale.getTimestamp(),
                sale.getTotalAmount(),
                sale.getPaymentType(),
            };
            
            // Add the row to the table model
            salesModel.addRow(row);
        }
    }
}
