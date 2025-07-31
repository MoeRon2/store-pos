package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;

public class POSFrame extends JFrame {
    private JPanel mainContentPanel;
    
    public POSFrame() {
    	// Set frame properties
        setTitle("Store POS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080); // Set to full screen dimensions
        setMinimumSize(new Dimension(1400, 1000)); // Set minimum size
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Set the layout for the main content panel with GridBagLayout
        mainContentPanel = new JPanel(new GridBagLayout());

        // Cart Panel (Top Left)
        CartPanel cartPanel = new CartPanel();
        mainContentPanel.add(cartPanel, cartPanel.getGbcCartPanel());

        // Options Panel (Bottom Left)
        OptionsPanel optionsPanel = new OptionsPanel(); 
        mainContentPanel.add(optionsPanel, optionsPanel.getGbcOptionsPanel());

        // Control Panel (Right)
        ControlPanel controlPanel = new ControlPanel();
        
		Product addProducts = new Product("Item 1", 10.00, 1, "1");
		Product addProducts2 = new Product("Item 1", 10.00, 1, "1");
		Cart cart = new Cart();
		cart.addProduct(addProducts, 2); // Add 2 of Item 1
		cart.addProduct(addProducts2, 3);
        
	    cartPanel.refreshCartTable(cart);
	    
        controlPanel.barcodePlusButton.addActionListener(e -> {
			// Increase quantity for selected row in cart
			System.out.println("Clicked Plus Button");
        	increaseQuantityForSelectedRow(cartPanel, cart, cartPanel.cartTable);
		});
        mainContentPanel.add(controlPanel, controlPanel.getGbcControlPanel());
        
        
      

        getContentPane().add(mainContentPanel, BorderLayout.CENTER);
        
        
    	
    }
    
    private void increaseQuantityForSelectedRow(CartPanel cartPanel, Cart cart, JTable cartTable) {
	    int selectedRow = cartTable.getSelectedRow();
	    if (selectedRow == -1) {
	        System.out.println("No row selected.");
	        return;
	    }

	    String productName = (String) cartTable.getValueAt(selectedRow, 0); // Assuming first column is name

	    // Find the matching Product in the cart
	    for (Product product : cart.getItems().keySet()) {
	        if (product.getName().equals(productName)) {
	            cart.addProduct(product, 1); // Increase quantity by 1
	            cartPanel.refreshCartTable(cart);     // Refresh the UI
	            break;
	        }
	    }
	    
	    if (selectedRow < cartTable.getRowCount()) {
	        cartTable.setRowSelectionInterval(selectedRow, selectedRow);
	    }
	    else if (selectedRow > 0) {
	        cartTable.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
	    } else {
	        cartTable.clearSelection();
	    }
    }
}
