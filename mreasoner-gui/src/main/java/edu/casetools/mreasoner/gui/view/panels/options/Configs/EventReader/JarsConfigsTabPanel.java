package edu.casetools.mreasoner.gui.view.panels.options.Configs.EventReader;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import edu.casetools.mreasoner.core.configs.MConfigurations;

public class JarsConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel jarCommands;
	JTextArea jarCommandsTf;
	JScrollPane scrollPane;

	
	public JarsConfigsTabPanel(MConfigurations configs){
		 TitledBorder titledBorder = BorderFactory.createTitledBorder("Jar Configurations");
		 scrollPane = new JScrollPane();
		 
		 this.setLayout( new BorderLayout() );
		
	     jarCommands = new JLabel("Jar Commands: ");
         jarCommandsTf =  new JTextArea(" ");
         jarCommandsTf.setLineWrap(true);
         scrollPane.add(jarCommandsTf);
         this.add(jarCommandsTf,BorderLayout.CENTER);
         this.setBorder(titledBorder);	 
		 this.setJarConfigs(configs);
		
	     jarCommandsTf.setSize(1, 1);
	
	}
	
	
	
	public MConfigurations getJarConfigs(MConfigurations configs){
		if (configs == null) configs = new MConfigurations();	
		configs.setJarConfigs(jarCommandsTf.getText());

		return configs;
	}
	
	
	public void setJarConfigs(MConfigurations configs){
		this.jarCommandsTf.setText(configs.getJarConfigs());
	}
	
	
}
