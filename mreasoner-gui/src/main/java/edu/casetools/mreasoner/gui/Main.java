package edu.casetools.mreasoner.gui;

import edu.casetools.mreasoner.gui.Controller.Controller;
import edu.casetools.mreasoner.gui.Model.Model;
import edu.casetools.mreasoner.gui.View.View;
import edu.casetools.mreasoner.input.configurations.MConfigurations;

public class Main {
	
	public static void main(String[] args) {

			MConfigurations configs = new MConfigurations();

			Model model = new Model(configs);
			View view = new View(configs);
			Controller controller = new Controller(view,model,configs);
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			controller.setDividersAtDefaultLocation();

		
	}

}
