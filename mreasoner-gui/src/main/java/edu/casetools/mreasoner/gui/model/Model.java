package edu.casetools.mreasoner.gui.model;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.database.core.MDBImplementations;
import edu.casetools.mreasoner.database.core.MDBImplementations.DB_IMPLEMENTATION;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.mreasoner.gui.model.io.IOManager;
import edu.casetools.mreasoner.gui.model.io.lfpubs.LFPUBSTranslatorWrapper;
import edu.casetools.mreasoner.gui.model.io.mconfigs.ConfigsReaderWrapper;
import edu.casetools.mreasoner.gui.model.io.nusmv.ExportManager;
import edu.casetools.mreasoner.utils.Launcher;


//import Translator.Translator;


public class Model {

	private IOManager   			 testerModel;
	private Launcher 				 reasonerModel;
    private LFPUBSTranslatorWrapper  lfpubsTranslModel;
	private ExportManager 			 exporterModel;
	private DatabaseOperations  	 dbOperations;
	private ConfigsReaderWrapper     configsReader;

	
	public Model(MConfigurations configs){
		testerModel 	  = new IOManager();
		exporterModel 	  = new ExportManager();
		lfpubsTranslModel = new LFPUBSTranslatorWrapper();
		configsReader 	  = new ConfigsReaderWrapper();
		if(configs != null){
			dbOperations  = DatabaseOperationsFactory.getDatabaseOperations(
					DB_IMPLEMENTATION.POSTGRESQL,configs.getDBConfigs());
		}
		
	}

	public IOManager getTesterModel() {
		return testerModel;
	}
	
	public Launcher getReasonerModel(){
		return reasonerModel;
	}
	
	public DatabaseOperations getDBConnection(){
		return dbOperations;
	}
	
	public LFPUBSTranslatorWrapper getLFPUBSTranslator(){		
		return this.lfpubsTranslModel;
	}
	
	public void createDatabaseConnection(MConfigurations configs){
		MDBImplementations.DB_IMPLEMENTATION implementation = MDBImplementations.DB_IMPLEMENTATION.valueOf(configs.getDBConfigs().getDbType().toUpperCase());
		dbOperations = DatabaseOperationsFactory.getDatabaseOperations(implementation,configs.getDBConfigs());
	}
	
	public ConfigsReaderWrapper getConfigsReader(){
		return this.configsReader;
	}
	
	public ExportManager getExporterModel(){
		return this.exporterModel;
	}

	public void startReasoner(String configFileName) {
		reasonerModel = new Launcher();
		reasonerModel.readMSpecification(configFileName);
		reasonerModel.start();
	}
	
	public void stopReasoner() {
		try {
			reasonerModel.terminate();
			reasonerModel.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
