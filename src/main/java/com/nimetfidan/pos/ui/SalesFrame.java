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

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.db.SalesDAO;
import com.nimetfidan.pos.model.Product;
import com.nimetfidan.pos.model.Sale;

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
	    
	    loadSalesData();
		
	    salesTable.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                JTable target = (JTable) e.getSource();
	                int row = target.getSelectedRow();

	                if (row != -1) {
	                    int saleId = (int) target.getValueAt(row, 0); // assumes ID is in column 0
	                    System.out.println("Double clicked on sale ID: " + saleId);
//	                    openSaleItemsDialog(saleId);
	                }
	            }
	        }
	    });
	    
	    
		// Make the frame visible
		setVisible(true);		
		System.out.println("SalesFrame initialized.");
	}
	
//	private void openSaleItemsDialog(int saleId) {
//	    List<SaleItem> items = SalesDAO.getSaleItemsForSaleId(saleId);
//
//	    // You can make a custom dialog, but here's a simple version:
//	    JDialog dialog = new JDialog((Frame) null, "Sale Items for ID " + saleId, true);
//	    dialog.setLayout(new BorderLayout());
//
//	    String[] columnNames = { "Barcode", "Name", "Price", "Quantity" };
//	    Object[][] data = new Object[items.size()][4];
//
//	    for (int i = 0; i < items.size(); i++) {
//	        SaleItem item = items.get(i);
//	        data[i][0] = item.getBarcode();
//	        data[i][1] = item.getName();
//	        data[i][2] = item.getPrice();
//	        data[i][3] = item.getQuantity();
//	    }
//
//	    JTable table = new JTable(data, columnNames);
//	    dialog.add(new JScrollPane(table), BorderLayout.CENTER);
//
//	    dialog.setSize(500, 300);
//	    dialog.setLocationRelativeTo(null);
//	    dialog.setVisible(true);
//	}

	
	
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
