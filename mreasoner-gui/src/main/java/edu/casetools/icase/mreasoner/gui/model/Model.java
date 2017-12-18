package edu.casetools.icase.mreasoner.gui.model;

import java.util.Vector;

import edu.casetools.icase.mreasoner.configs.MConfigsLoader;
import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.configs.data.db.MDBConfigs;
import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperationsFactory;
import edu.casetools.icase.mreasoner.deployment.Launcher;
import edu.casetools.icase.mreasoner.gui.model.io.IOManager;
import edu.casetools.icase.mreasoner.learning.LFPUBSTranslatorWrapper;
import edu.casetools.icase.mreasoner.vera.actuators.device.Actuator;
import edu.casetools.icase.mreasoner.verification.NuSMVExportManager;

public class Model {

	private IOManager   			 testerModel;
	private Launcher 				 reasonerModel;
    private LFPUBSTranslatorWrapper  lfpubsTranslModel;
	private NuSMVExportManager 		 exporterModel;
	private DatabaseOperations  	 dbOperations;
	private MConfigsLoader     		 configsReader;
	private Vector<Actuator> 		 actuators;

	
	public Model(MDBConfigs configs, Vector<Actuator> actuators){
		this.actuators 	  = actuators;
		testerModel 	  = new IOManager();
		exporterModel 	  = new NuSMVExportManager();
		lfpubsTranslModel = new LFPUBSTranslatorWrapper();
		configsReader 	  = new MConfigsLoader();
		if(configs != null){
			dbOperations  = DatabaseOperationsFactory.getDatabaseOperations( configs );
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
	
	public void createDatabaseConnection(MConfigs configs){
		dbOperations = DatabaseOperationsFactory.getDatabaseOperations(configs.getDBConfigs());
	}
	
	public MConfigsLoader getConfigsReader(){
		return this.configsReader;
	}
	
	public NuSMVExportManager getExporterModel(){
		return this.exporterModel;
	}

	public void startReasoner(String configFileName) {
		reasonerModel = new Launcher(actuators);
		reasonerModel.readMSpecification(reasonerModel.readMConfigs(configFileName));
		reasonerModel.start();
	}
	
	public void stopReasoner() {
		try {
			reasonerModel.terminate();
			if(!reasonerModel.isInterrupted())
				reasonerModel.interrupt();
			reasonerModel.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MConfigsLoader getSSHConfigsReader() {
		// TODO Auto-generated method stub
		return null;
	}

}
