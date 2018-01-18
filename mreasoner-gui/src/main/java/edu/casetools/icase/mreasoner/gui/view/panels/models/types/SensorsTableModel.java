package edu.casetools.icase.mreasoner.gui.view.panels.models.types;

import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.view.panels.models.DefaultModel;

public class SensorsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;

	
	public SensorsTableModel(){
	
	}
	
	public void updateTable(DatabaseOperations con){

		super.defaultUpdateTable(con.getDeviceMappingTableContent());
		      
	}
	
	public void removeSensorRelation(DatabaseOperations con,String device, String state){
		con.removeDeviceMappingTableRelation(device, state);

	}
	
	public void addSensorRelation(DatabaseOperations con,String device, String state){
		con.newDeviceMappingTableRelation(device, state);
	}
}
