package edu.casetools.icase.mreasoner.extensions.sensors;

import edu.casetools.icase.mreasoner.deployment.sensors.SensorObserver;

public class TemperatureObserver extends SensorObserver{


	@Override
	protected boolean applyCustomModellingRules(String stateName, String iteration, String value) {
		int sensorValue = Integer.valueOf(value);
		boolean result = false;
		
		if(stateName.equals("BatteryLow")){
			result = applyBatteryLowModellingRule(sensorValue);
		} else if(stateName.equals("BatteryMed")){
			result = applyBatteryMedModellingRule(sensorValue);
		} else if(stateName.equals("BatteryHigh")){
			result = applyBatteryHighModellingRule(sensorValue);
		} 
		return result;
	}

	private boolean applyBatteryHighModellingRule(int sensorValue) {
		if(sensorValue >= 70) return true;
		else return false;
	}

	private boolean applyBatteryMedModellingRule(int sensorValue) {
		if(sensorValue < 70 && sensorValue > 30) return true;
		else return false;
	}

	private boolean applyBatteryLowModellingRule(int sensorValue) {
		if(sensorValue <= 30) return true;
		else return false;
	}
	
	

}
