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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.db.SaleItemsDAO;
import com.nimetfidan.pos.db.SalesDAO;
import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;

public class POSFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6109562823470358908L;

	private JPanel mainContentPanel;
    
    private CartPanel cartPanel;
    private OptionsPanel optionsPanel;
    private ControlPanel controlPanel;
    
    
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
        cartPanel = new CartPanel();
        mainContentPanel.add(cartPanel, cartPanel.getGbcCartPanel());

        // Options Panel (Bottom Left)
        optionsPanel = new OptionsPanel(); 
        mainContentPanel.add(optionsPanel, optionsPanel.getGbcOptionsPanel());

        // Control Panel (Right)
        controlPanel = new ControlPanel();
        
//		Product addProducts = new Product("Item 1", 10.00, 1, "1");
//		Product addProducts2 = new Product("Item 2", 10.00, 1, "2");
		Cart cart = new Cart();
//		cart.changeProductQuantity(addProducts, 2); // Add 2 of Item 1
//		cart.changeProductQuantity(addProducts2, 3);
        
	    cartPanel.refreshCartTable(cart);
	    
	    
	    JMenuBar menuBar = new JMenuBar();
	    JMenu stockMenu = new JMenu("Stock");
	    JMenu salesMenu = new JMenu("Sales");
	    JMenu importMenu = new JMenu("Import");
	    JMenu exportMenu = new JMenu("Export");
	
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
	    
	    
	    JMenuItem viewSalesItem = new JMenuItem("View Sales");
	    viewSalesItem.addActionListener(e -> {
	    	new SalesFrame(); // Table display of sales
	    });
	    
	    
	    salesMenu.add(viewSalesItem);
	    
	    JMenuItem importFullStock = new JMenuItem("Import Full Stock (Clear & Replace Existing)");
	    JMenuItem importPartialStock = new JMenuItem("Import Partial Stock (Add New, Update Existing");
	    
	    importFullStock.addActionListener(e -> {
	    	new ImportFullStockDialog(this); // Your custom JFrame for the form
	    });
    
	    importPartialStock.addActionListener(e -> {
	    	new ImportPartialStockDialog(this); // Your custom JFrame for the form
	    });
	    
	    
	    importMenu.add(importFullStock);
	    importMenu.add(importPartialStock);
	    
	    
	    JMenuItem exportStockItem = new JMenuItem("Export Stock to Excel");
	    exportStockItem.addActionListener(e -> {
	    	new ExportStockDialog(this).setVisible(true); // Your custom JFrame for the form
	    });
	    
	    JMenuItem exportSalesItem = new JMenuItem("Export Sales to Excel");
	    
	    exportSalesItem.addActionListener(e -> {
	    	new ExportSalesDialog(this).setVisible(true); // Your custom JFrame for the form
	    });
	    
	    exportMenu.add(exportStockItem);
	    exportMenu.add(exportSalesItem);
	    
	    
	    menuBar.add(stockMenu);
	    menuBar.add(salesMenu);
	    menuBar.add(importMenu);
	    menuBar.add(exportMenu);

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
        
        
        
