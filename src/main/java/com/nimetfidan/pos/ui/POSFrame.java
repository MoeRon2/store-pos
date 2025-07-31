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
        getContentPane().setLayout(new BorderLayout());

        // Set the layout for the main content panel with GridBagLayout
        mainContentPanel = new JPanel(new GridBagLayout());

        // Cart Panel (Top Left)
        CartPanel cartPanel = new CartPanel();
        mainContentPanel.add(cartPanel, cartPanel.getGbcCartPanel());

        // Options Panel (Bottom Left)
        OptionsPanel optionsPanel = new OptionsPanel(); 
        mainContentPanel.add(optionsPanel, optionsPanel.getGbcOptionsPanel());

        // Control Panel (Right)
        ControlPanel controlPanel = new ControlPanel();
        mainContentPanel.add(controlPanel, controlPanel.getGbcControlPanel());
        
        JPanel panel = new JPanel();
        controlPanel.add(panel);

        getContentPane().add(mainContentPanel, BorderLayout.CENTER);
        
        
    	
    }
}
