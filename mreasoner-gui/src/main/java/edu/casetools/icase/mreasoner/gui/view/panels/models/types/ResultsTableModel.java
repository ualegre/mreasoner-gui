package edu.casetools.icase.mreasoner.gui.view.panels.models.types;

import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.view.panels.models.DefaultModel;




public class ResultsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getResultsTableContent());
		
	}
	
}
