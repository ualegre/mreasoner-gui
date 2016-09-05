package edu.casetools.mreasoner.gui.core.view.panels.models.Models;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.core.view.panels.models.DefaultModel;
import edu.casetools.mreasoner.configurations.data.MConfigurations;


public class InternalEventsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	private boolean simulateEvents;
	
	public InternalEventsTableModel(MConfigurations configs){
		simulateEvents = configs.getTimeIsGivenInIterations();
	}
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getInternalEventsTableContent(simulateEvents));		
	}
}
