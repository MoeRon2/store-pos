package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.db.SaleItemsDAO;
import com.nimetfidan.pos.model.Product;
import com.nimetfidan.pos.model.SaleItem;

public class SaleItemsDialog extends JDialog {
	DefaultTableModel saleItemsModel;
	JTable saleItemsTable;
	int itemSaleId;
	
	public SaleItemsDialog(JFrame parent, int saleId) {
		super(parent, "View Items from Sale", true);
		setPreferredSize(new Dimension(400, 300));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		
		itemSaleId = saleId;
		
		JScrollPane scrollPane = new JScrollPane();

	    saleItemsModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"sale_id", "barcode", "name", "price", "quantity", "total_price"
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
		
	
		saleItemsTable = new JTable(saleItemsModel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < saleItemsTable.getColumnCount(); i++) {
		    saleItemsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		
		
		loadSaleItemsData();
		
		scrollPane.setViewportView(saleItemsTable);
		
		
	    add(scrollPane, BorderLayout.CENTER);
	    
	    pack();
	    setVisible(true);
	    
	    
	}
	
	private void loadSaleItemsData() {
		System.out.println("Loading sale items for sale ID: " + itemSaleId);
        // Get the list of products from the DB
        List<SaleItem> saleItemList = SaleItemsDAO.getSaleItemsForSaleId(itemSaleId);

        // Add data to the table model
        for (SaleItem saleItem : saleItemList) {
        	System.out.println(saleItem);
            // Create an Object[] from the Product data
            Object[] row = new Object[] {
                itemSaleId,
                saleItem.getBarcode(),
                saleItem.getName(),
                saleItem.getPrice(),
                saleItem.getQuantity(),
                String.format("$%.2f", saleItem.getPrice() * saleItem.getQuantity()) // Total price
            };
            
            // Add the row to the table model
            saleItemsModel.addRow(row);
        }
    }
}

