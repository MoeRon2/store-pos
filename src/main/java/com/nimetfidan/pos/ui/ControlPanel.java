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

public class ControlPanel extends JPanel {

private GridBagConstraints gbcControlPanel = new GridBagConstraints();

	ControlPanel() {
		setLayout(new GridBagLayout());
	    setBorder(BorderFactory.createTitledBorder("Control Panel"));

	    
        GridBagConstraints gbcBarcodeField = new GridBagConstraints();
        gbcBarcodeField.gridx = 0;  // Keep in the same column as barcodeLabel
        gbcBarcodeField.gridy = 0; 
        gbcBarcodeField.weightx = 0.5;
        gbcBarcodeField.weighty = 1;
 	    gbcBarcodeField.insets = new Insets(10, 55, 5, 55);  // Add some padding around the field
        gbcBarcodeField.anchor = GridBagConstraints.NORTH;  // Align to the left
        gbcBarcodeField.fill = GridBagConstraints.HORIZONTAL;  // Make it fill horizontally
        
        
      
        JTextField barcodeField = new JTextField(10);
        // Center the text (and cursor)
        barcodeField.setHorizontalAlignment(SwingConstants.CENTER);
        barcodeField.setFont(new Font("Arial", Font.PLAIN, 24));
        barcodeField.setPreferredSize(new Dimension(barcodeField.getPreferredSize().width, 40));  
        add(barcodeField, gbcBarcodeField);
	    
        GridBagConstraints gbcBarcodeEnterButton = new GridBagConstraints();
        gbcBarcodeEnterButton.gridx = 1;  // Next column
        gbcBarcodeEnterButton.gridy = 0;
        gbcBarcodeEnterButton.weightx = 0.5;
        gbcBarcodeEnterButton.weighty = 1;
        gbcBarcodeEnterButton.insets = new Insets(10, 5, 5, 55);  // Add some padding around the button
        gbcBarcodeEnterButton.anchor = GridBagConstraints.NORTH;  // Align to the left
        gbcBarcodeEnterButton.fill = GridBagConstraints.HORIZONTAL;  // Make it fill horizontally
        JButton barcodeEnterButton = new JButton("Enter");
        barcodeEnterButton.setFont(new Font("Arial", Font.PLAIN, 24));
        barcodeEnterButton.setPreferredSize(new Dimension(barcodeEnterButton.getPreferredSize().width, 40));
        add(barcodeEnterButton, gbcBarcodeEnterButton);
        
        
        // Add ActionListener to JTextField for Enter key
        barcodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Entered Barcode: " + barcodeField.getText());
            }
        });
        
	    configureGBC();
	}
	
	private void configureGBC() {
		gbcControlPanel.gridx = 1;  // Column 1
        gbcControlPanel.gridy = 0;  // Row 1
        gbcControlPanel.gridwidth = 1;  // Span 1 column
        gbcControlPanel.gridheight = 4;  // Span 4 row
        gbcControlPanel.fill = GridBagConstraints.BOTH;  // Allow resizing
        gbcControlPanel.weightx = 0.5;  // Make it flexible in the x-direction
        gbcControlPanel.weighty = 0.5;  // Make it flexible in the y-direction
	}
	
	public GridBagConstraints getGbcControlPanel() {
		return gbcControlPanel;
	}
}
