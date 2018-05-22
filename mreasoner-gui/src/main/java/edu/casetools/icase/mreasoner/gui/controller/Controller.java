package edu.casetools.icase.mreasoner.gui.controller;

import edu.casetools.icase.mreasoner.configs.data.files.FilesConfigs;
import edu.casetools.icase.mreasoner.gui.controller.listeners.ConfigsListener;
import edu.casetools.icase.mreasoner.gui.controller.listeners.IterationTimeRadioButtonListener;
import edu.casetools.icase.mreasoner.gui.controller.listeners.MainMenuListener;
import edu.casetools.icase.mreasoner.gui.controller.listeners.RelativeTimeRadioButtonListener;
import edu.casetools.icase.mreasoner.gui.controller.listeners.StratificationRadioButtonListener;
import edu.casetools.icase.mreasoner.gui.controller.listeners.TabbedPaneListener;
import edu.casetools.icase.mreasoner.gui.model.Model;
import edu.casetools.icase.mreasoner.gui.view.View;


public class Controller {

	View view;
	Model model;
	public enum STATE {MAIN_PANEL,TEST_CASE_MENU};
	STATE state;
	MainMenuListener mainMenuListener;
	ConfigsListener configsListener;
	IterationTimeRadioButtonListener iterationTimeRBListener;
	RelativeTimeRadioButtonListener relativeTimeRBListener;
	StratificationRadioButtonListener stratificationListener;
	TabbedPaneListener tabbedPaneListener;


	public Controller(View view, Model model){
		state        = STATE.MAIN_PANEL;
		this.view 	 = view;
		this.model   = model;
		view.startMainWindow();
		initListeners();
		setListeners();
		disablePanels();
		initConfigs();		
		sleep();		
		setDividersAtDefaultLocation();

	}

	private void disablePanels() {
		view.getMainWindow().getMainPanel().getTranslataionsPanel().disableTopPanel();
		view.getMainWindow().getMainPanel().getTranslataionsPanel().disableBotPanel();
	}

	private void initConfigs() {
		FilesConfigs configs = new FilesConfigs();
		setFileOfSystemSpecificationEditor(configs);
		setFileOfLFPUBSRuleTranslationEditor(configs);
		setFileOfLog(configs);
		setFileOfConfigs(configs);
	}

	private void initListeners() {
		mainMenuListener 		= new MainMenuListener(this);
		configsListener 		= new ConfigsListener(this);
		iterationTimeRBListener = new IterationTimeRadioButtonListener(this);
		relativeTimeRBListener  = new RelativeTimeRadioButtonListener(this);
		stratificationListener  = new StratificationRadioButtonListener(this);
		tabbedPaneListener 		= new TabbedPaneListener(this);
	}
	
	public void sleep() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setFileOfConfigs(FilesConfigs configs) {
		if(configs.existsConfigsFilePath()){
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(configs.getSessionFilePath());
		}
		
	}
	private void setFileOfLog(FilesConfigs configs) {
		if(configs.existsLogFilePath()){
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setResultsPath(configs.getResultsFilePath());
		}
		
	}
	
	public void loadConfigs(){
		configsListener.refresh();
	}
	
	private void setFileOfLFPUBSRuleTranslationEditor(FilesConfigs configs) {
		if(configs.existsLFPUBSOutputFile()){
			String content = "";
	//		String content = model.getTesterModel().read(configs.getLFPUBSOutputFilePath());
			view.getMainWindow().getMainPanel().getTranslataionsPanel().setTopText(content);
			view.getMainWindow().getMainPanel().getTranslataionsPanel().enableTopPanel();
			view.getMainWindow().getMainPanel().getMainMenu().enableTranslateButtons(true);	
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setLFPUBSPath(configs.getLFPUBSOutputFilePath());
		}
		//view.getMainWindow().getMainPanel().getTesterPanel().centerSplitPaneDivider();
	}
	
	public void setDividersAtDefaultLocation(){
		view.getMainWindow().getMainPanel().getMainSplitPane().setDividerLocation(0.22);
		view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getSplitPane().setDividerLocation(0.5);
	}
	
	
	private void setFileOfSystemSpecificationEditor(FilesConfigs configs) {
		if(configs.existsSystemDeclarationFile()){
			String content = "New File";
			//String content = model.getTesterModel().read(configs.getSystemDeclarationFilePath());
			view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getFileTextPane().setText(content);
			view.getMainWindow().getMainPanel().getMainMenu().enableButtons(true);
			view.getMainWindow().setTitle(configs.getSystemSpecificationFilePath());
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSystemDeclarationFilePath(configs.getSystemSpecificationFilePath());
			view.getMainWindow().setTitle("M Specification File Editor - "+configs.getSystemSpecificationFilePath());
		}
		//view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().centerSplitPaneDivider();
	}

	private void setListeners(){
		view.getMainWindow().getMainPanel().addMainMenuListener(mainMenuListener);
		view.getMainWindow().getMainPanel().addConfigsListener(configsListener);
		view.getMainWindow().getMainPanel().addIterationTimeRBListener(iterationTimeRBListener);
		view.getMainWindow().getMainPanel().addRelativeTimeRBListener(relativeTimeRBListener);
		view.getMainWindow().getMainPanel().addStratificationPanelListener(stratificationListener);
		view.getMainWindow().getMainPanel().addTabbedPaneListener(tabbedPaneListener);
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}
	
	public void start(){
		view.startMainWindow();
	}

	
}
