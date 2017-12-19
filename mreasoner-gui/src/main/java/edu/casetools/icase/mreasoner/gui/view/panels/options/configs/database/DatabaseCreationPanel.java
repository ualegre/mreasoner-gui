package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.configs.data.db.MDBConfigs;
import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;

public class DatabaseCreationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel,statusLabelTitle,statusLabel;
	JTextField nameTf;
	JButton checkButton,createButton,dropButton;
	TitledBorder titledBorder;
	JPanel mainPanel,buttonPanel;
	public DatabaseCreationPanel(){
		

	}
	
	public DatabaseCreationPanel(String dbName) {
		this.initializeElements(dbName);
		this.addElements();
        SpringUtilities.makeCompactGrid(mainPanel,
        2, 2, 		  //rows, cols
        6, 6,        //initX, initY
        6, 6);       //xPad, yPad
        this.setStatus(false);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);

	}

	private void initializeElements(String dbName){
		titledBorder	  = BorderFactory.createTitledBorder("Create/Drop Database");
		nameLabel 		  = new JLabel("Database name: ");
		statusLabelTitle  = new JLabel("Status: ");
		statusLabel  	  = new JLabel();
		checkButton		  = new JButton("Check");
		checkButton.setFocusable(false);
		createButton	  = new JButton("Create");
		createButton.setFocusable(false);
		dropButton 		  = new JButton("Drop");
		dropButton.setFocusable(false);
		nameTf    		  = new JTextField(20);
		nameTf.setText(dbName);
		mainPanel         = new JPanel(new SpringLayout());
		buttonPanel       = new JPanel(new GridLayout(1,3));        
	}
	
	private void addElements(){
		mainPanel.add(nameLabel);
		mainPanel.add(nameTf);
		mainPanel.add(statusLabelTitle);
		mainPanel.add(statusLabel);
		buttonPanel.add(checkButton);
		buttonPanel.add(createButton);
		buttonPanel.add(dropButton);
		this.setBorder(titledBorder);
	}

	public void setDBConfigs(MDBConfigs configs) {
		this.nameTf.setText(configs.getDbName());
	}

	public MDBConfigs getDBConfigs(MDBConfigs configs) {

		configs.setDbName(nameTf.getText());

		return configs;
	}

	public void addActionListener(ActionListener actionListener) {
		this.createButton.addActionListener(actionListener);
		this.dropButton.addActionListener(actionListener);
		this.checkButton.addActionListener(actionListener);	
	}

	public void setStatus(boolean connected){
		if(connected){
			this.statusLabel.setText("Created.");
			this.statusLabel.setForeground(Color.GREEN);
		}
		else {
			this.statusLabel.setText("Does not exist.");
			this.statusLabel.setForeground(Color.RED);
		}
		
	}

	
	
	
	
}
