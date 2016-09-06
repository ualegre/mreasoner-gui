package edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.Lamp;

import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.ActuatorConfigs;

public class LampConfigs extends ActuatorConfigs{

	private String ip;
	private String port;
	
	public LampConfigs(String state){
		super(state);
		ip   = "192.168.81.1";
		port = "3480";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	
	
}
