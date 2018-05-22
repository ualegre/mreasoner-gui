package edu.casetools.icase.mreasoner.extensions.actuators;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.casetools.icase.mreasoner.vera.actuators.data.Action;

public class ExampleMessageDisplayActuator extends JavaActuator{

	@Override
	public void performAction(Action action) {
		JFrame messageFrame = new JFrame();
		JOptionPane.showMessageDialog(messageFrame,"Put here your custom message.");
		
	}

}
