package edu.casetools.mreasoner.gui.core.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.gui.core.view.panels.main.DatabasePanel;
import edu.casetools.mreasoner.gui.core.view.panels.main.SystemSpecificationEditorPanel;
import edu.casetools.mreasoner.gui.core.view.panels.main.TranslationsPanel;
import edu.casetools.mreasoner.gui.core.view.panels.menu.MainMenu;
import edu.casetools.mreasoner.gui.core.view.panels.options.Configs.Database.DatabaseConfigsTabPanel;
import edu.casetools.mreasoner.gui.core.view.panels.options.Configs.EventReader.JarsConfigsTabPanel;
import edu.casetools.mreasoner.gui.core.view.panels.options.Configs.General.ConfigsTabPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplitPane;
	private JTabbedPane leftTabbedPane, rightTabbedPane;
	private SystemSpecificationEditorPanel systemSpecificationEditorPanel;
	private TranslationsPanel translataionsPanel;
	private ConfigsTabPanel configsPanel;
//	private SensorMappingPanel sensorMappingPanel,actuatorMappingPanel;
	private DatabasePanel databasePanel;
	private MainMenu menu;
	private DatabaseConfigsTabPanel databaseConfigsTabPanel;
	private JarsConfigsTabPanel jarsConfigsPanel;

	public MainPanel(MConfigurations configs) {

		menu 						   = new MainMenu(configs);
		configsPanel 				   = new ConfigsTabPanel(configs);
		systemSpecificationEditorPanel = new SystemSpecificationEditorPanel();
		translataionsPanel   		   = new TranslationsPanel();
		databasePanel 				   = new DatabasePanel(configs);
//		sensorMappingPanel 			   = new SensorMappingPanel();
//		actuatorMappingPanel 		   = new SensorMappingPanel();
		databaseConfigsTabPanel    	   = new DatabaseConfigsTabPanel(configs);
		jarsConfigsPanel 	   = new JarsConfigsTabPanel(configs);
		
		createLeftTabbedPane(configs);
		createRightTabbedPane(configs);
		createMainSplitPanel();
		
		configureExecutionMode(configs.getExecutionMode());
		
		this.setLayout(new BorderLayout());
		this.add(mainSplitPane, BorderLayout.CENTER);
		this.add(menu, BorderLayout.NORTH);

	}

	private void createMainSplitPanel() {
		mainSplitPane = new JSplitPane();

		mainSplitPane.setLeftComponent(leftTabbedPane);
		mainSplitPane.setRightComponent(rightTabbedPane);
		mainSplitPane.setPreferredSize(new Dimension(100,200));

		mainSplitPane.setEnabled(false);
	}

	private void createLeftTabbedPane(MConfigurations configs) {
		leftTabbedPane = new JTabbedPane();

		leftTabbedPane.setFocusable(false);

		leftTabbedPane.add("General", configsPanel);
		leftTabbedPane.add("Database", databaseConfigsTabPanel);		
		leftTabbedPane.add("Add External Jars", jarsConfigsPanel);
		
		leftTabbedPane.setEnabledAt(2, false);
	}
	
	public void configureExecutionMode(EXECUTION_MODE mode){
		switch(mode){
		case REAL_ENVIRONMENT:
			break;
		case SIMULATION_ITERATION:
			break;
		case SIMULATION_REAL_TIME:
			break;
		default:
			break;
		
		}
	}
	

	private void createRightTabbedPane(MConfigurations configs) {
		rightTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rightTabbedPane.addTab("System Specification File Editor", systemSpecificationEditorPanel);
		rightTabbedPane.addTab("Database Results", databasePanel);
		//rightTabbedPane.add("Sensor Mapping", sensorMappingPanel);
		//rightTabbedPane.add("Actuator Mapping", actuatorMappingPanel);
		rightTabbedPane.addTab("LFPUBS Rule Translations", translataionsPanel);

		rightTabbedPane.setFocusable(false);

	}

	public MainMenu getMainMenu() {
		return menu;
	}

	public TranslationsPanel getTranslataionsPanel() {
		return translataionsPanel;
	}

	public void setTranslataionsPanel(TranslationsPanel translataionsPanel) {
		this.translataionsPanel = translataionsPanel;
	}

	public void addMainMenuListener(ActionListener actionListener) {
		menu.addActionListener(actionListener);
	}

	public void addConfigsListener(ActionListener actionListener) {
		configsPanel.addActionListener(actionListener);
//		this.sensorMappingPanel.addActionListener(actionListener);
//		this.actuatorMappingPanel.addActionListener(actionListener);
		this.databaseConfigsTabPanel.addActionListener(actionListener);
	}

	public void addTabbedPaneListener(MouseListener mouseListener) {
		this.rightTabbedPane.addMouseListener(mouseListener);
	}

	public void addIterationTimeRBListener(ActionListener actionListener) {
		configsPanel.addIterationTimeRBListener(actionListener);
	}
	
	public DatabaseConfigsTabPanel getDatabaseConfigsTabPanel(){
		return databaseConfigsTabPanel;
	}
	
	public void addDatabaseConfigsActionListener(ActionListener actionListener) {
		this.databaseConfigsTabPanel.addActionListener(actionListener);
	}
	public void addStratificationPanelListener(ActionListener actionListener) {
		
		configsPanel.getGeneralConfigsPanel().addStratificationPanelActionListener(actionListener);
	}

//	public SensorMappingPanel getSensorMappingPanel() {
//		return this.sensorMappingPanel;
//	}
	
	public JarsConfigsTabPanel getEventReaderConfigsPanel(){
		return this.jarsConfigsPanel;
	}

	public SystemSpecificationEditorPanel getSystemSpecificationEditorPanel() {
		return this.systemSpecificationEditorPanel;
	}

	public ConfigsTabPanel getConfigsPanel() {
		return this.configsPanel;
	}

	public DatabasePanel getDatabasePanel() {
		return this.databasePanel;
	}

//	public void enableSensorMappingTab(boolean enable) {
//		this.rightTabbedPane.setEnabledAt(2, enable);
//	}
//
//	public void enableActuatorMgrTab(boolean enable) {
//		this.rightTabbedPane.setEnabledAt(3, enable);
//	}

	public void enableEventReaderTab(boolean enable) {
		this.leftTabbedPane.setEnabledAt(2, enable);
	}

	public JTabbedPane getRightTabbedPane() {
		return this.rightTabbedPane;
	}

	public void setConfigs(MConfigurations configs) {
		
		configsPanel.setConfigs(configs);
		databaseConfigsTabPanel.setDBConfigs(configs.getDBConfigs());
		jarsConfigsPanel.setJarConfigs(configs);
			
	}

	public JSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	public void addRelativeTimeRBListener(ActionListener actionListener) {
		configsPanel.addRelativeTimeRBListener(actionListener);
		
	}

}
