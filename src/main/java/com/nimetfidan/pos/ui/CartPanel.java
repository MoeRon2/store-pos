package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CartPanel extends JPanel{
	private GridBagConstraints gbcCartPanel = new GridBagConstraints();
	
	CartPanel() {
	    setBorder(BorderFactory.createTitledBorder("Cart Panel"));
	    configureGBC();
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
	
	
	
}
