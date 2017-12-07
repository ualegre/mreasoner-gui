package edu.casetools.mreasoner.gui.controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.controller.Controller;
import edu.casetools.mreasoner.gui.view.panels.models.Models.EventsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.InternalEventsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.ResultsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.SensorsTableModel;

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
		switch(selected){
		case 0:
			String defaultSystemSpec = "System Specification File Editor - ";
			String fileName = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath();
			if(fileName != null){
				controller.getView().getMainWindow().setTitle(defaultSystemSpec+fileName);
			}else{
				controller.getView().getMainWindow().setTitle(defaultSystemSpec+"New File");
			}

			break;
		case 1:
			controller.getView().getMainWindow().setTitle("Database Results");
			refresh();
			break;
		case 2:
			controller.getView().getMainWindow().setTitle("Sensor Mapping");
			break;
		case 3:
			controller.getView().getMainWindow().setTitle("Actuator Mapping");
			break;
		case 4:
			String defaultLFPUBS = "LFPUBS Rule Translations - ";
			String lfpubsFile = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath();
			if(lfpubsFile != null){
				controller.getView().getMainWindow().setTitle(defaultLFPUBS+lfpubsFile);
			}else{
				controller.getView().getMainWindow().setTitle(defaultLFPUBS+"New File");
			}
			break;
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
		MConfigurations configs = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs();
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
