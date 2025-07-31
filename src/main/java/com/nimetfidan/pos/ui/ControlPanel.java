package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlPanel extends JPanel {

	private GridBagConstraints gbcControlPanel = new GridBagConstraints();

	ControlPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Control Panel"));
		configureGBC();

		createBarcodeField();
		createPlusMinusButtons();
		
		
		GridBagConstraints gbcPreviousTotalLabel = new GridBagConstraints();
		gbcPreviousTotalLabel.gridx = 0; // Keep in the same column as barcodeField
		gbcPreviousTotalLabel.gridy = 3; // Next row
		gbcPreviousTotalLabel.weightx = 1; // Fill the width
		gbcPreviousTotalLabel.weighty = 1; // No extra height
		gbcPreviousTotalLabel.insets = new Insets(0, 0, 5, 0); // Add some padding around the label
		gbcPreviousTotalLabel.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcPreviousTotalLabel.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally
		
		JLabel totalLabel = new JLabel("Previous Total: $0.00");
		totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
		totalLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		add(totalLabel, gbcPreviousTotalLabel);
		
		GridBagConstraints gbcTotalPanel = new GridBagConstraints();
		gbcTotalPanel.gridx = 0; // Keep in the same column as barcodeField
		gbcTotalPanel.gridy = 4; // Next row
		gbcTotalPanel.weightx = 1; // Fill the width
		gbcTotalPanel.weighty = 1; // No extra height
		gbcTotalPanel.insets = new Insets(0, 0, 5, 0); // Add some padding around the label
		gbcTotalPanel.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcTotalPanel.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally
		
		JPanel totalPanel = new JPanel(new GridLayout(0, 1));
		totalPanel.setBorder(BorderFactory.createTitledBorder("Total"));
		totalPanel.setFont(new Font("Arial", Font.BOLD, 24));
		totalLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		
		JLabel totalAmountLabel = new JLabel("$0.00");
		JLabel discountLabel = new JLabel("Discount: $0.00");
		JLabel taxLabel = new JLabel("Tax: $0.00");
		totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 24));
		discountLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		taxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		discountLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		taxLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		totalPanel.add(totalAmountLabel);
		totalPanel.add(discountLabel);
		totalPanel.add(taxLabel);
		
		
		add(totalPanel, gbcTotalPanel);
	}

	private void createPlusMinusButtons() {
		GridBagConstraints gbcBarcodePlusButton = new GridBagConstraints();
		gbcBarcodePlusButton.gridx = 0; // Next column
		gbcBarcodePlusButton.gridy = 1;
		gbcBarcodePlusButton.weightx = 0;
		gbcBarcodePlusButton.weighty = 0;
		gbcBarcodePlusButton.insets = new Insets(40, 5, 0, 0); // Add some padding around the button
		gbcBarcodePlusButton.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcBarcodePlusButton.fill = GridBagConstraints.NONE; // No fill,
		JButton barcodePlusButton = new JButton("+");
		barcodePlusButton.setFont(new Font("Arial", Font.PLAIN, 24));
		barcodePlusButton.setPreferredSize(new Dimension(80, 40));
		barcodePlusButton.setFocusPainted(false);
		add(barcodePlusButton, gbcBarcodePlusButton);

		GridBagConstraints gbcBarcodeMinusButton = new GridBagConstraints();
		gbcBarcodeMinusButton.gridx = 0; // Next column
		gbcBarcodeMinusButton.gridy = 2;
		gbcBarcodeMinusButton.weightx = 0;
		gbcBarcodeMinusButton.weighty = 1;
		gbcBarcodeMinusButton.insets = new Insets(10, 5, 0, 0); // Add some padding around the button
		gbcBarcodeMinusButton.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcBarcodeMinusButton.fill = GridBagConstraints.NONE; // No fill,
		JButton barcodeMinusButton = new JButton("-");
		barcodeMinusButton.setFont(new Font("Arial", Font.PLAIN, 24));
		barcodeMinusButton.setPreferredSize(new Dimension(80, 40));
		barcodeMinusButton.setFocusPainted(false);
		add(barcodeMinusButton, gbcBarcodeMinusButton);

	}

	private void createBarcodeField() {
		GridBagConstraints gbcBarcodeField = new GridBagConstraints();
		gbcBarcodeField.gridx = 0; // Keep in the same column as barcodeLabel
		gbcBarcodeField.gridy = 0;
		gbcBarcodeField.weightx = 1;
		gbcBarcodeField.weighty = 0;
		gbcBarcodeField.insets = new Insets(10, 55, 5, 55); // Add some padding around the field
		gbcBarcodeField.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcBarcodeField.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally

		JTextField barcodeField = new JTextField(10);
		// Center the text (and cursor)
		barcodeField.setHorizontalAlignment(SwingConstants.CENTER);
		barcodeField.setFont(new Font("Arial", Font.PLAIN, 24));
		barcodeField.setPreferredSize(new Dimension(barcodeField.getPreferredSize().width, 40));

		add(barcodeField, gbcBarcodeField);

		// Add ActionListener to JTextField for Enter key
		barcodeField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entered Barcode: " + barcodeField.getText());
			}
		});

		barcodeField.getDocument().addDocumentListener(new DocumentListener() {
			private void autoSubmitIfReady() {
				String text = barcodeField.getText();
				if (text.length() == 13) {
					System.out.println("Auto-submitted barcode: " + text);
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

	}

	private void configureGBC() {
		gbcControlPanel.gridx = 1; // Column 1
		gbcControlPanel.gridy = 0; // Row 1
		gbcControlPanel.gridwidth = 1; // Span 1 column
		gbcControlPanel.gridheight = 4; // Span 4 row
		gbcControlPanel.fill = GridBagConstraints.BOTH; // Allow resizing
		gbcControlPanel.weightx = 0.5; // Make it flexible in the x-direction
		gbcControlPanel.weighty = 0.5; // Make it flexible in the y-direction
	}

	public GridBagConstraints getGbcControlPanel() {
		return gbcControlPanel;
	}
}
