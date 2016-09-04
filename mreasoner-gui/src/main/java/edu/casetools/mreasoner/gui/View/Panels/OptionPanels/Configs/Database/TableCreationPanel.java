package edu.casetools.mreasoner.gui.View.Panels.OptionPanels.Configs.Database;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import edu.casetools.mreasoner.gui.SpringUtilities.SpringUtilities;

public class TableCreationPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	JCheckBox eventsCb,internalEventsCb,resultsCb,sensorsCb,
			  actuatorsCb,sensorsImplementationCb,actuatorsImplementationCb;
	JButton   checkButton,selectAllButton,clearButton;
	
	JPanel    mainPanel,buttonsPanel;
	
	TitledBorder titledBorder;
	
	public TableCreationPanel(){
		this.initializeElements();
		this.unfocusCheckBoxes();
		this.addElements();
	}
	
	private void addElements() {
		this.addCheckBoxes();
		this.addButtons();
		this.addPanels();
	}

	private void addPanels() {
        SpringUtilities.makeCompactGrid(mainPanel,
                4, 2, 		  //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(buttonsPanel,BorderLayout.SOUTH);
        this.setBorder(titledBorder);
	}

	private void addButtons() {
		buttonsPanel.add(checkButton);
		buttonsPanel.add(selectAllButton);
		buttonsPanel.add(clearButton);
		buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,200,0,200));
	}



	private void addCheckBoxes() {
		mainPanel.add(eventsCb);
		mainPanel.add(internalEventsCb);
		mainPanel.add(resultsCb);
		mainPanel.add(new JPanel());
		mainPanel.add(sensorsCb);
		mainPanel.add(actuatorsCb);
		mainPanel.add(sensorsImplementationCb);
		mainPanel.add(actuatorsImplementationCb);
		
	}

	private void initializeElements(){
		titledBorder	  = BorderFactory.createTitledBorder("Clear Tables");
		this.initializePanels();
		this.initializeCheckBoxes();
		this.initializeJButtons();
	}
	
	private void initializePanels(){
		mainPanel = new JPanel(new SpringLayout());
		buttonsPanel = new JPanel(new GridLayout(1,3));
	}
	
	private void initializeCheckBoxes(){
		eventsCb 		          = new JCheckBox("Events Table");
		internalEventsCb		  = new JCheckBox("Internal Events Table");
		resultsCb				  = new JCheckBox("Results Table");
		sensorsCb				  = new JCheckBox("Sensors Table");
		actuatorsCb				  = new JCheckBox("Actuators Table");
		sensorsImplementationCb	  = new JCheckBox("Sensor Implementation Table");
		actuatorsImplementationCb = new JCheckBox("Actuator Implementation Table");

	}
	
	private void unfocusCheckBoxes(){
		eventsCb.setFocusable(false);
		internalEventsCb.setFocusable(false);
		resultsCb.setFocusable(false);
		sensorsCb.setFocusable(false);
		actuatorsCb.setFocusable(false);
		sensorsImplementationCb.setFocusable(false);
		actuatorsImplementationCb.setFocusable(false);
		checkButton.setFocusable(false);
		selectAllButton.setFocusable(false);
		clearButton.setFocusable(false);
	}
	
	private void initializeJButtons(){
		checkButton = new JButton("Check");
		selectAllButton = new JButton("Select All");
		clearButton = new JButton("Clear Selected");
	}

	public void addActionListener(ActionListener actionListener) {
		checkButton.addActionListener(actionListener);
		selectAllButton.addActionListener(actionListener);
		clearButton.addActionListener(actionListener);
		
	}

	public void selectAll(boolean select) {
		
		eventsCb.setSelected(select);
		internalEventsCb.setSelected(select);
		resultsCb.setSelected(select);
		sensorsCb.setSelected(select);
		actuatorsCb.setSelected(select);
		sensorsImplementationCb.setSelected(select);
		actuatorsImplementationCb.setSelected(select);
		
	}

	public JCheckBox getEventsCb() {
		return eventsCb;
	}

	public JCheckBox getInternalEventsCb() {
		return internalEventsCb;
	}

	public JCheckBox getResultsCb() {
		return resultsCb;
	}

	public JCheckBox getSensorsCb() {
		return sensorsCb;
	}

	public JCheckBox getActuatorsCb() {
		return actuatorsCb;
	}

	public JCheckBox getSensorsImplementationCb() {
		return sensorsImplementationCb;
	}

	public JCheckBox getActuatorsImplementationCb() {
		return actuatorsImplementationCb;
	}
	
	
	
}
