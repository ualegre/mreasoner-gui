package edu.casetools.mreasoner.gui.Model.Reasoner;

import edu.casetools.mreasoner.gui.Controller.Controller;
import edu.casetools.mreasoner.input.configurations.MConfigurations;


public class ArchitectureManager {

	RealArchitecture realArchitecture;
	TestArchitecture testArchitecture;

	
	public void executeReasoner(MConfigurations configs,Controller controller){

		configureExecutionMode(configs,controller);
		
	}
	
	public void configureExecutionMode( MConfigurations configs,Controller controller){
		switch(configs.getExecutionMode()){
		case REAL_ENVIRONMENT:
			executeRealArchitectureThread(configs);
			break;
		case SIMULATION_ITERATION:
			executeTestArchitectureThread(controller,configs);
			break;
		case SIMULATION_REAL_TIME:
			executeTestArchitectureThread(controller,configs);
			break;
		default:
			break;
		
		}
	}
	
	
	
	private void executeRealArchitectureThread(MConfigurations configs){
	 testArchitecture = null;
	 realArchitecture = new RealArchitecture(configs.getSessionFilePath());
		try {
			realArchitecture.join();
			realArchitecture.start();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	private void executeTestArchitectureThread(Controller controller,MConfigurations configs){
		 testArchitecture = new TestArchitecture(controller,configs.getSessionFilePath());
		 realArchitecture = null;
		try {
			testArchitecture.join();
			testArchitecture.start();

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void interruptThread(){
		if(realArchitecture != null){
			realArchitecture.terminate();
		}
		if(testArchitecture != null){
			testArchitecture.terminate();
		}
	//	reasoner.interrupt();
		//reasoner.destroy();
	}
	
	
}
