package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nimetfidan.pos.model.Product;

public class CartPanel extends JPanel{
	private GridBagConstraints gbcCartPanel = new GridBagConstraints();
	
	CartPanel() {
	    setBorder(BorderFactory.createTitledBorder("Cart Panel"));
	    configureGBC();
	    setLayout(new GridLayout(0, 1));
	    createCartTable();
	}
	
	private void configureGBC() {
		gbcCartPanel.gridx = 0;  // Column 0
	    gbcCartPanel.gridy = 0;  // Row 0
	    gbcCartPanel.gridwidth = 1;  // Span 1 column
	    gbcCartPanel.gridheight = 2;  // Span 2 rows
	    gbcCartPanel.fill = GridBagConstraints.BOTH;  // Allow resizing in both directions
	    gbcCartPanel.weightx = 0.5;  // Make it flexible in the x-direction
	    gbcCartPanel.weighty = 1.0;  // Make it flexible in the y-direction
	}
	
	public GridBagConstraints getGbcCartPanel() {
		return gbcCartPanel;
	}
	
	private void createCartTable() {
		JScrollPane scrollPane = new JScrollPane();
		
		
		DefaultTableModel cartModel = new DefaultTableModel(new Object[][] {}, new String[] {
				"Name", "Quantity", "Price per Item", "Total Price"
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
		
		
		Product addProduct = new Product("Item 1", 10.00, 1, "1");
		cartModel.addRow(addProduct.toObjectArray());
		cartModel.addRow(addProduct.toObjectArray());
		cartModel.addRow(addProduct.toObjectArray());
		
		
		JTable cartTable = new JTable(cartModel);
		scrollPane.setViewportView(cartTable);
	   
		
		
	    add(scrollPane);
	    
	    
	    // Column names
//	    String[] columnNames = {"Name", "Quantity", "Price per Item", "Total Price"};
//
//	    // Sample data (You can replace this with actual cart data)
//	    Object[][] data = {
//	        {"Item 1", 1, 10.00, 10.00},
//	        {"Item 2", 2, 15.00, 30.00},
//	        {"Item 3", 1, 7.50, 7.50}
//	    };
//
//	    // Create a table with data and column names
//	    JTable cartTable = new JTable(data, columnNames);
//	    
//	    // Optionally, make the table non-editable
//	    cartTable.setDefaultEditor(Object.class, null);
//	    
//	    // Set the table to be scrollable
//	    JScrollPane scrollPane = new JScrollPane(cartTable);
//	    scrollPane.setPreferredSize(new Dimension(400, 200)); // Set your preferred size here

	    // Add the table to your cart panel
	}
	
	
	
}
