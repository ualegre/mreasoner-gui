package edu.casetools.mreasoner.gui.View.Panels.TableModels.Models;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.View.Panels.TableModels.DefaultModel;




public class ResultsTableModel extends DefaultModel{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void updateTable(DatabaseOperations con) {
		super.defaultUpdateTable(con.getResultsTableContent());
		
	}
	
}
