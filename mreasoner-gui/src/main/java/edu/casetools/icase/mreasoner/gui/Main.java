package edu.casetools.icase.mreasoner.gui;


import java.util.Vector;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.database.core.MDBImplementations;
import edu.casetools.icase.mreasoner.deployment.sensors.SensorObserver;
import edu.casetools.icase.mreasoner.deployment.sensors.Sensor;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.model.Model;
import edu.casetools.icase.mreasoner.gui.view.View;
import edu.casetools.icase.mreasoner.vera.actuators.device.Actuator;

public class Main {
	
	public static void main(String[] args) {

			MConfigs configs = new MConfigs();
		
			Vector<Actuator> actuators = new Vector<>();
			
			//Add to the actuators vector your own list of actuators.
		
			//Vector<Sensor> sensors = new Vector<>();
			
			//Add to the actuators vector your own list of sensors.
			//Sensor mySensor = new Sensor("name","1","0",true);

			
			
			Vector<String> states = new Vector<String>();
			states.add("lowBattery");
			states.add("medBattery");
			states.add("highBattery");
			
			
			Vector<SensorObserver> sensorObservers = new Vector<>();
			// Add your own list of sensor observers and its corresponding modelling rules
			TemperatureObserver temperatureObserver = new TemperatureObserver();
			Sensor temperatureSensor = new Sensor("05","TemperatureSensor", "JXF193", "Kitchen", MDBImplementations.DATA_TYPES.INTEGER.toString(), "100", "-100", false,states);
			temperatureObserver.setSensor(temperatureSensor);

			sensorObservers.add(temperatureObserver);

			Model model = new Model(configs.getDBConfigs(), actuators, sensorObservers);
			View view = new View(configs);
			Controller controller = new Controller(view,model,configs.getFilesConfigs());
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controller.setDividersAtDefaultLocation();

		
	}

}
