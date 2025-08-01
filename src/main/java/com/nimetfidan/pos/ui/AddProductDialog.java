package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

public class AddProductDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4288036225739250056L;
	JPanel mainContentPanel;
	public AddProductDialog(JFrame parentFrame) {
        super(parentFrame, "Add Product - Store POS", true); // Modal dialog

        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // rows, cols, hgap, vgap
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // top, left, bottom, right

        // Labels and fields
        JLabel barcodeLabel = new JLabel("Barcode:");
        JTextField barcodeField = new JTextField(15);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(15);

        JLabel stockLabel = new JLabel("Stock:");
        JTextField stockField = new JTextField(15);

        // Add to panel
        mainPanel.add(barcodeLabel);
        mainPanel.add(barcodeField);

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);

        mainPanel.add(priceLabel);
        mainPanel.add(priceField);

        mainPanel.add(stockLabel);
        mainPanel.add(stockField);

        // Submit button at the bottom
        JButton submitButton = new JButton("Add Product");
        submitButton.setPreferredSize(new Dimension(150, 40));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        // Add everything to the dialog
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Dialog settings
        pack(); // Auto size to fit content
        setResizable(false);
        setLocationRelativeTo(parentFrame); // Center relative to main POS window
        
        submitButton.addActionListener(e -> {
			// Here you would handle the form submission, e.g., save the product
			String barcode = barcodeField.getText();
			String name = nameField.getText();
			String price = priceField.getText();
			String stock = stockField.getText();
			
			// Validate and process the input data
			// For example, you could create a Product object and save it to the database
			
			System.out.println("Product Added: " + name + " with barcode " + barcode);
			Product newProduct = new Product(name, Double.parseDouble(price), Integer.parseInt(stock), barcode);
			ProductDAO.addProductsToDB(newProduct);
			dispose(); // Close the dialog after submission
		});
    }
}
