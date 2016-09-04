package edu.casetools.mreasoner.gui.Controller;

import edu.casetools.mreasoner.gui.Controller.Listeners.ConfigsListener;
import edu.casetools.mreasoner.gui.Controller.Listeners.StratificationRadioButtonListener;
import edu.casetools.mreasoner.gui.Controller.Listeners.MainMenuListener;
import edu.casetools.mreasoner.gui.Controller.Listeners.IterationTimeRadioButtonListener;
import edu.casetools.mreasoner.gui.Controller.Listeners.RelativeTimeRadioButtonListener;
import edu.casetools.mreasoner.gui.Controller.Listeners.TabbedPaneListener;
import edu.casetools.mreasoner.gui.Model.Model;
import edu.casetools.mreasoner.gui.View.View;
import edu.casetools.mreasoner.input.configurations.MConfigurations;


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


	public Controller(View view, Model model,MConfigurations configs){
		//STATUS status;
		this.view 	 = view;
		this.model   = model;
		state        = STATE.MAIN_PANEL;

		mainMenuListener 		= new MainMenuListener(this);
		configsListener 		= new ConfigsListener(this);
		iterationTimeRBListener = new IterationTimeRadioButtonListener(this);
		relativeTimeRBListener  = new RelativeTimeRadioButtonListener(this);
		stratificationListener  = new StratificationRadioButtonListener(this);
		tabbedPaneListener 		= new TabbedPaneListener(this);

		view.getMainWindow().getMainPanel().getTranslataionsPanel().disableTopPanel();
		view.getMainWindow().getMainPanel().getTranslataionsPanel().disableBotPanel();
		this.setListeners();
		setFileOfSystemSpecificationEditor(configs);
		setFileOfLFPUBSRuleTranslationEditor(configs);
		setFileOfLog(configs);
		setFileOfConfigs(configs);
		
		setDividersAtDefaultLocation();

	}
	private void setFileOfConfigs(MConfigurations configs) {
		if(configs.existsConfigsFilePath()){
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setConfigsPath(configs.getSessionFilePath());
		}
		
	}
	private void setFileOfLog(MConfigurations configs) {
		if(configs.existsLogFilePath()){
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setResultsPath(configs.getResultsFilePath());
		}
		
	}
	
	public void loadConfigs(){
		configsListener.refresh();
	}
	
	private void setFileOfLFPUBSRuleTranslationEditor(MConfigurations configs) {
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
		view.getMainWindow().getMainPanel().getMainSplitPane().setDividerLocation(0.28);
		view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getSplitPane().setDividerLocation(0.5);
	}
	
	
	private void setFileOfSystemSpecificationEditor(MConfigurations configs) {
		if(configs.existsSystemDeclarationFile()){
			String content = "";
			//String content = model.getTesterModel().read(configs.getSystemDeclarationFilePath());
			view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getFileTextPane().setText(content);
			view.getMainWindow().getMainPanel().getMainMenu().enableButtons(true);
			view.getMainWindow().setTitle(configs.getSystemSpecificationFilePath());
			view.getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().setSystemDeclarationFilePath(configs.getSystemSpecificationFilePath());
			view.getMainWindow().setTitle("System Specification File Editor - "+configs.getSystemSpecificationFilePath());
		}
		view.getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().centerSplitPaneDivider();
	}

	private void setListeners(){
		this.controlTesterPanel();
	}

	private void controlTesterPanel(){
	
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
	
	
	
	
}
