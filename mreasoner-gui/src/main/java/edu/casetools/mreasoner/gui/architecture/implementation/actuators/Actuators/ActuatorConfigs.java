package edu.casetools.mreasoner.gui.architecture.implementation.actuators.Actuators;

public abstract class ActuatorConfigs {
	 String state;
	 
	 public ActuatorConfigs(String state){
		 this.state = state;
	 }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	 
	 
}
