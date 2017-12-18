package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.general;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.configs.data.files.FilesConfigs;
import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;


public class FilePathsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	//JPanel mainPanel;
	JLabel specificationLabel,resultsLabel,LFPUBSLabel,configsLabel,sshConfigsLabel;
	JLabel specificationPathLabel,resultsPathLabel,LFPUBSPathLabel,configsPathLabel,sshConfigsPathLabel;
	JButton specificationButton,resultsButton,LFPUBSLabelButton,ConfigsLabelButton;
	
	
	
	public FilePathsPanel(FilesConfigs configs){
		 //mainPanel = new JPanel(new SpringLayout());
		this.setLayout(new SpringLayout());
		 TitledBorder titledBorder = BorderFactory.createTitledBorder("File Paths Configurations");
		 //mainPanel.setBorder(titledBorder);
		 this.setBorder(titledBorder);
		 specificationLabel = new JLabel("M Specification File Path");
		 resultsLabel 	    = new JLabel("M Results File Path");
		 LFPUBSLabel 	    = new JLabel("LFPUBS Output File Path");
		 configsLabel 	    = new JLabel("System Configurations File Path");
		 sshConfigsLabel    = new JLabel("SSH Configurations File Path");
		 
		 specificationPathLabel = new JLabel(writeNull(configs.getSystemSpecificationFilePath()));
		 specificationPathLabel.setForeground(Color.LIGHT_GRAY);
		 resultsPathLabel 		= new JLabel(writeNull(configs.getResultsFilePath()));
		 resultsPathLabel.setForeground(Color.LIGHT_GRAY);
		 LFPUBSPathLabel 		= new JLabel(writeNull(configs.getLFPUBSOutputFilePath()));
		 LFPUBSPathLabel.setForeground(Color.LIGHT_GRAY);
		 configsPathLabel 		= new JLabel(writeNull(configs.getSessionFilePath()));
		 configsPathLabel.setForeground(Color.LIGHT_GRAY);
		 sshConfigsPathLabel 		= new JLabel(writeNull(configs.getSessionFilePath()));
		 sshConfigsPathLabel.setForeground(Color.LIGHT_GRAY);
		 
	     this.add( specificationLabel );
	     this.add( specificationPathLabel );
	     this.add( resultsLabel );   
	     this.add( resultsPathLabel );
	     this.add( LFPUBSLabel );
	     this.add( LFPUBSPathLabel );
	     this.add( configsLabel );   
	   	 this.add( configsPathLabel );
	     this.add( sshConfigsLabel );   
	   	 this.add( sshConfigsPathLabel );
	   	 
        SpringUtilities.makeCompactGrid(this,
                10, 1, 		  //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
	 //  	 mainPanel.add(this);
		 
	}
	
	public String writeNull(String line){
		if((line != null)&&(!line.equals("null"))){
			return line;
		}else return "Path not assigned.";
	}
	
	public String readNull(String line){
		if((line != null)&&(!line.equals("Path not assigned."))){
			return line;
		}else return "null";
	}

	public FilesConfigs getPathConfigs(FilesConfigs configs) {
		configs.setSystemSpecificationFilePath(readNull(specificationPathLabel.getText()));
		configs.setResultsFilePath(readNull(resultsPathLabel.getText()));
		configs.setLFPUBSOutputFilePath(readNull(LFPUBSPathLabel.getText()));
		configs.setSessionFilePath(readNull(configsPathLabel.getText()));
		configs.setSshConfigsFilePath(readNull(sshConfigsPathLabel.getText()));
		return configs;
	}
	
	public void setPathConfigs(FilesConfigs configs) {
		specificationPathLabel.setText(writeNull(configs.getSystemSpecificationFilePath()));
		resultsPathLabel.setText(writeNull(configs.getResultsFilePath()));
		LFPUBSPathLabel.setText(writeNull(configs.getLFPUBSOutputFilePath()));
		configsPathLabel.setText(writeNull(configs.getSessionFilePath()));
		sshConfigsPathLabel.setText(writeNull(configs.getSshConfigsFilePath()));
	}
	
	public String getSystemDeclarationFilePath(){
		return readNull(specificationPathLabel.getText());
	}
	
	public String getResultsPath(){
		return readNull(resultsPathLabel.getText());
	}
	
	public String getLFPUBSPath(){
		return readNull(LFPUBSPathLabel.getText());
	}
	
	public String getSessionPath(){
		return readNull(configsPathLabel.getText());
	}
	
	public String getSSHConfigsPath(){
		return readNull(sshConfigsPathLabel.getText());
	}
	
	public void setSystemDeclarationFilePath(String path){
		specificationPathLabel.setText(writeNull(path));
	}
	
	public void setResultsPath(String path){
		resultsPathLabel.setText(writeNull(path));
	}
	
	public void setLFPUBSPath(String path){
		LFPUBSPathLabel.setText(writeNull(path));
	}
	
	public void setConfigsPath(String path){
		configsPathLabel.setText(writeNull(path));
	}
	
	public void setSSHConfigsPath(String path){
		sshConfigsPathLabel.setText(writeNull(path));
	}
	
}
