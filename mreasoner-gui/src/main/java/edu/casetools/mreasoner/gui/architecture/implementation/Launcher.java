package edu.casetools.mreasoner.gui.architecture.implementation;


import java.io.FileNotFoundException;
import java.util.Vector;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.reader.ParseException;
import edu.casetools.mreasoner.core.MReasoner;
import edu.casetools.mreasoner.gui.architecture.implementation.actuators.ActuatorManager;
import edu.casetools.mreasoner.gui.architecture.implementation.elements.LibraryThread;
import edu.casetools.mreasoner.gui.architecture.implementation.elements.SystemData;
import edu.casetools.mreasoner.gui.architecture.implementation.events.VeraLogReader;
import edu.casetools.mreasoner.gui.architecture.implementation.loader.SystemLoader;



public class Launcher {

	SystemData 	      systemData;
	SystemLoader 	  testCaseLoader;
	MReasoner 		  mtpl;
	ActuatorManager   actuatorManager;
	VeraLogReader		  eventReader;
	Vector<LibraryThread> externalLibraries;
	

	
	public Launcher(){
		testCaseLoader = new SystemLoader();
		externalLibraries = new Vector<LibraryThread>();
		

	}
	
	public boolean loadSystem(String configFileName) {
		try {
			systemData = testCaseLoader.getSystemLoadData(configFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (edu.casetools.mreasoner.core.compiler.realtime.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
	}
	
	public void initializeExternalJars(){
		if(systemData != null){
			String[] commands = systemData.getSystemConfigs().getJarConfigs().split(",");
//			String a = "java -jar c:/b.jar";
//			String[] commands = a.split(",");
	
			//System.out.println("LLEGO");
			for(int i=0;i<commands.length;i++){
				//System.out.println("GO! "+commands[i]);
				//synchronizationStart();
				LibraryThread thread = new LibraryThread(commands[i]);
				externalLibraries.add(thread);
				thread.start();
			}
			//System.out.println("FINISH");
		}
	}
	

	
	
	public void initializeReasoner(){

		if(systemData != null){
			MConfigurations configs = systemData.getSystemConfigs();
			mtpl = new MReasoner(systemData.getSystemInput(),configs);

			
			initializeExternalJars();
//			System.out.println("\nINITIALIZING ACTUATOR MANAGER..\n");
//			actuatorManager = new ActuatorManager(configs.getDBConfigs());
//			actuatorManager.start();
//			sleep(500);
//			
//			System.out.println("\nINITIALIZING EVENT READER..\n");
//			eventReader = new EventReader(configs,true);
//			eventReader.startSSHConnection();
//			sleep(500);
			
//			while(!eventReader.isInitializationFinished()){
//				sleep(1);
//			}
//			if(eventReader.checkConnection()){
			
				System.out.println("\nINITIALIZING REASONER..\n");
				mtpl.MTPLInitialization();
				mtpl.run();	
//			} else  {System.out.println("SSH Connection error");}
		}


	}
	
	public void terminateReasoner(){
		try {

			
			mtpl.terminate();
			mtpl.join();
			
			eventReader.stop();
			while(!eventReader.isFinalizationFinished()){
				sleep(1);
			}
			actuatorManager.terminate();
			actuatorManager.join();
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	

	
	private void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
