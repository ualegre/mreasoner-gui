package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.database;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


import edu.casetools.icase.mreasoner.configs.data.db.MDBConfigs;


public class DatabaseConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	DatabaseConfigsPanel dbConfigsPanel;
	DatabaseCreationPanel dbCreationPanel;
	TableCreationPanel    tableCreationPanel;

	
	public DatabaseConfigsTabPanel(MDBConfigs configs){

		dbConfigsPanel = new DatabaseConfigsPanel(configs);
		dbCreationPanel = new DatabaseCreationPanel(configs.getDbName());
		tableCreationPanel = new TableCreationPanel();
		this.setLayout(new GridLayout(3,1));
		this.add(dbConfigsPanel);	
		this.add(dbCreationPanel);
		this.add(tableCreationPanel);

	}
	
	public void addActionListener(ActionListener actionListener){
		dbConfigsPanel.addActionListener(actionListener);
		dbCreationPanel.addActionListener(actionListener);
		tableCreationPanel.addActionListener(actionListener);
	}
	
	public MDBConfigs getDBConfigs(MDBConfigs configs) {		
		configs = dbConfigsPanel.getDBConfigs(configs);
		configs = dbCreationPanel.getDBConfigs(configs);
		return configs;
	}
	
	public void setDBConfigs(MDBConfigs configs) {
		dbConfigsPanel.setDBConfigs(configs);
		dbCreationPanel.setDBConfigs(configs);
	}

	public DatabaseConfigsPanel getDatabaseConfigsPanel(){
		return dbConfigsPanel;
	}
	
	public DatabaseCreationPanel getDatabaseCreationPanel(){
		return dbCreationPanel;
	}
	
	public TableCreationPanel getTableCreationPanel(){
		return tableCreationPanel;
	}
}
