package edu.casetools.mreasoner.gui.architecture.implementation.events;


import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MDBTypes.DB_IMPLEMENTATION;
import edu.casetools.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.vera.logreader.VeraLogReader;
import edu.casetools.vera.logreader.ssh.SSHConfigs;


public class MVeraLogReader extends VeraLogReader{

	DatabaseOperations databaseOperations;
	//VeraLogReader vlr;
	SSHConfigs sshConfigs;
	
	
		
	public MVeraLogReader(MConfigurations configs, boolean silence){
		databaseOperations = DatabaseOperationsFactory.getDatabaseOperations(DB_IMPLEMENTATION.POSTGRESQL, configs.getDBConfigs());
		if(databaseOperations.getDBConnection().checkConnection() == STATUS.CONNECTED){
			this.registerObserver(new MDataManager(databaseOperations));
			initSSHConfigs(silence);
			//startVera();
		}else{
			System.out.println("ERROR CONECTING TO THE DATABASE");
		}	
	}


	private SSHConfigs initSSHConfigs(boolean silence) {
		sshConfigs = SSHConfigs.getInstance();
		//THIS CONFIGURATIONS MUST BE CHANGED EACH TIME YOU CHANGE THE SSH CONNECTION
		sshConfigs.setPassword("smarthouse123");
		sshConfigs.setUsername("root");
		sshConfigs.setSilence(silence);
		sshConfigs.setHostname("10.12.102.156");
		return sshConfigs;
	}

	}

