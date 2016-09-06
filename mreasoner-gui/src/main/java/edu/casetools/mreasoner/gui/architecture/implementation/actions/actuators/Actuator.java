package edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators;

import edu.casetools.mreasoner.gui.architecture.implementation.actions.Action;
import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.Lamp.LampConfigs;

public interface Actuator {
	
	public void performAction(Action action);
	public LampConfigs getConfigs();
}
