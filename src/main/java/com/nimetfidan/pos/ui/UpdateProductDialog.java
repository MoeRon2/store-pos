package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.nimetfidan.pos.db.ProductDAO;
import com.nimetfidan.pos.model.Product;

public class UpdateProductDialog extends JDialog {
    private JTextField barcodeField, nameField, priceField, stockField;
    private JButton updateButton;

    public UpdateProductDialog(JFrame parent) {
        super(parent, "Update Product", true);
        setLayout(new GridLayout(5, 2, 10, 10));
        setPreferredSize(new Dimension(400, 300));
        setLocationRelativeTo(parent);

        barcodeField = new JTextField(15);
        nameField = new JTextField(15);
        priceField = new JTextField(15);
        stockField = new JTextField(15);

        add(new JLabel("Barcode:"));
        add(barcodeField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Price:"));
        add(priceField);
        add(new JLabel("Stock:"));
        add(stockField);

        updateButton = new JButton("Update Product");
        add(new JLabel()); // spacer
        add(updateButton);

        // When barcode is entered and Enter is pressed
        barcodeField.addActionListener(e -> {
            String barcode = barcodeField.getText();
            Product product = ProductDAO.getItemFromDB(barcode);
            if (product != null) {
                nameField.setText(product.getName());
                priceField.setText(String.valueOf(product.getPrice()));
                stockField.setText(String.valueOf(product.getStock()));
            } else {
                JOptionPane.showMessageDialog(this, "Product not found!");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                String barcode = barcodeField.getText();
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());

                Product updatedProduct = new Product(name, price, stock, barcode);
                ProductDAO.updateProduct(barcode, updatedProduct);
                JOptionPane.showMessageDialog(this, "✅ Product updated!");
                dispose(); // Close the dialog
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ Invalid price or stock");
            }
        });

        pack();
        setVisible(true);
    }
}
