package edu.casetools.mreasoner.gui.architecture.implementation.actuators.Actuators.Lamp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import edu.casetools.mreasoner.gui.architecture.implementation.actuators.Actuators.Actuator;
import edu.casetools.mreasoner.gui.architecture.implementation.actuators.Elements.Action;



public class LampActuator implements Actuator{
	URL service;
	URLConnection connection;
	LampConfigs configs;
	Action lastAction;
	public LampActuator(LampConfigs configs){
		this.configs = configs;
		lastAction = new Action();
	}
	

	public void performAction(Action action) {
		// TODO Auto-generated method stub
		try {
		if(!lastAction.equals(action)){
			String url = "http://"+configs.getIp()+":"+configs.getPort()+
					"/data_request?id=lu_action&output_format=xml&DeviceNum="+action.getDevice()+
					"&serviceId=urn:upnp-org:serviceId:SwitchPower1&action=SetTarget&newTargetValue="+
					action.getValue();
			
			service = new URL(url);
			connection = service.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    connection.getInputStream()));
	  //      String inputLine;
	        System.out.println("ACTUATOR MANAGER: SWITCHING LAMP "+action.getDevice()+" TO VALUE "+action.getValue());
//	        while ((inputLine = in.readLine()) != null) 
//	            System.out.println(inputLine);
	        in.close();
		}
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		lastAction = action;

	}
	@Override
	public LampConfigs getConfigs() {
		return configs;
	}

	
//	service = new URL("http://192.168.81.1:3480/data_request?id=lu_action&output_format=xml&DeviceNum=4&serviceId=urn:upnp-org:serviceId:SwitchPower1&action=SetTarget&newTargetValue=1");

	
}
