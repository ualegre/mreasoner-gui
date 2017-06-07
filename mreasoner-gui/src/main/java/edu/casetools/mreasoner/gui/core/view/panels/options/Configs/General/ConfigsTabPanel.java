package edu.casetools.mreasoner.gui.core.view.panels.options.Configs.General;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.casetools.mreasoner.configurations.data.MConfigurations;



public class ConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	TimeConfigsPanel timeConfigs;
	GeneralConfigsPanel generalConfigs;

	FilePathsPanel filePathPanel;
	
	public ConfigsTabPanel(MConfigurations configs){
		this.setLayout( new GridLayout(3,1) );
		//buttonPanel = new JPanel();	
		
		//timePanel = new JPanel(new BorderLayout());
        filePathPanel = new FilePathsPanel(configs);

		timeConfigs    = new TimeConfigsPanel(configs);
		generalConfigs = new GeneralConfigsPanel(configs);
		
		//timePanel.add(generalConfigs,BorderLayout.NORTH);
		//timePanel.add(timeConfigs,BorderLayout.CENTER);
			
	    this.add(generalConfigs);
	    this.add(timeConfigs);
	    this.add(filePathPanel);
      
       
	}
	public TimeConfigsPanel getTimeConfigsPanel(){
		return timeConfigs;
	}

	public GeneralConfigsPanel getGeneralConfigsPanel(){
		return generalConfigs;
	}
	
	public MConfigurations getConfigs(){
		MConfigurations configs = new MConfigurations();
		configs = generalConfigs.getGeneralConfigs(configs);
		configs = timeConfigs.getTimeConfigs(configs);
		configs = filePathPanel.getPathConfigs(configs);
		return configs;
	}
	
	public void addActionListener(ActionListener actionListener){
		this.generalConfigs.addActionListener(actionListener);
	}
	
	public void setConfigs(MConfigurations configs) {
		generalConfigs.setGeneralConfigs(configs);
		timeConfigs.setTimeConfigs(configs);
		filePathPanel.setPathConfigs(configs);
	}
	
	public FilePathsPanel getFilePathsPanel(){
		return this.filePathPanel;
	}
	public void addRelativeTimeRBListener(ActionListener actionListener) {
		this.timeConfigs.addRelativeTimeRBListener(actionListener);
		
	}
	public void addIterationTimeRBListener(ActionListener actionListener) {
		this.timeConfigs.addIterationTimeRBListener(actionListener);
		
	}

}
