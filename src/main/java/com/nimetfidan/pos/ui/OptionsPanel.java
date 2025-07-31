package com.nimetfidan.pos.ui;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel {
	private GridBagConstraints gbcOptionsPanel = new GridBagConstraints();
	
	OptionsPanel() {
	    setBorder(BorderFactory.createTitledBorder("Options Panel"));
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
