package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.ssh;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.casetools.icase.mreasoner.vera.sensors.ssh.configs.SSHConfigs;


public class SSHConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	SSHConfigsPanel dbConfigsPanel;
	JPanel emptyPanel1, emptyPanel2,emptyPanel3;

	
	public SSHConfigsTabPanel(SSHConfigs configs){

		dbConfigsPanel = new SSHConfigsPanel(configs);
		emptyPanel1 = new JPanel();
		emptyPanel2 = new JPanel();
		emptyPanel3 = new JPanel();
		this.setLayout(new GridLayout(4,1));
		this.add(dbConfigsPanel);	
		this.add(emptyPanel1);
		this.add(emptyPanel2);
		this.add(emptyPanel3);
	}
	
	public void addActionListener(ActionListener actionListener){
		dbConfigsPanel.addActionListener(actionListener);
	}
	
	public SSHConfigs getSSHConfigs(SSHConfigs configs) {		
		configs = dbConfigsPanel.getSSHConfigs(configs);
		return configs;
	}
	
	public void setSSHConfigs(SSHConfigs configs) {
		dbConfigsPanel.setSSHConfigs(configs);
	}

	public SSHConfigsPanel getSshConfigsPanel(){
		return dbConfigsPanel;
	}
	
}
