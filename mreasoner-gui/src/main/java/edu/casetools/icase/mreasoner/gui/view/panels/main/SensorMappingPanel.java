package edu.casetools.icase.mreasoner.gui.view.panels.main;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.casetools.icase.mreasoner.gui.view.panels.main.mapping.ImplementationPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.main.mapping.MappingPanel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.SensorImplementationTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.SensorsTableModel;

public class SensorMappingPanel extends JPanel{

private static final long serialVersionUID = 1L;
	
	JLabel maxValueLabel,minValueLabel;
	MappingPanel sensorMappingPanel;
	ImplementationPanel implementationPanel;
	//JTable mappingTable,implementationTable;
	JComboBox<String> implementationComboBox;
	SensorsTableModel mappingModel;
	SensorImplementationTableModel implementationModel;
	//JScrollPane mappingScroll,implementationScroll;
	JTextField sensorTf,stateTf,maxValueTf,minValueTf;

	public SensorMappingPanel(){
	
		this.setLayout(new GridLayout(2,1));
		this.createMappingPanel();
		this.createImplementationPanel();

	}
	
	private void createImplementationPanel() {
		implementationPanel = new ImplementationPanel(false);
		implementationModel = new SensorImplementationTableModel();
		implementationPanel.setModel(implementationModel);
		this.add(implementationPanel);
	}

	private void createMappingPanel() {
		sensorMappingPanel = new MappingPanel();
		sensorMappingPanel.getImplementationLabel().setText("Sensor Implementation");
		//LOAD COMBOBOX
			
		mappingModel = new SensorsTableModel();		


		sensorMappingPanel.setModel(mappingModel);
		this.add(sensorMappingPanel);
	}

	public SensorsTableModel getSensorsTableModel(){
		return this.mappingModel;
	}

	public void addActionListener(ActionListener actionListener) {
		sensorMappingPanel.addActionListener(actionListener);
		implementationPanel.addActionListener(actionListener);
	}

	public MappingPanel getMappingPanel() {
		return sensorMappingPanel;
	}
	
	public ImplementationPanel getImplementationPanel() {
		return implementationPanel;
	}

	public void refreshSensorsTable(SensorsTableModel tm) {
		sensorMappingPanel.getTable().setModel(tm);		
	}
	
	public void refreshImplementationsTable(SensorsTableModel tm) {
		sensorMappingPanel.getTable().setModel(tm);		
	}
	
}
