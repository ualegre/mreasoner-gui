package edu.casetools.mreasoner.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.core.configs.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.database.core.connection.DBConnection.STATUS;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.controller.Controller;
import edu.casetools.mreasoner.gui.view.panels.models.Models.EventsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.InternalEventsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.ResultsTableModel;
import edu.casetools.mreasoner.gui.view.panels.models.Models.SensorsTableModel;


public class ConfigsListener implements ActionListener{

	Controller controller;
	boolean selected;
	public ConfigsListener(Controller controller){
		this.controller = controller;
		selected = false;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Refresh")) refresh();
		else if(arg0.getActionCommand().equals("Test"))testEnvironment_Iteration();
		else if(arg0.getActionCommand().equals("Create"))createDatabase();
		else if(arg0.getActionCommand().equals("Drop"))dropDatabase();
		else if(arg0.getActionCommand().equals("Check"))checkDatabase();
		else if(arg0.getActionCommand().equals("Check Tables"))checkTables();
		else if(arg0.getActionCommand().equals("Select All"))selectAll();
		else if(arg0.getActionCommand().equals("Clear Selected"))clearSelected();
		else {
			int selection = controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getGeneralConfigsPanel().getExecutionModeComboBox().getSelectedIndex();
            switch (selection){
	            case 0: 
	            	testEnvironment_Iteration();
	            	
	            	break;
	            case 1:  
	            	testEnvironment_RealTime(); 
	            	break;
	            case 2:  
	            	realEnvironment(); 
            	break;
            }
		}
		
	}
	
	private void testEnvironment_RealTime() {
		environment(false);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().configureExecutionMode(EXECUTION_MODE.SIMULATION_REAL_TIME);
		
	}

	private void clearSelected() {
		DatabaseOperations con = controller.getModel().getDBConnection();
		if(con.getDBConnection().isConnected()){
			clearEventsTable(con);
			clearInternalEventsTable(con);
			clearResultsTable(con);
			
			clearSensorsTable(con);
			clearActuatorsTable(con);
			clearSensorsImplementationTable(con);
			clearActuatorsImplementationTable(con);
			refresh();
		}
		
	}
	
	private void clearSensorsImplementationTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getSensorsImplementationCb().isSelected();
		if(clearTable){
			con.eraseSensorImplementationTable();
		}
		
	}

	private void clearActuatorsImplementationTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getActuatorsImplementationCb().isSelected();
		if(clearTable){
			con.eraseActuatorImplementationTable();
		}
		
	}

	private void clearActuatorsTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getActuatorsCb().isSelected();
		if(clearTable){
			con.eraseActuatorTable();
		}
		
	}

	private void clearSensorsTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getSensorsCb().isSelected();
		if(clearTable){
			con.eraseSensorTable();
		}
	}

	private void clearResultsTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getResultsCb().isSelected();
		if(clearTable){
			con.eraseResultsTable();
		}
		
	}

	private void clearInternalEventsTable(DatabaseOperations con) {
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getInternalEventsCb().isSelected();
		if(clearTable){
			con.eraseInternalEventsTable();
		}
	}

	private void clearEventsTable(DatabaseOperations con){
		boolean clearTable = controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().getEventsCb().isSelected();
		if(clearTable){
			con.eraseEventsTable();
		}
	}

	private void checkTables() {
		// TODO Auto-generated method stub
		
	}

	private void selectAll() {
		selected = !selected;
		controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getTableCreationPanel().selectAll(selected);
		
	}

	private boolean checkDatabase() {
		MConfigurations configs = controller.getView().getMainWindow().getMainPanel()
		.getDatabaseConfigsTabPanel().getDatabaseCreationPanel().getDBConfigs(new MConfigurations());
		boolean exists = controller.getModel().getDBConnection().databaseExists(configs.getDBConfigs().getDbName());	
		controller.getView().getMainWindow().getMainPanel()
				.getDatabaseConfigsTabPanel().getDatabaseCreationPanel().setStatus(exists); 

		return exists;
		
	}

	private void dropDatabase() {
		DatabaseOperations con = controller.getModel().getDBConnection();
		MConfigurations configs;
		if(this.checkDatabase()){
			if(con.checkConnection() == STATUS.CONNECTED){
				con.disconnect();
			}
			configs = getConfigs();
			con.dropDatabase(configs.getDBConfigs().getDbName());
			checkDatabase();
		}
		refresh();
	}

	private void createDatabase() {
		DatabaseOperations con = controller.getModel().getDBConnection();
		MConfigurations configs;
		if(con.checkConnection() == STATUS.CONNECTED){
			con.disconnect();
		}
			configs = getConfigs();
			con.createDatabase(configs.getDBConfigs().getDbName());
		this.createTables(con);
		refresh();	
		controller.getView().getMainWindow().getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		
	}
	
	private void createTables(DatabaseOperations con){
		con.connect();
		con.createEventsTable();
		con.createInternalEventsTable();
	//	con.createResultsTable(new Vector<String>());
		con.createSensorTable();
		con.createActuatorTable();
		con.createSensorImplementationTable();
		con.createActuatorImplementationTable();
		con.disconnect();
	}

	private void realEnvironment() {
		environment(true);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().configureExecutionMode(EXECUTION_MODE.REAL_ENVIRONMENT);
	}
	
	private void environment(boolean enable){
//		 controller.getView().getMainWindow().getMainPanel().enableSensorMappingTab(enable);
//		 controller.getView().getMainWindow().getMainPanel().enableActuatorMgrTab(enable);
		 controller.getView().getMainWindow().getMainPanel().enableEventReaderTab(enable);
		// controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().enableExecutionTimeTf(!enable);
		 controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getGeneralConfigsPanel().getStratificationPanel().enableRadioButtons(!enable);
	}
	

	private void testEnvironment_Iteration() {
		environment(false);
		controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getTimeConfigsPanel().configureExecutionMode(EXECUTION_MODE.SIMULATION_ITERATION);
	}

