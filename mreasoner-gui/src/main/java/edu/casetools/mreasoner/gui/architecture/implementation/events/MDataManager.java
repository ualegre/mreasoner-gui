package edu.casetools.mreasoner.gui.architecture.implementation.events;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.vera.logreader.VeraLogDataManager;
import edu.casetools.vera.logreader.data.VeraData;
import edu.casetools.vera.logreader.data.VeraEvent;
import edu.casetools.vera.logreader.data.VeraVariable;



public class MDataManager extends VeraLogDataManager {

	DatabaseOperations databaseOperations;
	
	public MDataManager(DatabaseOperations databaseOperations){
		this.databaseOperations = databaseOperations;
	}
	
	@Override
	protected void storeVeraEvent(VeraData data) {
		VeraEvent event = data.getEvent();
		 if(event != null){	 
		 	 String state = databaseOperations.getState(event.getDevice());
		 	 if(state != null){
		     	 databaseOperations.insertEvent(state, Boolean.toString(event.getStatus()),"-1", event.getDate(), event.getTime());
		     	 printEvent(event, state);
		     } else printEventWarning(event);
		  }
		
	}

	@Override
	protected void storeVeraVariable(VeraData data) {
		VeraVariable variable = data.getVariable();
		 if(!variable.isEmpty()){	
			 if(variable.getVariable().contentEquals(VeraVariable.VAR_TRIPPED)||(variable.getVariable().contentEquals(VeraVariable.VAR_STATUS))){
				 
		 	 String state = databaseOperations.getState(variable.getDeviceId());

		 	 if(state != null){
				 System.out.println("******************Change on State: "+state+"**********************************");
		     	 databaseOperations.insertEvent(state, ""+variable.getNewValue(),"-1", variable.getDate(), variable.getTime());
		     	 printVariable(variable, state);
		     	 } else printVariableWarning(variable);
		 	      System.out.println("***************************************************************************");

			 }
		 }	
	}

	private void printVariableWarning(VeraVariable variable) {
		System.out.println("WARNING! Device id:"+variable.getDeviceId()+
				 " not recogniced in database table "+
				 databaseOperations.getDBConnection().getDBConfigs().getTable()+
				 "_events");
	}

	private void printVariable(VeraVariable variable, String state) {
		System.out.println("\n VERA LOG READER: Variable Detection: "+state+ " - "
		 +variable.getNewValue()+" - "+ variable.getDate()+" "+ variable.getTime());
	}
	
	private void printEventWarning(VeraEvent event) {
		System.out.println("WARNING! Event Reading Failed. Device id:"
				 +event.getDevice()+
				 " not recogniced in database table "+
				 databaseOperations.getDBConnection().getDBConfigs().getTable()+
				 "_events");
	}

	private void printEvent(VeraEvent event, String state) {
		System.out.println("\n VERA LOG READER: Event Detection: "+state+ " - "
		 +event.getStatus()+" - "+ event.getDate()+" "+ event.getTime());
	}

//	@Override
//	protected void storeVeraEvent(VeraData data) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	protected void storeVeraVariable(VeraData data) {
//		// TODO Auto-generated method stub
//		
//	}

}
