package edu.casetools.icase.mreasoner.gui.controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.casetools.icase.mreasoner.core.elements.time.conf.TimeConfigs;
import edu.casetools.icase.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.gui.controller.Controller;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.EventsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.InternalEventsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.ResultsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.SensorsTableModel;

public class TabbedPaneListener implements MouseListener{

	Controller controller;
	
	public TabbedPaneListener(Controller controller){
		this.controller = controller;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			int selected = controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().getSelectedIndex();
			switch(selected){
			case 0:
				controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().centerSplitPaneDivider();
				break;
			case 4:
				controller.getView().getMainWindow().getMainPanel().getTranslataionsPanel().centerSplitPaneDivider();
				break;
			}
		
		}
	
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int selected = controller.getView().getMainWindow().getMainPanel().getRightTabbedPane().getSelectedIndex();
		String defaultTitle = "";
		String filePath = "";
		switch(selected){
		case 0:
			defaultTitle = "M Specification File Editor - ";
			filePath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath();
			displayTitle(defaultTitle, filePath);
			break;
		case 1:
			defaultTitle = "Database Results - ";
			filePath = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getName();
			displayTitle(defaultTitle, filePath);
			refresh();
			break;
		case 2:
			defaultTitle = "Vera Manager - ";
			filePath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSSHConfigsPath();
			displayTitle(defaultTitle, filePath);
			break;
		case 3:
			defaultTitle = "LFPUBS Rule Translations - ";
			filePath = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath();
			displayTitle(defaultTitle, filePath);
			break;
		}
		
		
	}

	private void displayTitle(String defaultTitle, String filePath) {
		if(filePath != null){
			controller.getView().getMainWindow().setTitle(defaultTitle+filePath);
		}else{
			controller.getView().getMainWindow().setTitle(defaultTitle+"New File");
		}
	}
	private void refresh() {
		STATUS status;// TODO Auto-generated method stub
//		Configs configs = 
//				controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs();
		DatabaseOperations con = controller.getModel().getDBConnection();

			con = controller.getModel().getDBConnection();
			status = con.checkConnection();
			//con.createDatabase();
			if(status == STATUS.CONNECTED)
				updateTableModels(con);
	//	}else status = 0;
		controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getDatabaseConfigsPanel().connectionStatus(status);
		
	}
	
	private void updateTableModels(DatabaseOperations con){
		TimeConfigs configs = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs().getTimeConfigs();
		SensorsTableModel stm = new SensorsTableModel();
		stm.updateTable(con);
		ResultsTableModel rtm = new ResultsTableModel();
		rtm.updateTable(con);
		EventsTableModel  etm = new EventsTableModel(configs);
		etm.updateTable(con);
		InternalEventsTableModel ietm = new InternalEventsTableModel(configs);
		ietm.updateTable(con);

		controller.getView().getMainWindow().getMainPanel().getDatabasePanel().getResultsTable().setModel(rtm);
		controller.getView().getMainWindow().getMainPanel().getDatabasePanel().getEventsTable().setModel(etm);
		controller.getView().getMainWindow().getMainPanel().getDatabasePanel().getInternalEventsTable().setModel(ietm);
//		controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getTable().setModel(stm);
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
