package com.nimetfidan.pos.ui;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.nimetfidan.pos.model.Cart;
import com.nimetfidan.pos.model.Product;

public class ReceiptDialog extends JDialog {

    public ReceiptDialog(JFrame parent, Cart cart, int saleId, String paymentType) {
        super(parent, "Receipt", true);
        setSize(400, 500);
        setLocationRelativeTo(parent);

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        receiptArea.setText(buildReceiptText(cart, saleId, paymentType));

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    private String buildReceiptText(Cart cart, int saleId, String paymentType) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== Store POS Receipt ===\n");
        sb.append("Sale ID: ").append(saleId).append("\n");
        sb.append("Date: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        sb.append("Payment: ").append(paymentType).append("\n");
        sb.append("==========================\n");
        sb.append(String.format("%-15s %5s %7s\n", "Item", "Qty", "Price"));
        sb.append("--------------------------\n");

        for (Product product : cart.getItems().keySet()) {
            int qty = cart.getQuantity(product);
            double price = product.getPrice() * qty;
            sb.append(String.format("%-15s %5d %7.2f\n", product.getName(), qty, price));
        }

        sb.append("==========================\n");
        sb.append(String.format("Subtotal:       %.2f\n", cart.getTotalPrice()));
        sb.append(String.format("Discount:      -%.2f\n", cart.getDiscount()));
        sb.append(String.format("Total:          %.2f\n", cart.getTotalPrice()));
        sb.append("==========================\n");
        sb.append("Thank you for shopping!\n");

        return sb.toString();
    }
}
