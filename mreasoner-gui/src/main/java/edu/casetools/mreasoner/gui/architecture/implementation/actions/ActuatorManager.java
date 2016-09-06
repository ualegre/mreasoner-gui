package edu.casetools.mreasoner.gui.architecture.implementation.actions;

import java.util.Vector;

import edu.casetools.mreasoner.configurations.data.MDBConfigs;
import edu.casetools.mreasoner.configurations.data.MDBTypes.DB_IMPLEMENTATION;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.Actuator;
import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.ActuatorConfigs;
import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.Lamp.LampActuator;
import edu.casetools.mreasoner.gui.architecture.implementation.actions.actuators.Lamp.LampConfigs;



public class ActuatorManager extends Thread{

	private boolean running;
	private DatabaseOperations databaseOperations;
	//private LampActuator lampOn;
	
	private Vector<Actuator> actuators;
	public ActuatorManager(MDBConfigs configs){
		actuators = new Vector<Actuator>();
		databaseOperations = DatabaseOperationsFactory.getDatabaseOperations(DB_IMPLEMENTATION.POSTGRESQL, configs);
		running = true;
		
	}
	
	private void initialization(){
		LampConfigs lampOnConfigs = new LampConfigs("lampOn");
		LampActuator lampOn = new LampActuator(lampOnConfigs);
		actuators.add(lampOn);
		
	}
	
	public void run(){
		initialization();
		while (running)
		{
			for(int i=0;i<actuators.size();i++){
				Action action = readAction(actuators.get(i).getConfigs());				
				actuators.get(i).performAction(action);
			}
		}
	}
	
	private Action readAction(ActuatorConfigs actuatorConfigs){
		Action action  = new Action();
		String state   = actuatorConfigs.getState();

		String device  = databaseOperations.getDevice(state);
		boolean status = databaseOperations.getStatus(state);
		
		if(device != null){
			action.setDevice(device);
			action.setValue(status);
		}
		
		return action;
	}

	public void terminate(){
		running = false;
	}
	
}
