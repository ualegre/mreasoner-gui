package edu.casetools.mreasoner.gui.architecture.implementation.elements;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.core.elements.MInputData;


public class SystemData {

	MInputData 		systemInput;
	MConfigurations   		systemConfigs; 

	public MInputData getSystemInput() {
		return systemInput;
	}

	public void setSystemInput(MInputData systemInput) {
		this.systemInput = systemInput;
	}

	public MConfigurations getSystemConfigs() {
		return systemConfigs;
	}

	public void setSystemConfigs(MConfigurations systemConfigs) {
		this.systemConfigs = systemConfigs;
	}



}
