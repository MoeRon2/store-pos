package com.nimetfidan.pos.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class POSFrame extends JFrame {
    private JPanel mainContentPanel;
    
    public POSFrame() {
    	// Set frame properties
        setTitle("Store POS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1920, 1080); // Set to full screen dimensions
        setMinimumSize(new Dimension(1400, 1000)); // Set minimum size
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set the layout for the main content panel with GridBagLayout
        mainContentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcMainPanel = new GridBagConstraints();

        // Cart Panel (Top Left)
        JPanel cartPanel = new JPanel();
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart Panel"));
        gbcMainPanel.gridx = 0;  // Column 0
        gbcMainPanel.gridy = 0;  // Row 0
        gbcMainPanel.gridwidth = 1;  // Span 1 column
        gbcMainPanel.gridheight = 2;  // Span 2 rows
        gbcMainPanel.fill = GridBagConstraints.BOTH;  // Allow resizing in both directions
        gbcMainPanel.weightx = 0.5;  // Make it flexible in the x-direction
        gbcMainPanel.weighty = 1.0;  // Make it flexible in the y-direction
        mainContentPanel.add(cartPanel, gbcMainPanel);

        // Options Panel (Top Right)
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options Panel"));
        GridBagConstraints gbcOptionsPanel = new GridBagConstraints();
        gbcOptionsPanel.gridx = 0;  // Column 1
        gbcOptionsPanel.gridy = 3;  // Row 0
        gbcOptionsPanel.gridwidth = 1;  // Span 1 column
        gbcOptionsPanel.gridheight = 1;  // Span 1 row
        gbcOptionsPanel.fill = GridBagConstraints.BOTH;  // Allow resizing
        gbcOptionsPanel.weightx = 0.5;  // Make it flexible in the x-direction
        gbcOptionsPanel.weighty = 0.5;  // Make it flexible in the y-direction
        mainContentPanel.add(optionsPanel, gbcOptionsPanel);

        // Input Panel (Right)
        JPanel inputPanel = new JPanel();
        GridBagConstraints gbcInputPanel = new GridBagConstraints();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Panel"));
        gbcInputPanel.gridx = 1;  // Column 1
        gbcInputPanel.gridy = 0;  // Row 1
        gbcInputPanel.gridwidth = 1;  // Span 1 column
        gbcInputPanel.gridheight = 4;  // Span 1 row
        gbcInputPanel.fill = GridBagConstraints.BOTH;  // Allow resizing
        gbcInputPanel.weightx = 0.5;  // Make it flexible in the x-direction
        gbcInputPanel.weighty = 0.5;  // Make it flexible in the y-direction
        
        
        mainContentPanel.add(inputPanel, gbcInputPanel);

        // Add the main content panel to the frame
        add(mainContentPanel, BorderLayout.CENTER);
//    	setTitle("Store POS");
//    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	setSize(800, 600);
//    	setLocationRelativeTo(null); 
//    	setLayout(new GridBagLayout());
//    	
    	// Set frame properties
//        setTitle("Store POS");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(0, 0, 1920, 1080);
//        setMinimumSize(new Dimension(1400, 1000));
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Set the layout for the main content panel
//        mainContentPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        // Cart Panel
//        JPanel cartPanel = new JPanel();
//        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart Panel"));
//        cartPanel.setPreferredSize(new Dimension(800, 600));
//        gbc.gridx = 0;  // Column 0
//        gbc.gridy = 0;  // Row 0
//        gbc.gridwidth = 1;  // Span 1 column
//        gbc.gridheight = 2;  // Span 2 rows
//        gbc.fill = GridBagConstraints.BOTH;  // Allow resizing in both directions
//        mainContentPanel.add(cartPanel, gbc);
//
//        // Options Panel
//        JPanel optionsPanel = new JPanel();
//        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options Panel"));
//        optionsPanel.setPreferredSize(new Dimension(400, 300));
//        gbc.gridx = 1;  // Column 1
//        gbc.gridy = 0;  // Row 0
//        gbc.gridwidth = 1;  // Span 1 column
//        gbc.gridheight = 1;  // Span 1 row
//        gbc.fill = GridBagConstraints.BOTH;  // Allow resizing
//        mainContentPanel.add(optionsPanel, gbc);
//
//        // Input Panel
//        JPanel inputPanel = new JPanel();
//        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Panel"));
//        gbc.gridx = 1;  // Column 1
//        gbc.gridy = 1;  // Row 1
//        gbc.gridwidth = 1;  // Span 1 column
//        gbc.gridheight = 1;  // Span 1 row
//        gbc.fill = GridBagConstraints.BOTH;  // Allow resizing
//        mainContentPanel.add(inputPanel, gbc);
//
//        // Add the main content panel to the frame
//        add(mainContentPanel, BorderLayout.CENTER);
//
//        // Make the frame visible
//        setVisible(true);
    	
//    	
//    	add(mainContentPanel, BorderLayout.CENTER);
//    	
//    	mainContentPanel = new JPanel(new GridLayout(3, 5));
//    	
//    	// Panels with visible borders
//        JPanel cartPanel = new JPanel();
//        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart Panel"));
//        cartPanel.setPreferredSize(new Dimension(400, 300));
//
//        JPanel optionsPanel = new JPanel();
//        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options Panel"));
//        optionsPanel.setPreferredSize(new Dimension(400, 300));
//
//        JPanel inputPanel = new JPanel();
//        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Panel"));
//
//        // Add to layout
//        add(cartPanel, BorderLayout.NORTH);
//        add(optionsPanel, BorderLayout.CENTER);
//        add(inputPanel, BorderLayout.SOUTH);
    	
    	
    }
}
