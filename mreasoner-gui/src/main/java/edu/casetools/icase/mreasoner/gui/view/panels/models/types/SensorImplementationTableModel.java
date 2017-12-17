package edu.casetools.icase.mreasoner.gui.view.panels.models.types;


import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.view.panels.models.DefaultModel;

public class SensorImplementationTableModel extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getSensorImplementationTableContent());
	}

	public void removeSensorImplementationRelation(DatabaseOperations con,String name,String maxValue, String minValue, String isOnOff){
		con.removeSensorImplementation(name, maxValue, minValue, isOnOff);

	}
	
	public void addSensorImplementationRelation(DatabaseOperations con,String name,String maxValue, String minValue, String isOnOff){
		con.newSensorImplementation(name, maxValue, minValue, isOnOff);
	}
	

}
