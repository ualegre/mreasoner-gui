package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.configs.data.files.FilesConfigs;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.view.panels.menu.MainMenu.FILECHOOSER;
import edu.casetools.icase.mreasoner.gui.view.panels.menu.MainMenu.FILETYPE;




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
		else if(e.getActionCommand().equals("Save Session As")) saveConfigurationsButtonAs();
		else if(e.getActionCommand().equals("Export to NuSMV")) exportToNuSMV();
		
	}
	
	private void exportToNuSMV() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(configsPath, FILECHOOSER.SAVE, FILETYPE.SMV);//controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().displaySaveConfigsFileChooser();
		
		if(file != null){			
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(file);
			MConfigs configs = getSystemConfigs();
			controller.getModel().getExporterModel().export(file,configs);		
		}
		
	}

	private void saveConfigurationsButtonAs() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(configsPath, FILECHOOSER.SAVE, FILETYPE.CONF);//controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().displaySaveConfigsFileChooser();
		
		if(file != null){
			controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(file);
			FilesConfigs configs = getSystemConfigs().getFilesConfigs();
			configs.setSessionFilePath(file);
			controller.getModel().getTesterModel().writeFile(configs.getSessionFilePath(), configs.parseConfigs());		
			controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(true);
		}
		
	}

	private void loadConfigurationsButton() {
		String configsPath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath();
		if(configsPath.equals(""))
			configsPath = ".\\examples\\development_stage";
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(configsPath, FILECHOOSER.OPEN, FILETYPE.CONF);
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

	private void newConfigurationsButton() {
		MConfigs configs = new MConfigs();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		controller.getView().getMainWindow().getMainPanel().setConfigs(configs);
		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableConfigurationButtons(false);
		
	}
	
	private void saveConfigurationsButton(){
		MConfigs configs = getSystemConfigs();
		controller.getModel().getTesterModel().writeFile(configs.getFilesConfigs().getSessionFilePath(), configs.parseConfigs());		
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
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(fileName, FILECHOOSER.OPEN, FILETYPE.LFPUBS);
		loadLFPUBSFileView(file);

	}
	
	private void loadLFPUBSFileView(String file){
		if(file != null){
			if(!file.equals("null")){
				String content;
				try {
					content = controller.getModel().getTesterModel().read(file);
					controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().setTopText(content);
					controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().enableTopPanel();
					controller.getView().getMainWindow().getMainPanel().getMainMenu().enableTranslateButtons(true);	
					controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().setSelectedIndex(2);
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
		return configs;
	}
	
	private void saveResultAsButtonAction() {
		String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath();
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(fileName, FILECHOOSER.SAVE, FILETYPE.CONF);
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
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(fileName, FILECHOOSER.SAVE, FILETYPE.MTPL);
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
		String file = controller.getView().getMainWindow().getMainPanel().getMainMenu().displayFileChooser(fileName, FILECHOOSER.OPEN, FILETYPE.MTPL);
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
		controller.getView().getMainWindow().setTitle("System Specification File Editor - New File");
	}

	
	private void resetFileTextPane(){
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().resetFileText();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().centerSplitPaneDivider();
	}


	
	private MConfigs getSystemConfigs(){
		MConfigs configs = 
				controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs();
		configs = 
				controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getDBConfigs(configs);	
		configs = 
				controller.getView().getMainWindow().getMainPanel().getEventReaderConfigsPanel().getJarConfigs(configs);
		configs.getFilesConfigs().setSystemSpecificationFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath());
		configs.getFilesConfigs().setSessionFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSessionPath());
		//System.out.println("CONFIGS PATH "+configs.getConfigsFilePath()+" - "+controller.getView().getMainWindow().getMainPanel().getMainMenu().getConfigsPath());
		configs.getFilesConfigs().setResultsFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath());
		configs.getFilesConfigs().setLFPUBSOutputFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath());
		return configs;
	}
	
}
