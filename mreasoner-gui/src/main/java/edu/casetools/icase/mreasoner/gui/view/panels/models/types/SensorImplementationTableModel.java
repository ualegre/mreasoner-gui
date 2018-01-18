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
		super.defaultUpdateTable(con.getDevicesTableContent());
	}

	public void removeSensorImplementationRelation(DatabaseOperations con,String name,String maxValue, String minValue, String isBoolean){
		con.removeSensorImplementation(name, maxValue, minValue, isBoolean);

	}
	
	public void addSensorImplementationRelation(DatabaseOperations con, String id, String name, String model, 
			String location, String dataType, String maxValue,String minValue, String isBoolean){
		con.newDevicesTableRelation(id, name, model, location, dataType, maxValue, minValue, isBoolean);
	}
	

}
