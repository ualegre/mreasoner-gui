package edu.casetools.icase.mreasoner.gui;

import edu.casetools.icase.mreasoner.extensions.modules.ExampleDeploymentModule;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.model.Model;
import edu.casetools.icase.mreasoner.gui.view.View;

public class Main {
	
	public static void main(String[] args) {
			
			Controller controller = new Controller(new View(),new Model(new ExampleDeploymentModule()));
			controller.start();
			
	}

}
