package com.nimetfidan.pos.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nimetfidan.pos.logic.ExcelSalesExport;

public class ExportSalesDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3313585573156034185L;
	JLabel nameLabel;	
	JTextField nameField;
		public ExportSalesDialog(JFrame parent) {
			super(parent, "Export Sales", true);
			setPreferredSize(new Dimension(400, 300));
			setLocationRelativeTo(parent);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
			
			JButton exportButton = new JButton("Export Sales");
			
			
			add(nameLabel);
			add(nameField);
			add(exportButton);
			
			exportButton.addActionListener(e -> exportSales());
			
			pack();
			
			
			
		}

		
		public void exportSales() {
			ExcelSalesExport.createExcelFile(nameField.getText());
			System.out.println(nameField.getText()+ " file created succcessfuly!!");
		}
	}


