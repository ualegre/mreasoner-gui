package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.casetools.icase.mreasoner.gui.controller.Controller;

public class IterationTimeRadioButtonListener implements ActionListener{

	Controller controller;

	public IterationTimeRadioButtonListener(Controller controller){
		this.controller = controller;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Yes")) positiveSelected();
		if(arg0.getActionCommand().equals("No"))  {
			negativeSelected();	
		}
		
	}
	private void negativeSelected() {
		enableElements(false);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().getIterationTimePanel().getLeftRadioButton().setSelected(false);
		
	}
	private void positiveSelected() {
		enableElements(true);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().getIterationTimePanel().getRightRadioButton().setSelected(false);
		
	}
	
	private void enableElements(boolean enable){
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().getTimeUnit().setEditable(enable);
	}


}
