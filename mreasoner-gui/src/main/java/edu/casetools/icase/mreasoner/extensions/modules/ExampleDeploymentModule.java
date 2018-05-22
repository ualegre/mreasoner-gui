package edu.casetools.icase.mreasoner.extensions.modules;

import java.util.Vector;

import edu.casetools.icase.mreasoner.database.core.MDBImplementations;
import edu.casetools.icase.mreasoner.deployment.realenvironment.AbstractDeploymentModule;
import edu.casetools.icase.mreasoner.deployment.sensors.Sensor;
import edu.casetools.icase.mreasoner.extensions.actuators.ExampleMessageDisplayActuator;
import edu.casetools.icase.mreasoner.extensions.sensors.BatteryObserver;
import edu.casetools.icase.mreasoner.vera.actuators.device.VeraActuator;

public class ExampleDeploymentModule extends AbstractDeploymentModule {
	
	public ExampleDeploymentModule(){
		super();	
	}
	
	@Override
	protected void initialiseSensorObservers() {
		initialiseTemperatureSensorObserver();
	}

	private void initialiseTemperatureSensorObserver() {
		Vector<String> states 					= initialiseTemperatureSensorStates();
		BatteryObserver temperatureObserver = new BatteryObserver();	
		Sensor temperatureSensor 				= new Sensor("05","TemperatureSensor", "JXF193", "Kitchen",
				MDBImplementations.DATA_TYPES.INTEGER.toString(),"-100", "100", false,states);
		temperatureObserver.setSensor(temperatureSensor);
		this.sensorObservers.add(temperatureObserver);
	}

	private Vector<String> initialiseTemperatureSensorStates() {
		Vector<String> states = new Vector<String>();
		states.add("lowBattery");
		states.add("medBattery");
		states.add("highBattery");
		return states;
	}
	
	@Override
	protected void initialiseActuators(){
		VeraActuator lampActuator = new VeraActuator("urn:upnp-org:serviceId:SwitchPower1","SetTarget");
		this.actuators.add(lampActuator);
		
		ExampleMessageDisplayActuator messageActuator = new ExampleMessageDisplayActuator();
		this.actuators.add(messageActuator);
		
	}
	
}