//        System.out.println(ProductDAO.getProductsFromDB());
        
    	// Add ActionListener to JTextField for Enter key
		controlPanel.barcodeField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    String text = controlPanel.barcodeField.getText().trim();
				System.out.println("Entered Barcode: " + text);
				Product newProduct = ProductDAO.getItemFromDB(text);
				
				if (newProduct == null) {
					String errorMessage = "Product not found for barcode: " + text;
					JOptionPane.showMessageDialog(POSFrame.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

					return; // Exit if product not found
				}
				
				if (newProduct.getStock() <= 0) {
					String errorMessage = "Product is out of stock: " + newProduct.getName();
					JOptionPane.showMessageDialog(POSFrame.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
					return; // Exit if product is out of stock
				}
				
				System.out.println(newProduct);
				int result = cart.changeProductQuantity(newProduct, 1); // Add product to cart
				
				if (result == -1) {
					JOptionPane.showMessageDialog(POSFrame.this, "Not enough stock available for " + newProduct.getName() + ". Available stock: " + newProduct.getStock(), "Error", JOptionPane.ERROR_MESSAGE);
					return; // Exit if not enough stock
				}
				
				cartPanel.refreshCartTable(cart); // Refresh the cart table
				
			    SwingUtilities.invokeLater(() -> {
	                controlPanel.barcodeField.setText("");
	                controlPanel.totalAmountLabel.setText(cart.getTotalPrice() + "$"); // Update total label
	            });
			}
		});
		
		
		optionsPanel.applyDiscountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle discount logic here
				System.out.println("Discount button clicked");
				String discountText = optionsPanel.discountField.getText().trim();
				if (!discountText.isEmpty()) {
					try {
						double discount = Double.parseDouble(discountText);
						if (discount < 0) {
							System.out.println("Discount cannot be negative.");
							return;
						}
						
						if (optionsPanel.discountCashButton.isSelected()) {
							cart.applyCashDiscount(discount); // Apply cash discount to cart
						}
						else if (optionsPanel.discountPercentageButton.isSelected()) {
							cart.applyDiscountPercentage(discount); // Apply percentage discount to cart
						}
						else {
							System.out.println("No discount type selected.");
							return;
						}
						
					
				       controlPanel.totalAmountLabel.setText(cart.getTotalPrice() + "$"); // Update total label
				       controlPanel.discountLabel.setText("Discount: " + cart.getDiscount() + "$"); // Update discount label
					} catch (NumberFormatException ex) {
					   System.out.println("Invalid discount value: " + discountText);
				   }
						
				
				
			}
			}
		});

       
        controlPanel.finishSaleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (cart.getItems().isEmpty()) {
					JOptionPane.showMessageDialog(POSFrame.this, "Cart is empty. Please add items before finishing the sale.", "Error", JOptionPane.ERROR_MESSAGE);
					return; // Exit if cart is empty
				}
				
				
				
				String paymentType;
				if (controlPanel.cashButton.isSelected()) {
				    paymentType = "Cash";
				} else if (controlPanel.cardButton.isSelected()) {
				    paymentType = "Card";
				} else {
				    paymentType = "Unknown"; // fallback
				}
				
				System.out.println("Payment Type: " + paymentType);
				// Handle finish sale logic here
				System.out.println("Finish Sale button clicked");
				

				int saleId = SalesDAO.addSalesToDB(cart.getTotalPrice(), paymentType); // Save sale to database
			
				
				controlPanel.previousTotalLabel.setText("Previous Total: " + cart.getTotalPrice() + "$");
				cart.finishSale(saleId); // Finalize the sale
				
				new ReceiptDialog(POSFrame.this, cart,saleId,  paymentType); // Show receipt dialog

				cart.clearCart(); // Clear the cart after finishing the sale
				cartPanel.refreshCartTable(cart); // Refresh the cart table
				controlPanel.totalAmountLabel.setText(cart.getTotalPrice() + "$"); // Update total label
				controlPanel.discountLabel.setText("Discount: " + cart.getDiscount() + "$"); // Update discount label
				controlPanel.barcodeField.requestFocusInWindow();
				

			}
		});
	
        mainContentPanel.add(controlPanel, controlPanel.getGbcControlPanel());
		
        SwingUtilities.invokeLater(() -> controlPanel.barcodeField.requestFocusInWindow());

        



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
	        	if (quantityChange < 0 || cart.getQuantity(product) <= 1) {
	        		cart.removeProduct(product); // Remove product if quantity is 1 or less
	        		cartPanel.refreshCartTable(cart); // Refresh the UI
	        		break;
	        	}
	            int result = cart.changeProductQuantity(product, quantityChange); // Increase quantity by 1
	            if (result == -1) {
	            	JOptionPane.showMessageDialog(cartPanel, "Not enough stock available for " + product.getName() + ". Available stock: " + product.getStock(), "Error", JOptionPane.ERROR_MESSAGE);
	            	return; // Exit if not enough stock
	            }
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
	    
	    // finally change the total label 
	    controlPanel.totalAmountLabel.setText(cart.getTotalPrice() + " $");
    }
}
