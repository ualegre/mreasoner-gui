package edu.casetools.mreasoner.gui;


import java.util.Vector;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.vera.actuators.device.Actuator;
import edu.casetools.mreasoner.gui.controller.Controller;
import edu.casetools.mreasoner.gui.model.Model;
import edu.casetools.mreasoner.gui.view.View;

public class Main {
	
	public static void main(String[] args) {

			Vector<Actuator> actuators = new Vector<>();
			
			//Add to the actuators vector your own list of actuators.
		
			MConfigs configs = new MConfigs();

			Model model = new Model(configs.getDBConfigs(), actuators);
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
