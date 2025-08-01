package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;

public class CartPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3721245288273947555L;
	private GridBagConstraints gbcCartPanel = new GridBagConstraints();
	private DefaultTableModel cartModel;
	public JTable cartTable;
	
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
		
		
		cartModel = new DefaultTableModel(new Object[][] {}, new String[] {
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
		
		

		
		
		
		cartTable = new JTable(cartModel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < cartTable.getColumnCount(); i++) {
		    cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		scrollPane.setViewportView(cartTable);
	    add(scrollPane);
	    

	   
	    
	}
	
	public void refreshCartTable(Cart cart) {
        cartModel.setRowCount(0); // Clear the table first

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            // Create a row using your toObjectArray method, but with updated quantity
            Object[] row = new Object[] {
                product.getName(),
                quantity,
                String.format("$%.2f", product.getPrice()),
                String.format("$%.2f", product.getPrice() * quantity)
            };

            cartModel.addRow(row);
        }
    }
	
	
	
}
