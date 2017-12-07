package edu.casetools.mreasoner.gui.view.panels.options.Configs.Database;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.core.configs.MDBConfigs;
import edu.casetools.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.mreasoner.gui.view.panels.utils.SpringUtilities;



public class DatabaseConfigsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel dbTypeLabel,driver,ipLabel,portLabel,
	userLabel,passLabel,conectionStatusLabel,conectionStatus;
	JButton connectButton;
	JTextField driverTf,ipTf,portTf,userTf;
	JComboBox<String> comboBox;
	JPasswordField passField;
	JPanel dbConfigsPanel,buttonPanel;

	
	public DatabaseConfigsPanel(MConfigurations configs){

		TitledBorder titledBorder = BorderFactory.createTitledBorder("Database Configurations");
		
		initializeElements();
		setLabels();
        addPanels();
		configureElements();

		this.setLayout(new GridLayout(1,0));
        dbConfigsPanel.setBorder(titledBorder);
        this.add(dbConfigsPanel);
        this.setDBConfigs(configs.getDBConfigs());
	
	}
	
	private void configureElements() {
        connectButton.setFocusable(false);
     	connectButton.setHorizontalAlignment( SwingConstants.CENTER );
     	buttonPanel.setBorder(BorderFactory.createEmptyBorder(2,10,2,4));
     //	buttonPanel.add(conectionStatusLabel);
     	buttonPanel.add(conectionStatus);
     	buttonPanel.add(connectButton);    	 
    	comboBox.addItem("PostgreSQL");
        passLabel.setLabelFor(passField);
        
        SpringUtilities.makeCompactGrid(dbConfigsPanel,
                7, 2, 		  //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		
	}

	private void initializeElements(){
		dbConfigsPanel 	 = new JPanel(new SpringLayout());
    	comboBox  	 = new JComboBox<String>();
					initJLabels();
					initTextFields();
		passField 	  = new JPasswordField(20);
        connectButton = new JButton("Refresh");
     	buttonPanel   = new JPanel(new GridLayout(1,3));	
	}
	
	private void initJLabels(){
        dbTypeLabel 		 = new JLabel("Database type: ");
        driver				 = new JLabel("Driver: ");
      //  dbName  		     = new JLabel("Database name: ");
        ipLabel     		 = new JLabel("Ip: ");
        portLabel   		 = new JLabel("Port: ");
        userLabel   		 = new JLabel("Username: ");
        passLabel   		 = new JLabel("Password: ");
        conectionStatusLabel = new JLabel("Status :");
        conectionStatus 	 = new JLabel("");
	}
	
	private void initTextFields(){
		driverTf =  new JTextField(20);
    //    dbNameTf =  new JTextField(20);
        ipTf 	 =  new JTextField(20);
        portTf   =  new JTextField(20);
        userTf   =  new JTextField(20);
	}
	
	private void addPanels(){  	  
       dbConfigsPanel.add(dbTypeLabel);
       dbConfigsPanel.add(comboBox);
       dbConfigsPanel.add(driver);
       dbConfigsPanel.add(driverTf);
//       dbConfigsPanel.add(dbName);
//       dbConfigsPanel.add(dbNameTf);
       dbConfigsPanel.add(ipLabel);
       dbConfigsPanel.add(ipTf);
       dbConfigsPanel.add(portLabel);
       dbConfigsPanel.add(portTf);
       dbConfigsPanel.add(userLabel);
       dbConfigsPanel.add(userTf);
       dbConfigsPanel.add(passLabel);
       dbConfigsPanel.add(passField);
       dbConfigsPanel.add(conectionStatusLabel);
       dbConfigsPanel.add(buttonPanel);
	}

	private void setLabels(){
        dbTypeLabel.setLabelFor(comboBox);     
//        dbName.setLabelFor(dbNameTf);
        ipLabel.setLabelFor(ipTf);
        portLabel.setLabelFor(portTf);
        userLabel.setLabelFor(userTf);
        conectionStatusLabel.setLabelFor(conectionStatus);
	}
	@SuppressWarnings("deprecation")
	public MConfigurations getDBConfigs(MConfigurations configs) {
		if (configs == null) configs = new MConfigurations();	
		String value = comboBox.getItemAt(comboBox.getSelectedIndex());
		configs.getDBConfigs().setDbType(value);

		value = this.driverTf.getText();
		configs.getDBConfigs().setDriver(value);
		
//		value = this.dbNameTf.getText();
//		configs.getDBConfigs().setDbName(value);
	
		value = this.ipTf.getText();
		configs.getDBConfigs().setIp(value);

		value = this.portTf.getText();
		configs.getDBConfigs().setPort(value);
	
		value =this.userTf.getText();
		configs.getDBConfigs().setUser(value);

		value = this.passField.getText();
		configs.getDBConfigs().setPassword(value);

		return configs;
	}
	
	public void connectionStatus(STATUS status){
		if( status == STATUS.CONNECTED){
			conectionStatus.setText("Connected.   ");
			conectionStatus.setForeground(Color.GREEN);
		}else{
			conectionStatus.setText("Disconnected.   ");
			conectionStatus.setForeground(Color.RED);
		}
	}
	
	public void addActionListener(ActionListener actionListener){
		connectButton.addActionListener(actionListener);
	}
	
	public void setDBConfigs(MDBConfigs configs) {
		
		//this.comboBox.set
		this.comboBox.setSelectedItem(configs.getDbType());
		this.driverTf.setText(configs.getDriver());
//		this.dbNameTf.setText(configs.getDbName());
		this.ipTf.setText(configs.getIp());
		this.portTf.setText(configs.getPort());
		this.userTf.setText(configs.getUser());
		this.passField.setText(configs.getPassword());
		
	
	}
}
