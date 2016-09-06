package edu.casetools.mreasoner.gui.architecture.implementation.events;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MDBTypes.DB_IMPLEMENTATION;
import edu.casetools.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.mreasoner.gui.architecture.implementation.events.ssh.SSHConfigs;
import edu.casetools.mreasoner.gui.architecture.implementation.events.ssh.SSHConnection;

public class VeraLogReader {

	DatabaseOperations databaseOperations;
	SSHConnection          sshClient;
	
	public VeraLogReader(MConfigurations configs,boolean silence){

		databaseOperations = DatabaseOperationsFactory.getDatabaseOperations(DB_IMPLEMENTATION.POSTGRESQL, configs.getDBConfigs());
		if(databaseOperations.getDBConnection().checkConnection() == STATUS.CONNECTED){
			SSHConfigs sshConfigs = new SSHConfigs();
			//THIS CONFIGURATIONS MUST BE CHANGED EACH TIME YOU CHANGE THE SSH CONNECTION
			sshConfigs.setHostname("192.168.81.1");
			sshConfigs.setPassword("smarthouse123");
			sshConfigs.setUsername("root");
			sshConfigs.setSilence(silence);
			sshClient  = new SSHConnection(databaseOperations,sshConfigs);
		}else{
			System.out.println("ERROR CONECTING TO THE DATABASE");
		}
		
	}
	
	public void start(){
		sshClient.start();
	}

	public boolean isInitializationFinished(){
		return sshClient.isInitializationFinished();
	}
	
	public void stop() {

		try {
			sshClient.interrupt();
			sshClient.terminate();
			sshClient.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	}
	


	public boolean checkConnection() {
		return sshClient.checkConnection();
	}

	public boolean isFinalizationFinished() {
		return sshClient.isFinalizationFinished();
	}

}
