package edu.casetools.mreasoner.gui.view.panels.models.Models;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.view.panels.models.DefaultModel;




public class ResultsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getResultsTableContent());
		
	}
	
}
