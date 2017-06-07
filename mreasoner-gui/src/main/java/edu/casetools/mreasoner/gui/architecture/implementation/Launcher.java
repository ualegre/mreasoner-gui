package edu.casetools.mreasoner.gui.architecture.implementation;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.reader.ParseException;
import edu.casetools.mreasoner.core.MReasoner;
import edu.casetools.mreasoner.gui.architecture.implementation.elements.LibraryThread;
import edu.casetools.mreasoner.gui.architecture.implementation.elements.SystemData;
import edu.casetools.mreasoner.gui.architecture.implementation.events.MVeraLogReader;
import edu.casetools.mreasoner.gui.architecture.implementation.loader.SystemLoader;


public class Launcher {

	SystemData 	      systemData;
	SystemLoader 	  testCaseLoader;
	MReasoner 		  mtpl;
	MVeraLogReader		  eventReader;
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
	
			for(int i=0;i<commands.length;i++){
				LibraryThread thread = new LibraryThread(commands[i]);
				externalLibraries.add(thread);
				thread.start();
			}
		}
	}
	
	public void initializeReasoner(){

		if(systemData != null){
			MConfigurations configs = systemData.getSystemConfigs();
			mtpl = new MReasoner(systemData.getSystemInput(),configs);

			
			initializeExternalJars();
			System.out.println("\nINITIALIZING ACTUATOR MANAGER..\n");
			sleep(500);
			
			System.out.println("\nINITIALIZING EVENT READER..\n");
			eventReader = new MVeraLogReader(configs,false);
			eventReader.start();
			sleep(500);
			
			while(!eventReader.getSshClient().isInitializationFinished()){
				sleep(1);
			}
			if(eventReader.checkConnection()){
				System.out.println("\nINITIALIZING REASONER..\n");
				mtpl.MTPLInitialization();
				mtpl.run();	
			
		}startSSHConnection();
			} else  {System.out.println("SSH Connection error");
				}


	}
	
	public void terminateReasoner(){
		try {

			
			mtpl.terminate();
			mtpl.join();
			
			eventReader.stop();
			while(!eventReader.getSshClient().isFinalizationFinished()){
				sleep(1);
			}
			if(externalLibraries != null){
				for(LibraryThread library : externalLibraries){
					library.interrupt();
					library.join();
				}
			}
			

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
	public void stop() {
		eventReader.stop();
	}
	public void startSSHConnection() {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		eventReader.stop();
		in.close();
		
		
	}
	
	
}
