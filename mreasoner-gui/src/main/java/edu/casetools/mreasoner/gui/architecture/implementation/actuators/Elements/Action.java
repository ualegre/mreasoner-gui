package edu.casetools.mreasoner.gui.architecture.implementation.actuators.Elements;

public class Action {
	
	String device;
	String status;
	
	public Action(){
		device = "-1";
		status  = "-1";
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getValue() {
		return status;
	}

	public void setValue(boolean value) {
		if(value)
			this.status = "1";
		else
			this.status = "0";
	}

	public boolean equals(Action action){
		boolean devicesEqual = this.device.equals(action.getDevice());
		boolean statusEqual  = this.status.equals(action.getValue());
		boolean result = devicesEqual && statusEqual;
		
		return result;
	}
	
}
