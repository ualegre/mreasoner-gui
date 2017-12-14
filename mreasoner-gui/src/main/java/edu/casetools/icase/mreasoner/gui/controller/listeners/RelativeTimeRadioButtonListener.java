package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.casetools.icase.mreasoner.gui.controller.Controller;

public class RelativeTimeRadioButtonListener implements ActionListener{

	Controller controller;

	public RelativeTimeRadioButtonListener(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Yes")) acceptSelected();
		if(arg0.getActionCommand().equals("No"))  {
			negationSelected();	
		}
		
	}
	private void negationSelected() {
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().getRelativeTimeRBPanel().getLeftRadioButton().setSelected(false);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().enableExecutionTimeTf(false);
	}
	private void acceptSelected() {
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().getRelativeTimeRBPanel().getRightRadioButton().setSelected(false);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().enableExecutionTimeTf(true);
	}
	


}
