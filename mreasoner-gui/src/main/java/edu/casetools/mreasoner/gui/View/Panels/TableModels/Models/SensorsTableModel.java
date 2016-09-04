package edu.casetools.mreasoner.gui.View.Panels.TableModels.Models;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.View.Panels.TableModels.DefaultModel;

public class SensorsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;

	
	public SensorsTableModel(){
	
	}
	
	public void updateTable(DatabaseOperations con){

		super.defaultUpdateTable(con.getSensorTableContent());
		      
	}
	
	public void removeSensorRelation(DatabaseOperations con,String device,String implementation, String state){
		
		//con.connect();	
		con.removeSensorTableRelation(device,implementation, state);

	}
	
//	public void update(){
//		if (this.getRowCount() > 0) {
//		    for (int i = this.getRowCount() - 1; i > -1; i--) {
//		        this.removeRow(i);
//		    }
//		}
//	}
	
	public void addSensorRelation(DatabaseOperations con,String device,String implementation,String state){
		con.newSensorTableRelation(device, implementation, state);
	}
}
