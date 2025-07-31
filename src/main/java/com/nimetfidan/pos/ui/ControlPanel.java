package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

private GridBagConstraints gbcControlPanel = new GridBagConstraints();

	ControlPanel() {
		setLayout(new GridBagLayout());
	    setBorder(BorderFactory.createTitledBorder("Control Panel"));
//	    JButton barcodeButton = new JButton("Add Item");
//	    JTextField barcodeField = new JTextField(20);  // 20 characters width
//	    
//	    add(barcodeButton);
//	    add(barcodeField);
	    
        GridBagConstraints gbcBarcodeField = new GridBagConstraints();
        gbcBarcodeField.gridx = 0;  // Keep in the same column as barcodeLabel
        gbcBarcodeField.gridy = 0; 
        gbcBarcodeField.weightx = 0;
        gbcBarcodeField.weighty = 1;
        gbcBarcodeField.anchor = GridBagConstraints.NORTH;  // Align to the left
        gbcBarcodeField.fill = GridBagConstraints.HORIZONTAL;  // Make it fill horizontally
        JTextField barcodeField = new JTextField(20);
        add(barcodeField, gbcBarcodeField);
	    
	    // Create components
//	    JLabel barcodeLabel = new JLabel("Enter Barcode:");
//	    JTextField barcodeField = new JTextField(20);  // 20 characters width
//	    barcodeField.setMaximumSize(new Dimension(300, 30));  // Limit max width to 300px, height 30px
//	    JButton barcodeButton = new JButton("Add Item");
//	    
//	    // Add components to the panel
//	    add(barcodeLabel);
//	    add(barcodeField);
//	    add(barcodeButton);
//	    
	    configureGBC();
	}
	
	private void configureGBC() {
		gbcControlPanel.gridx = 1;  // Column 1
        gbcControlPanel.gridy = 0;  // Row 1
        gbcControlPanel.gridwidth = 1;  // Span 1 column
        gbcControlPanel.gridheight = 4;  // Span 1 row
        gbcControlPanel.fill = GridBagConstraints.BOTH;  // Allow resizing
        gbcControlPanel.weightx = 0.5;  // Make it flexible in the x-direction
        gbcControlPanel.weighty = 0.5;  // Make it flexible in the y-direction
	}
	
	public GridBagConstraints getGbcControlPanel() {
		return gbcControlPanel;
	}
}
