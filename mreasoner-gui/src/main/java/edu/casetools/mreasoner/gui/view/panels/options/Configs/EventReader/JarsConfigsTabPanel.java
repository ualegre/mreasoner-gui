package edu.casetools.mreasoner.gui.view.panels.options.Configs.EventReader;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;


public class JarsConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel jarCommands;
	JTextArea jarCommandsTf;
	JScrollPane scrollPane;

	
	public JarsConfigsTabPanel(MConfigs configs){
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
	
	
	
	public MConfigs getJarConfigs(MConfigs configs){
		if (configs == null) configs = new MConfigs();	
		configs.setJarConfigs(jarCommandsTf.getText());

		return configs;
	}
	
	
	public void setJarConfigs(MConfigs configs){
		this.jarCommandsTf.setText(configs.getJarConfigs());
	}
	
	
}
