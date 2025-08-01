package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OptionsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657101050055207027L;
	private GridBagConstraints gbcOptionsPanel = new GridBagConstraints();
	public JTextField discountField;
	public JRadioButton discountCashButton;
	public JRadioButton discountPercentageButton;
	public JButton applyDiscountButton;
	
	
	OptionsPanel() {
	    setBorder(BorderFactory.createTitledBorder("Options Panel"));
	    
	    JLabel discountLabel = new JLabel("Discount:");
	    discountField = new JTextField(10);
	    
		discountCashButton = new JRadioButton("Cash", true); // Default
		discountPercentageButton = new JRadioButton("Percentage");
		
	    ButtonGroup paymentGroup = new ButtonGroup();
		paymentGroup.add(discountCashButton);
		paymentGroup.add(discountPercentageButton);
		
	    applyDiscountButton = new JButton("Apply Discount");
	    
	    
	    add(discountLabel);
	    add(discountField);
	    add(discountCashButton);
	    add(discountPercentageButton);
	    add(applyDiscountButton);
	    
	    configureGBC();
	}
	

	
	private void configureGBC() {
		 gbcOptionsPanel.gridx = 0;  // Column 1
	     gbcOptionsPanel.gridy = 3;  // Row 0
	     gbcOptionsPanel.gridwidth = 1;  // Span 1 column
	     gbcOptionsPanel.gridheight = 1;  // Span 1 row
	     gbcOptionsPanel.fill = GridBagConstraints.BOTH;  // Allow resizing
	     gbcOptionsPanel.weightx = 0.5;  // Make it flexible in the x-direction
	     gbcOptionsPanel.weighty = 0.5;  // Make it flexible in the y-direction
	}
	
	public GridBagConstraints getGbcOptionsPanel() {
		return gbcOptionsPanel;
	}
	
	
	
	
	
}
