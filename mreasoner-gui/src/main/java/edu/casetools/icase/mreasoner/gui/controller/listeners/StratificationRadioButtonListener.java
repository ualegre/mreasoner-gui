package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.casetools.icase.mreasoner.gui.controller.Controller;

public class StratificationRadioButtonListener implements ActionListener{

	Controller controller;

	public StratificationRadioButtonListener(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Yes")) simulationSelected();
		if(arg0.getActionCommand().equals("No"))  realTimeSelected();	
		
	}
	private void realTimeSelected() {
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getGeneralConfigsPanel().getStratificationPanel().getLeftRadioButton().setSelected(false);
		
	}
	private void simulationSelected() {
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getGeneralConfigsPanel().getStratificationPanel().getRightRadioButton().setSelected(false);
		
	}

}
