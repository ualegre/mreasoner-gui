package edu.casetools.mreasoner.gui;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.gui.controller.Controller;
import edu.casetools.mreasoner.gui.model.Model;
import edu.casetools.mreasoner.gui.view.View;

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
