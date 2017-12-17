package edu.casetools.icase.mreasoner.gui.view.panels.models.types;

import edu.casetools.icase.mreasoner.core.elements.time.conf.TimeConfigs;
import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.view.panels.models.DefaultModel;

public class InternalEventsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	private boolean simulateEvents;
	
	public InternalEventsTableModel(TimeConfigs configs){
		simulateEvents = configs.isSimulation();
	}
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getInternalEventsTableContent(simulateEvents));		
	}
}
