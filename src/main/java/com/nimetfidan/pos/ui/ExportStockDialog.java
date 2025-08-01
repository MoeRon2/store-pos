package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.nimetfidan.pos.logic.ExcelWriter;

public class ExportStockDialog extends JDialog {
	JTextField nameField;
	
	public ExportStockDialog(JFrame parent) {
		super(parent, "Export Stock", true);
		setPreferredSize(new Dimension(400, 300));
		setLocationRelativeTo(parent);
		setLayout(new GridLayout(3, 1, 10, 10));
		// Here you can implement the logic to export stock to an Excel file
		// For example, you can use Apache POI to create an Excel file
		// and save it to a specified location.
		

		// Placeholder for export logic
		JLabel nameLabel = new JLabel("File Name:");
		nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		nameField = new JTextField(15);
		nameField.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JButton exportButton = new JButton("Export Stock");
		
		
		add(nameLabel);
		add(nameField);
		add(exportButton);
		
		exportButton.addActionListener(e -> exportStock());
		
		pack();
		
		
		
		System.out.println("Export stock functionality is not yet implemented.");
	}

	
	public void exportStock() {
		ExcelWriter.createExcelFile(nameField.getText());
		System.out.println(nameField.getText()+ " file created succcessfuly!!");
	}
}
