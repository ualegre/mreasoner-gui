package edu.casetools.mreasoner.gui.View.Panels.TableModels.Models;


import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.View.Panels.TableModels.DefaultModel;

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
