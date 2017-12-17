package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.general;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;





public class ConfigsTabPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	TimeConfigsPanel timeConfigs;
	GeneralConfigsPanel generalConfigs;

	FilePathsPanel filePathPanel;
	
	public ConfigsTabPanel(MConfigs configs){
		this.setLayout( new GridLayout(3,1) );
        filePathPanel = new FilePathsPanel(configs.getFilesConfigs());
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
	
	public MConfigs getConfigs(){
		MConfigs configs = new MConfigs();
		configs = generalConfigs.getGeneralConfigs(configs);
		configs = timeConfigs.getTimeConfigs(configs);
		configs.setFilesConfigs(filePathPanel.getPathConfigs(configs.getFilesConfigs()));
		return configs;
	}
	
	public void addActionListener(ActionListener actionListener){
		this.generalConfigs.addActionListener(actionListener);
	}
	
	public void setConfigs(MConfigs configs) {
		generalConfigs.setGeneralConfigs(configs);
		timeConfigs.setTimeConfigs(configs);
		filePathPanel.setPathConfigs(configs.getFilesConfigs());
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
