package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import edu.casetools.icase.mreasoner.configs.data.db.MDBConfigs;
import edu.casetools.icase.mreasoner.database.core.MDBImplementations;
import edu.casetools.icase.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;



public class DatabaseConfigsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel dbTypeLabel,driver,ipLabel,portLabel,
	userLabel,passLabel,conectionStatusLabel,conectionStatus;
	JButton connectButton;
	JTextField driverTf,ipTf,portTf,userTf;
	JComboBox<String> comboBox;
	JPasswordField passField;
	JPanel dbConfigsPanel, buttonPanel;

	
	public DatabaseConfigsPanel(MDBConfigs configs){

		TitledBorder titledBorder = BorderFactory.createTitledBorder("Database Configurations");
		
		initializeElements();
		setLabels();
        addPanels();
		configureElements();

		//this.setLayout(new GridLayout(1,0));

        this.setLayout(new BorderLayout());

        this.setDBConfigs(configs);
//       // this.setPreferredSize(new Dimension(200, 700));
//        dbConfigsPanel.setPreferredSize(new Dimension(100, 100));
        this.add(dbConfigsPanel, BorderLayout.NORTH);
        this.setBorder(titledBorder);
        
	}
	
	private void configureElements() {
        connectButton.setFocusable(false);
     	connectButton.setHorizontalAlignment( SwingConstants.CENTER );
     	connectButton.setPreferredSize(new Dimension(100,20));

     	buttonPanel.add(conectionStatus);
     	buttonPanel.add(connectButton);   
  	
        passLabel.setLabelFor(passField);
        
        SpringUtilities.makeCompactGrid(dbConfigsPanel,
                7, 2, 		  //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        dbConfigsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        

		
	}

	private void initializeElements(){
		dbConfigsPanel 	 = new JPanel(new SpringLayout());
    	comboBox  	 = new JComboBox<String>(MDBImplementations.getNames());
		initJLabels();
		initTextFields();
		passField 	  = new JPasswordField(20);
        connectButton = new JButton("Refresh");
     	buttonPanel   = new JPanel(new GridLayout(1,3));	
	}
	
	private void initJLabels(){
        dbTypeLabel 		 = new JLabel("Database type: ");
        driver				 = new JLabel("Driver: ");
        ipLabel     		 = new JLabel("Ip: ");
        portLabel   		 = new JLabel("Port: ");
        userLabel   		 = new JLabel("Username: ");
        passLabel   		 = new JLabel("Password: ");
        conectionStatusLabel = new JLabel("Status :");
        conectionStatus 	 = new JLabel("");
	}
	
	private void initTextFields(){
		driverTf =  new JTextField(20);
        ipTf 	 =  new JTextField(20);
        portTf   =  new JTextField(20);
        userTf   =  new JTextField(20);
	}
	
	private void addPanels(){  	  
       dbConfigsPanel.add(dbTypeLabel);
       dbConfigsPanel.add(comboBox);
       dbConfigsPanel.add(driver);
       dbConfigsPanel.add(driverTf);
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
        ipLabel.setLabelFor(ipTf);
        portLabel.setLabelFor(portTf);
        userLabel.setLabelFor(userTf);
        conectionStatusLabel.setLabelFor(conectionStatus);
	}
	
	public MDBConfigs getDBConfigs(MDBConfigs configs) {
		if (configs == null) configs = new MDBConfigs();	
		String value = comboBox.getItemAt(comboBox.getSelectedIndex());
		configs.setDbType(value);
		configs.setDriver(driverTf.getText());	
		configs.setIp(ipTf.getText());
		configs.setPort(portTf.getText());	
		configs.setUser(userTf.getText());
		configs.setPassword(new String(passField.getPassword()));

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
		
		this.comboBox.setSelectedItem(configs.getDbType());
		this.driverTf.setText(configs.getDriver());
		this.ipTf.setText(configs.getIp());
		this.portTf.setText(configs.getPort());
		this.userTf.setText(configs.getUser());
		this.passField.setText(configs.getPassword());
		
	
	}
}
