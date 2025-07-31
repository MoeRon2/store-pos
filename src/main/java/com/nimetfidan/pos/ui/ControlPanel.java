package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

private GridBagConstraints gbcControlPanel = new GridBagConstraints();
	
	ControlPanel() {
	    setBorder(BorderFactory.createTitledBorder("Control Panel"));
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
