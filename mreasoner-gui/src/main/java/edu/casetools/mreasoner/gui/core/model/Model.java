package edu.casetools.mreasoner.gui.core.model;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.mreasoner.gui.core.model.files.ExportManager;
import edu.casetools.mreasoner.gui.core.model.files.SystemConfigsReader;
import edu.casetools.mreasoner.gui.core.model.files.TesterModel;
import edu.casetools.mreasoner.gui.core.model.reasoner.ArchitectureManager;
import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MDBTypes;
import edu.casetools.mreasoner.configurations.data.MDBTypes.DB_IMPLEMENTATION;

//import Translator.Translator;


public class Model {

	private TesterModel   		testerModel;
	private ArchitectureManager 		reasonerModel;
	//private Translator			LFPUBSTranslator;
	private ExportManager exporterModel;
	private DatabaseOperations  dbOperations;
	private SystemConfigsReader       configsReader;

	
	public Model(MConfigurations configs){
		testerModel = new TesterModel();
		reasonerModel = new ArchitectureManager();
	//	LFPUBSTranslator = new Translator(false);
		exporterModel = new ExportManager();
		
		configsReader = new SystemConfigsReader();
		if(configs != null){
			dbOperations = DatabaseOperationsFactory.getDatabaseOperations(
					DB_IMPLEMENTATION.POSTGRESQL,configs.getDBConfigs());
		}
		

	}

	public TesterModel getTesterModel() {
		return testerModel;
	}
	
	public ArchitectureManager getReasonerModel(){
		return reasonerModel;
	}
	
	public DatabaseOperations getDBConnection(){
		return dbOperations;
	}
	
//	public Translator getLFPUBSTRanslator(){
//		return this.LFPUBSTranslator;
//	}
	
	public void createDatabaseConnection(MConfigurations configs){
		MDBTypes.DB_IMPLEMENTATION implementation = MDBTypes.DB_IMPLEMENTATION.valueOf(configs.getDBConfigs().getDbType().toUpperCase());
		dbOperations = DatabaseOperationsFactory.getDatabaseOperations(implementation,configs.getDBConfigs());
	}
	
	public SystemConfigsReader getConfigsReader(){
		return this.configsReader;
	}
	
	public ExportManager getExporterModel(){
		return this.exporterModel;
	}
	
}
