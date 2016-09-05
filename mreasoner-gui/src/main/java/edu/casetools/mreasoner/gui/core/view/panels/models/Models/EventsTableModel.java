package edu.casetools.mreasoner.gui.core.view.panels.models.Models;



import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.core.view.panels.models.DefaultModel;
import edu.casetools.mreasoner.configurations.data.MConfigurations;


public class EventsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	private boolean simulateEvents;
	
	public EventsTableModel(MConfigurations configs){
		simulateEvents = configs.getTimeIsGivenInIterations();
	}
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getEventsTableContent(simulateEvents));// TODO Auto-generated method stub
		
	}

}
