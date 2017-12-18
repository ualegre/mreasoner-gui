package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.view.panels.menu.MainMenu.FILECHOOSER;
import edu.casetools.icase.mreasoner.gui.view.panels.menu.MainMenu.FILETYPE;
import edu.casetools.icase.mreasoner.vera.sensors.ssh.configs.SSHConfigs;




public class MainMenuListener implements ActionListener {

	private Controller controller;

	public MainMenuListener(Controller controller){
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New File")) 		 newButtonAction();
		else if(e.getActionCommand().equals("Load File")) loadButtonAction();
		else if(e.getActionCommand().equals("Save File")) saveButtonAction();
		else if(e.getActionCommand().equals("Save File As")) saveAsButtonAction();
		else if(e.getActionCommand().equals("Save Result")) saveResultButtonAction();
		else if(e.getActionCommand().equals("Save Result As")) saveResultAsButtonAction();
		else if(e.getActionCommand().equals("Start")) startButtonAction();
		else if(e.getActionCommand().equals("Stop")) stopButtonAction();
		else if(e.getActionCommand().equals("Load LFPUBS Output File")) loadLFPUBSFile();
		else if(e.getActionCommand().equals("Translate LFPUBS Rules")) translateLFPUBSFile();
		else if(e.getActionCommand().equals("Clear LFPUBS Rule Translations Tab")) clearTab();
		else if(e.getActionCommand().equals("New Session")) newConfigurationsButton();
		else if(e.getActionCommand().equals("Load Session")) loadConfigurationsButton();
		else if(e.getActionCommand().equals("Save Session")) saveConfigurationsButton();
		else if(e.getActionCommand().equals("Save Session As")) saveConfigurationsAsButton();
		else if(e.getActionCommand().equals("New SSH Configs")) newSSHConfigurationsButton();
		else if(e.getActionCommand().equals("Load SSH Configs")) loadSSHConfigurationsButton();
		else if(e.getActionCommand().equals("Save SSH Configs")) saveSSHConfigurationsButton();
		else if(e.getActionCommand().equals("Save SSH Configs As")) saveSSHConfigurationsAsButton();
		else if(e.getActionCommand().equals("Export to NuSMV")) exportToNuSMV();
		
	}
	
	private void saveSSHConfigurationsAsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSSHConfigsPath();
		String file = promptFileName(configsPath, FILECHOOSER.SAVE, FILETYPE.CONF);
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSSHConfigsPath(file);
			saveSSHConfigurationsButton();
		}
		
	}

	private void saveSSHConfigurationsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSSHConfigsPath();
		SSHConfigs sshConfigs = controller.getView().getMainWindow().getMainPanel().getSshConfigsTabPanel().getSSHConfigs(new SSHConfigs());
		if(!configsPath.isEmpty() && !configsPath.equals("null"))
			controller.getModel().getTesterModel().writeFile(configsPath, sshConfigs.parseConfigs());
		
	}

	private void loadSSHConfigurationsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSSHConfigsPath();
		String file = promptFileName(configsPath, FILECHOOSER.OPEN, FILETYPE.CONF);
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSSHConfigsPath(file);
			MConfigs configs = controller.getView().getMainWindow().getMainPanel().getConfigs();
			SSHConfigs sshConfigs = controller.getModel().getConfigsReader().readSSHConfigs(file);
			configs.setSshConfigs(sshConfigs);
			controller.getView().getMainWindow().getMainPanel().setConfigs(configs);	
		}
		
	}

	private void newSSHConfigurationsButton() {
		controller.getView().getMainWindow().getMainPanel().getSshConfigsTabPanel().setSSHConfigs(new SSHConfigs());
	}

	private void exportToNuSMV() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		String file = promptFileName(configsPath, FILECHOOSER.SAVE, FILETYPE.SMV);	
		if(file != null){			
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(file);
			MConfigs configs = getSystemConfigs();
			controller.getModel().getExporterModel().export(file,configs);		
		}
		
	}

	private void saveConfigurationsAsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		String file = promptFileName(configsPath, FILECHOOSER.SAVE, FILETYPE.CONF);
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(file);
			MConfigs configs = getSystemConfigs();
			controller.getModel().getTesterModel().writeFile(configs.getFilesConfigs().getSessionFilePath(), configs.parseConfigs());	
			if(!configs.getTimeConfigs().isSimulation()){
				saveSSHConfigurationsAsButton();	
			}
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(true);
		}
		
	}

	private void loadConfigurationsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		String file = promptFileName(configsPath, FILECHOOSER.OPEN, FILETYPE.CONF);
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		if(file != null){
			MConfigs configs = controller.getModel().getConfigsReader().readConfigs(file);
			if(configs!=null){
				controller.getView().getMainWindow().getMainPanel().setConfigs(configs);
				controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(true);
				controller.loadConfigs();
				this.loadLFPUBSFileView(configs.getFilesConfigs().getLFPUBSOutputFilePath());
				loadSystemSpecificationFileView(configs.getFilesConfigs().getSystemSpecificationFilePath());
				controller.setDividersAtDefaultLocation();
			}
			
		}
		
	}

	private String promptFileName(String configsPath, FILECHOOSER fcType, FILETYPE fileType) {
		if(configsPath.isEmpty()||configsPath.equals("null"))
			configsPath = ".\\examples\\development_stage";
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(configsPath, fcType, fileType);
		return file;
	}

	private void newConfigurationsButton() {
		MConfigs configs = new MConfigs();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		controller.getView().getMainWindow().getMainPanel().setConfigs(configs);
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(false);
		
	}
	
	
	
	private void saveConfigurationsButton(){
		MConfigs configs = getSystemConfigs();
		controller.getModel().getTesterModel().writeFile(configs.getFilesConfigs().getSessionFilePath(), configs.parseConfigs());	
		if(!configs.getTimeConfigs().isSimulation()){
			saveSSHConfigurationsButton();	
		}
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(true);
	}

	private void clearTab() {
		controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().setSelectedIndex(2);
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().setBotText("");
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().setTopText("");
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().disableTopPanel();
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().disableBotPanel();
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setLFPUBSPath("null");
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableTranslateButtons(false);	
		
	}

	private void translateLFPUBSFile() {
		// TODO Auto-generated method stub
		String filename = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath();
		String content = controller.getModel().getLFPUBSTranslator().translate(filename, true);
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().setBotText(content);
		controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().setSelectedIndex(2);
		controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().enableBotPanel();
		
		
	}


	private void loadLFPUBSFile() {
		String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath();
		String file = promptFileName(fileName,FILECHOOSER.OPEN,FILETYPE.LFPUBS);
		loadLFPUBSFileView(file);

	}
	
	private void loadLFPUBSFileView(String file){
		if(file != null){
			if(!file.equals("null") && !file.isEmpty()){
				String content;
				try {
					content = controller.getModel().getTesterModel().read(file);
					controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().setTopText(content);
					controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().enableTopPanel();
					controller.getView().getMainWindow().getMainPanel().getMainMenu().enableTranslateButtons(true);	
					//controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().setSelectedIndex(2);
					controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setLFPUBSPath(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	private MConfigs saveConfigurations(){
		MConfigs configs = this.getSystemConfigs();
		controller.getModel().getTesterModel().writeFile(configs.getFilesConfigs().getSessionFilePath(), configs.parseConfigs());
		if(!configs.getTimeConfigs().isSimulation())
				controller.getModel().getTesterModel().writeFile(configs.getFilesConfigs().getSshConfigsFilePath(), configs.getSshConfigs().parseConfigs());
		return configs;
	}
	
	private void saveResultAsButtonAction() {
		String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath();
		String file = promptFileName(fileName,FILECHOOSER.SAVE,FILETYPE.LOG);
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setResultsPath(file);
			String content = controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().getText();
			controller.getModel().getTesterModel().writeFile(file,content);
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableButtons(true);
			controller.getView().getMainWindow().setTitle(file);
		}
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableSaveResultsLog();
	}


	private void saveResultButtonAction() {
		String file = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath();
		if(file != null){
			String content = controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().getText();
			controller.getModel().getTesterModel().writeFile(file,content);

		}
		
	}


	private void saveButtonAction() {
		String file = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath();
		if(file != null){
			String content = controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getFileTextPane().getText();
			controller.getModel().getTesterModel().writeFile(file,content);

		}

	}


	private void startButtonAction() {
		

		MConfigs configs = this.saveConfigurations();
		this.saveButtonAction();
		if(controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().getSelectedIndex() != 1)
			controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().setSelectedIndex(0);
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");

			controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
			controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableStopButton(true);
			controller.getModel().startReasoner(configs.getFilesConfigs().getSessionFilePath());
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableSaveResultsLogAs();

		
	}
	
	private void stopButtonAction(){

		System.out.println("\n");
		controller.getModel().stopReasoner();
		System.out.println("----------EXECUTION STOPPED BY USER--------------");
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableStopButton(false);

	}
	
	private void saveAsButtonAction() {
		String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath();
		String file = promptFileName(fileName,FILECHOOSER.SAVE,FILETYPE.MTPL);
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSystemDeclarationFilePath(file);
			String content = controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getFileTextPane().getText();
			controller.getModel().getTesterModel().writeFile(file,content);
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableButtons(true);
			controller.getView().getMainWindow().setTitle(file);
		}
	}


	private void loadButtonAction() {
		String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath();
		String file = promptFileName(fileName,FILECHOOSER.OPEN,FILETYPE.MTPL);
		loadSystemSpecificationFileView(file);	
		
	}
	
	private void loadSystemSpecificationFileView(String file){
		if(file != null){
			if(!file.equals("null")){
			String content;
			try {
				controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSystemDeclarationFilePath(file);
				content = controller.getModel().getTesterModel().read(file);
				controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getFileTextPane().setText(content);
				controller.getView().getMainWindow().getMainPanel().getMainMenu().enableButtons(true);
				controller.getView().getMainWindow().setTitle(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}
		}
	}


	private void newButtonAction(){
		resetFileTextPane();
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSystemDeclarationFilePath("null");
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableButtons(false);
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableResultsButtons(false);
		controller.getView().getMainWindow().setTitle("M Specification File Editor - New File");
	}

	
	private void resetFileTextPane(){
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().resetFileText();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().centerSplitPaneDivider();
	}


	
	private MConfigs getSystemConfigs(){
		MConfigs configs = 
				controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs();
		configs.setDBConfigs(
				controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getDBConfigs(configs.getDBConfigs()));	
		configs.setSshConfigs( 
				controller.getView().getMainWindow().getMainPanel().getSshConfigsTabPanel().getSSHConfigs(configs.getSshConfigs()));
		configs.getFilesConfigs().setSystemSpecificationFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath());
		configs.getFilesConfigs().setSessionFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath());
		//System.out.println("CONFIGS PATH "+configs.getConfigsFilePath()+" - "+controller.getView().getMainWindow().getMainPanel().getMainMenu().getConfigsPath());
		configs.getFilesConfigs().setResultsFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath());
		configs.getFilesConfigs().setLFPUBSOutputFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath());
		return configs;
	}
	
}
