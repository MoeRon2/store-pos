package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.nimetfidan.pos.db.ProductDAO;
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
        
//		Product addProducts = new Product("Item 1", 10.00, 1, "1");
//		Product addProducts2 = new Product("Item 2", 10.00, 1, "2");
		Cart cart = new Cart();
//		cart.changeProductQuantity(addProducts, 2); // Add 2 of Item 1
//		cart.changeProductQuantity(addProducts2, 3);
        
	    cartPanel.refreshCartTable(cart);
	    
	    
	    JMenuBar menuBar = new JMenuBar();
	    JMenu stockMenu = new JMenu("Stock");

	    JMenuItem addProductItem = new JMenuItem("Add Product");
	    addProductItem.addActionListener(e -> {
	    	new AddProductDialog(this).setVisible(true); // Your custom JFrame for the form
	    });

	    JMenuItem viewStockItem = new JMenuItem("View Stock");
	    viewStockItem.addActionListener(e -> {
	        new StockFrame(); // Table display of ProductDAO.getProductsFromDB()
	    });
	    
	    JMenuItem updateProductItem = new JMenuItem("Update Existing Product");
	    updateProductItem.addActionListener(e -> {
	    	new UpdateProductDialog(this); // Your custom JFrame for the form
	    });

	    stockMenu.add(addProductItem);
	    stockMenu.add(viewStockItem);
	    stockMenu.add(updateProductItem);
	    menuBar.add(stockMenu);

	    setJMenuBar(menuBar); // inside your POSFrame constructor
	    
	    
        controlPanel.barcodePlusButton.addActionListener(e -> {
			// Increase quantity for selected row in cart
			System.out.println("Clicked Plus Button");
        	changeQuantityForSelectedRow(cartPanel, cart, cartPanel.cartTable, 1);
		});
        
        
        controlPanel.barcodeMinusButton.addActionListener(e -> {
			// Increase quantity for selected row in cart
			System.out.println("Clicked Minus Button");
        	changeQuantityForSelectedRow(cartPanel, cart, cartPanel.cartTable, -1);
		});
        
        
        
        System.out.println(ProductDAO.getProductsFromDB());
        
    	// Add ActionListener to JTextField for Enter key
		controlPanel.barcodeField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entered Barcode: " + controlPanel.barcodeField.getText());
			}
		});

		controlPanel.barcodeField.getDocument().addDocumentListener(new DocumentListener() {
			private void autoSubmitIfReady() {
				String text = controlPanel.barcodeField.getText();
				if (text.length() == 1) {
					System.out.println("Auto-submitted barcode: " + text);
					Product newProduct = ProductDAO.getItemFromDB(text);
					System.out.println(newProduct);
					cart.changeProductQuantity(newProduct, 1); // Add product to cart
					cartPanel.refreshCartTable(cart); // Refresh the cart table
					
				    SwingUtilities.invokeLater(() -> {
		                controlPanel.barcodeField.setText("");
		            });
					// Optionally clear or reset field here:
					// barcodeField.setText("");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				autoSubmitIfReady();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// Optional: handle backspace if needed
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Not used for plain text fields
			}
		});
        
        
        mainContentPanel.add(controlPanel, controlPanel.getGbcControlPanel());
        
        
      

        getContentPane().add(mainContentPanel, BorderLayout.CENTER);
        
        
    	
    }
    
    private void changeQuantityForSelectedRow(CartPanel cartPanel, Cart cart, JTable cartTable, int quantityChange) {
    	
    	// quantityChange is the amount to change the quantity by (+1 or -1 only these two)
	    int selectedRow = cartTable.getSelectedRow();
	    if (selectedRow == -1) {
	        System.out.println("No row selected.");
	        return;
	    }

	    String productName = (String) cartTable.getValueAt(selectedRow, 0); // Assuming first column is name

	    // Find the matching Product in the cart
	    for (Product product : cart.getItems().keySet()) {
	        if (product.getName().equals(productName)) {
	        	if (quantityChange < 0 && cart.getQuantity(product) <= 1) {
	        		cart.removeProduct(product); // Remove product if quantity is 1 or less
	        		cartPanel.refreshCartTable(cart); // Refresh the UI
	        		break;
	        	}
	            cart.changeProductQuantity(product, quantityChange); // Increase quantity by 1
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
