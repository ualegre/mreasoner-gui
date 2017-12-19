package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.ssh;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;
import edu.casetools.icase.mreasoner.vera.sensors.ssh.configs.SSHConfigs;



public class SSHConfigsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel fileLabel,hostnameLabel,portLabel,userLabel,passLabel,emptyLabel;
	JButton startButton, restartButton, stopButton;
	JTextField hostnameTf,portTf,userTf;
	JPasswordField passField;
	JPanel sshConfigsPanel,buttonPanel;

	
	public SSHConfigsPanel(SSHConfigs configs){

		TitledBorder titledBorder = BorderFactory.createTitledBorder("SSH Configurations");
		
		initializeElements();
		setLabels();
        addPanels();
		configureElements();

		this.setLayout(new GridLayout(1,0));
		this.setPreferredSize(new Dimension(300,50));
        sshConfigsPanel.setBorder(titledBorder);
        this.add(sshConfigsPanel);
        this.setSSHConfigs(configs);
	
	}
	
	private void configureElements() {
        startButton.setFocusable(false);
     	startButton.setHorizontalAlignment( SwingConstants.CENTER );
     	buttonPanel.setBorder(BorderFactory.createEmptyBorder(25,10,50,4));
     	buttonPanel.add(startButton);   
     	buttonPanel.add(restartButton);
     	buttonPanel.add(stopButton); 
        passLabel.setLabelFor(passField);
        
        SpringUtilities.makeCompactGrid(sshConfigsPanel,
                5, 2, 		  //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
		
	}

	private void initializeElements(){
		sshConfigsPanel = new JPanel(new SpringLayout());
		initJLabels();
		initTextFields();
		passField 	  = new JPasswordField(20);
        startButton   = new JButton("Start");
        restartButton = new JButton("Restart");
        stopButton    = new JButton("Stop");
     	buttonPanel   = new JPanel(new GridLayout(1,3));	
	}
	
	private void initJLabels(){		
        hostnameLabel 		 = new JLabel("Hostname: ");
        portLabel   		 = new JLabel("Port: 	 ");
        userLabel		     = new JLabel("Username: ");
        passLabel			 = new JLabel("Password: ");
        emptyLabel			 = new JLabel("");

	}
	
	private void initTextFields(){
		hostnameTf =  new JTextField(20);
        portTf     =  new JTextField(20);
        userTf     =  new JTextField(20);
	}
	
	private void addPanels(){  	  
       sshConfigsPanel.add(hostnameLabel);
       sshConfigsPanel.add(hostnameTf);
       sshConfigsPanel.add(portLabel);
       sshConfigsPanel.add(portTf);
       sshConfigsPanel.add(userLabel);
       sshConfigsPanel.add(userTf);
       sshConfigsPanel.add(passLabel);
       sshConfigsPanel.add(passField);
       sshConfigsPanel.add(emptyLabel);
       sshConfigsPanel.add(buttonPanel);
	}

	private void setLabels(){ 
        portLabel.setLabelFor(portTf);
        userLabel.setLabelFor(userTf);
	}

	public SSHConfigs getSSHConfigs(SSHConfigs configs) {
		if (configs == null) configs = new SSHConfigs();
		
		configs.setHostname(hostnameTf.getText());
		configs.setPort(portTf.getText());
		configs.setUsername(userTf.getText());
		configs.setPassword(new String(passField.getPassword()));

		return configs;
	}
	
	
	public void addActionListener(ActionListener actionListener){
		startButton.addActionListener(actionListener);
	}
	
	public void setSSHConfigs(SSHConfigs configs) {
		
		this.hostnameTf.setText(configs.getHostname());
		this.portTf.setText(configs.getPortAsString());
		this.userTf.setText(configs.getUsername());
		this.passField.setText(configs.getPassword());
		
	
	}
}
