package edu.casetools.icase.mreasoner.gui.view.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.configs.data.db.MDBConfigs;
import edu.casetools.icase.mreasoner.gui.view.panels.main.DatabasePanel;
import edu.casetools.icase.mreasoner.gui.view.panels.main.SystemSpecificationEditorPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.main.TranslationsPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.menu.MainMenu;
import edu.casetools.icase.mreasoner.gui.view.panels.options.configs.database.DatabaseConfigsTabPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.options.configs.general.ConfigsTabPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.options.configs.ssh.SSHConfigsTabPanel;
import edu.casetools.icase.mreasoner.vera.sensors.ssh.configs.SSHConfigs;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplitPane;
	private JTabbedPane leftTabbedPane, rightTabbedPane;
	private SystemSpecificationEditorPanel systemSpecificationEditorPanel;
	private TranslationsPanel translationPanel;
	private ConfigsTabPanel configsPanel;
	private DatabasePanel databasePanel;
	private MainMenu menu;
	private DatabaseConfigsTabPanel databaseConfigsTabPanel;
	private SSHConfigsTabPanel sshConfigsTabPanel;

	public MainPanel(MConfigs configs) {

		menu 						   = new MainMenu(configs);
		configsPanel 				   = new ConfigsTabPanel(configs);
		systemSpecificationEditorPanel = new SystemSpecificationEditorPanel();
		translationPanel   		   	   = new TranslationsPanel();
		databasePanel 				   = new DatabasePanel(configs.getTimeConfigs());
		databaseConfigsTabPanel    	   = new DatabaseConfigsTabPanel(configs.getDBConfigs());
		sshConfigsTabPanel			   = new SSHConfigsTabPanel(configs.getSshConfigs());
		
		createLeftTabbedPane(configs);
		createRightTabbedPane(configs);
		createMainSplitPanel();
		
		this.setLayout(new BorderLayout());
		this.add(mainSplitPane);
		this.add(menu, BorderLayout.NORTH);

	}

	private void createMainSplitPanel() {
		mainSplitPane = new JSplitPane();

		mainSplitPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));		
		
		mainSplitPane.setLeftComponent(leftTabbedPane);
		mainSplitPane.setRightComponent(rightTabbedPane);
		mainSplitPane.setResizeWeight(0.5);
		mainSplitPane.setDividerSize(5);
		
	}

	private void createLeftTabbedPane(MConfigs configs) {
		leftTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		leftTabbedPane.add("General", configsPanel);
		leftTabbedPane.add("Database", databaseConfigsTabPanel);		
		leftTabbedPane.add("Vera", sshConfigsTabPanel);
		
		leftTabbedPane.setFocusable(false);
		leftTabbedPane.setEnabledAt(2, false);
	}
	
	

	private void createRightTabbedPane(MConfigs configs) {
		rightTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rightTabbedPane.addTab("M Specification File Editor", systemSpecificationEditorPanel);
		rightTabbedPane.addTab("Database Results", databasePanel);
		rightTabbedPane.addTab("LFPUBS Rule Translations", translationPanel);

		rightTabbedPane.setFocusable(false);

	}

	public MainMenu getMainMenu() {
		return menu;
	}

	public TranslationsPanel getTranslataionsPanel() {
		return translationPanel;
	}

	public void setTranslataionsPanel(TranslationsPanel translataionsPanel) {
		this.translationPanel = translataionsPanel;
	}

	public void addMainMenuListener(ActionListener actionListener) {
		menu.addActionListener(actionListener);
	}

	public void addConfigsListener(ActionListener actionListener) {
		configsPanel.addActionListener(actionListener);
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

	public SystemSpecificationEditorPanel getSystemSpecificationEditorPanel() {
		return this.systemSpecificationEditorPanel;
	}

	public ConfigsTabPanel getConfigsPanel() {
		return this.configsPanel;
	}

	public DatabasePanel getDatabasePanel() {
		return this.databasePanel;
	}

	public void enableEventReaderTab(boolean enable) {
		this.leftTabbedPane.setEnabledAt(2, enable);
	}

	public JTabbedPane getRightTabbedPane() {
		return this.rightTabbedPane;
	}

	public void setConfigs(MConfigs configs) {
		
		configsPanel.setConfigs(configs);
		databaseConfigsTabPanel.setDBConfigs(configs.getDBConfigs());
		sshConfigsTabPanel.setSSHConfigs(configs.getSshConfigs());
			
	}

	public MConfigs getConfigs() {
		MDBConfigs dbConfigs = new MDBConfigs();
		SSHConfigs sshConfigs = new SSHConfigs();
		
		MConfigs configs = configsPanel.getConfigs();
		configs.setDBConfigs(databaseConfigsTabPanel.getDBConfigs(dbConfigs));
		configs.setSshConfigs(sshConfigsTabPanel.getSSHConfigs(sshConfigs));
		
		return configs;
			
	}

	
	public JSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	public void addRelativeTimeRBListener(ActionListener actionListener) {
		configsPanel.addRelativeTimeRBListener(actionListener);
		
	}

	public SSHConfigsTabPanel getSshConfigsTabPanel() {
		return sshConfigsTabPanel;
	}

}
