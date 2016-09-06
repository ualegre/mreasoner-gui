package edu.casetools.mreasoner.gui.architecture.implementation.actuators.Actuators;

import edu.casetools.mreasoner.gui.architecture.implementation.actuators.Actuators.Lamp.LampConfigs;
import edu.casetools.mreasoner.gui.architecture.implementation.actuators.Elements.Action;

public interface Actuator {
	
	public void performAction(Action action);
	public LampConfigs getConfigs();
}
