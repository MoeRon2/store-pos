package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230412760176507329L;
	private GridBagConstraints gbcControlPanel = new GridBagConstraints();
	public JTextField barcodeField; // Made public to access in other classes
	public JButton barcodePlusButton; 
	public JButton barcodeMinusButton;
	public JButton finishSaleButton;
	public JLabel totalAmountLabel;
	public JLabel previousTotalLabel;
	public JLabel discountLabel;
	public JRadioButton cashButton;
	public JRadioButton cardButton;
	ControlPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Control Panel"));
		configureGBC();
		createBarcodeField();
		createPlusMinusButtons();
		createPreviousTotalLabel();
		createCashOrCreditBoxes();
		createTotalPanel();
		createFinishSaleButton();
		
		
		
		
	
	}
	
	private void createCashOrCreditBoxes() {
		GridBagConstraints gbcPaymentOptions = new GridBagConstraints();
		gbcPaymentOptions.gridx = 0; // Keep in the same column as barcodeField
		gbcPaymentOptions.gridx = 0; // Keep in the same column as barcodeField
		gbcPaymentOptions.gridy = 4; // Next row
		gbcPaymentOptions.weightx = 1; // Fill the width
		gbcPaymentOptions.weighty = 0; // No extra height
		gbcPaymentOptions.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcPaymentOptions.insets = new Insets(0, 55, 50, 55); // Add some padding around the field
		gbcPaymentOptions.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally
		
		cashButton = new JRadioButton("Cash", true); // Default
		cardButton = new JRadioButton("Card");

		
//		cashButton.setIcon(new ImageIcon("icons/cash.png"));
//		cardButton.setIcon(new ImageIcon("icons/card.png"));
//		
		ButtonGroup paymentGroup = new ButtonGroup();
		paymentGroup.add(cashButton);
		paymentGroup.add(cardButton);
		
		// Nest them inside a panel (horizontal layout)
		JPanel paymentPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 2 columns
		paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment Method"));
		paymentPanel.add(cashButton);
		paymentPanel.add(cardButton);

		add(paymentPanel, gbcPaymentOptions);
		
	}

	
	private void createPreviousTotalLabel() {
		GridBagConstraints gbcPreviousTotalLabel = new GridBagConstraints();
		gbcPreviousTotalLabel.gridx = 0; // Keep in the same column as barcodeField
		gbcPreviousTotalLabel.gridy = 3; // Next row
		gbcPreviousTotalLabel.weightx = 1; // Fill the width
		gbcPreviousTotalLabel.weighty = 3; // No extra height
		gbcPreviousTotalLabel.insets = new Insets(0, 0, 40, 0); // Add some padding around the label
		gbcPreviousTotalLabel.anchor = GridBagConstraints.NORTH; // Align to the left
		gbcPreviousTotalLabel.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally
		
		previousTotalLabel = new JLabel("Previous Total: $0.00");
		previousTotalLabel.setFont(new Font("Arial", Font.BOLD, 24));
		previousTotalLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		add(previousTotalLabel, gbcPreviousTotalLabel);
		
	}
	
	private void createFinishSaleButton() {
		GridBagConstraints gbcFinishSaleButton = new GridBagConstraints();
		gbcFinishSaleButton.gridx = 0; // Keep in the same column as barcodeField
		gbcFinishSaleButton.gridy = 6; // Next row
		gbcFinishSaleButton.weightx = 1; // Fill the width
		gbcFinishSaleButton.weighty = 0.5; // No extra height
		gbcFinishSaleButton.insets = new Insets(0, 0, 20, 0); // Add some padding around the label
		gbcFinishSaleButton.anchor = GridBagConstraints.CENTER; // Align to the left
		gbcFinishSaleButton.fill = GridBagConstraints.BOTH; // Make it fill horizontally
		
		finishSaleButton = new JButton("Finish Sale");
		finishSaleButton.setFont(new Font("Arial", Font.BOLD, 24));
		finishSaleButton.setPreferredSize(new Dimension(200, 40));
		finishSaleButton.setFocusPainted(false);
		
		add(finishSaleButton, gbcFinishSaleButton);
		
	}
	
	private void createTotalPanel() {
		GridBagConstraints gbcTotalPanel = new GridBagConstraints();
		gbcTotalPanel.gridx = 0; // Keep in the same column as barcodeField
		gbcTotalPanel.gridy = 5; // Next row
		gbcTotalPanel.weightx = 1; // Fill the width
		gbcTotalPanel.weighty = 0.5; // No extra height
		gbcTotalPanel.insets = new Insets(0, 0, 0, 0); // Add some padding around the label
		gbcTotalPanel.anchor = GridBagConstraints.CENTER; // Align to the left
		gbcTotalPanel.fill = GridBagConstraints.HORIZONTAL; // Make it fill horizontally
		
		JPanel totalPanel = new JPanel(new GridLayout(0, 1));
		totalPanel.setBorder(BorderFactory.createTitledBorder("Total"));
		totalPanel.setFont(new Font("Arial", Font.BOLD, 24));
		
		totalAmountLabel = new JLabel("$0.00");
		discountLabel = new JLabel("Discount: $0.00");
		JLabel taxLabel = new JLabel("Tax: $0.00");
		totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 28));
		discountLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		taxLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		
		totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		discountLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		taxLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
		

//		// Empty label to act as a spacer
//		JLabel spacerLabel = new JLabel(" ");
//		spacerLabel.setPreferredSize(new Dimension(200, 20));  // Adjust size as needed
//
//		
		
		totalPanel.add(totalAmountLabel);
		totalPanel.add(discountLabel);
		totalPanel.add(taxLabel);
		// totalPanel.add(spacerLabel); // This creates space
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
		barcodePlusButton = new JButton("+");
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
		barcodeMinusButton = new JButton("-");
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

		barcodeField = new JTextField(10);
		// Center the text (and cursor)
		barcodeField.setHorizontalAlignment(SwingConstants.CENTER);
		barcodeField.setFont(new Font("Arial", Font.PLAIN, 24));
		barcodeField.setPreferredSize(new Dimension(barcodeField.getPreferredSize().width, 40));

		

		add(barcodeField, gbcBarcodeField);
		
		

	

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