//	private void deleteSelectedRelation() {
//		DatabaseOperations con= controller.getModel().getDBConnection();
//		if(con != null ){
//			int row = controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getTable().getSelectedRow();
//			System.out.println(row);
//			if(row >= 0){
//				String device = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getTable().getValueAt(row, 0);
//				String implementation = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getTable().getValueAt(row, 1);
//				String state  = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getTable().getValueAt(row, 2);
//				controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getSensorsTableModel().removeSensorRelation(con, device, implementation, state);
//			}
//		}
//		SensorsTableModel tm = new SensorsTableModel();
//		tm.updateTable(con);
//		controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().setModel(tm);
//	
//	}

//	private void addRelation() {
//		//if(controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getImplementationComboBox().getItemCount() > 0){
//			DatabaseOperations con= controller.getModel().getDBConnection();
//			String device = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getDeviceTextField().getText();
//			String implementation = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getSelectedImplementation();
//			String state  = (String) controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().getStateTextField().getText();
//			controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getSensorsTableModel().addSensorRelation(con, device, implementation,state);
//			controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().resetTextFields();
//			refresh();
//		//}
//		controller.getView().getMainWindow().getMainPanel().getSensorMappingPanel().getMappingPanel().resetTextFields();
//	}

	public void refresh() {
		STATUS status;  // TODO Auto-generated method stub
		MConfigurations configs = getConfigs();
			DatabaseOperations con = controller.getModel().getDBConnection();
		    
			if((con.getDBConnection() != null)&& con.getDBConnection().isConnected() ){
				con.disconnect();
			}
			controller.getModel().createDatabaseConnection(configs);
			con = controller.getModel().getDBConnection();
			con.reconnect(configs.getDBConfigs());
			status = con.checkConnection();
			if(status == STATUS.CONNECTED){
				con.createSensorTable();
				updateTableModels(con);
			}else{
				System.out.println("ERROR: There was an error connecting to the database: Status connection "+status);
				System.out.println("       Please check if the data introduced in the \"Database Configurations\" field \n from the \"Database\" tab is correct.\n");
			}
			checkDatabase();
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

	private MConfigurations getConfigs(){
			MConfigurations configs = 
					controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getConfigs();
			configs = 
					controller.getView().getMainWindow().getMainPanel().getDatabaseConfigsTabPanel().getDBConfigs(configs);	
			configs = 
					controller.getView().getMainWindow().getMainPanel().getEventReaderConfigsPanel().getJarConfigs(configs);
			configs.setSystemSpecificationFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getSystemDeclarationFilePath());
			configs.setSessionFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getConfigsPath());
			//System.out.println("CONFIGS PATH "+configs.getConfigsFilePath()+" - "+controller.getView().getMainWindow().getMainPanel().getMainMenu().getConfigsPath());
			configs.setResultsFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getResultsPath());
			configs.setLFPUBSOutputFilePath(controller.getView().getMainWindow().getMainPanel().getConfigsPanel().getFilePathsPanel().getLFPUBSPath());
			return configs;
	}


}
