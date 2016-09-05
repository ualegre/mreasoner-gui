package edu.casetools.mreasoner.gui.architecture.design;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.configurations.reader.ConfigsReader;
import edu.casetools.mreasoner.configurations.reader.ParseException;
import edu.casetools.mreasoner.core.compiler.iterations.MCompiler_Iteration;
import edu.casetools.mreasoner.core.compiler.realtime.MCompiler;
import edu.casetools.mreasoner.core.elements.MInputData;

public class TestCaseLoader {


	public TestCaseLoader(){
	
	}
	
	public MConfigurations readConfigs(String configsFileName) throws ParseException,FileNotFoundException{	
		ConfigsReader reader = new ConfigsReader(new FileReader(configsFileName));
		return reader.readConfigs();
	}
	
	public MInputData readSystemInputData_RealTime(String configsFileName) throws FileNotFoundException, edu.casetools.mreasoner.core.compiler.realtime.ParseException{
		MCompiler reader = new MCompiler(new FileReader(configsFileName));
		return reader.readSystemSpecifications();
	}
	
	public MInputData readSystemInputData_Iteration(String configsFileName) throws FileNotFoundException, edu.casetools.mreasoner.core.compiler.iterations.ParseException{
		MCompiler_Iteration reader = new MCompiler_Iteration(new FileReader(configsFileName));
		return reader.readSystemSpecifications();
	}
	
	public Vector<TestCase> getTestCases(String configsFileName) throws FileNotFoundException, ParseException, edu.casetools.mreasoner.core.compiler.iterations.ParseException, edu.casetools.mreasoner.core.compiler.realtime.ParseException{
		Vector<TestCase> testCases = new Vector<TestCase>();
		TestCase auxiliarTestCase;
		Vector<MConfigurations> systemConfigs = new Vector<MConfigurations>();
		
		MConfigurations configs = readConfigs(configsFileName);
		systemConfigs.add(configs);
		
		for(int i=0;i<systemConfigs.size();i++){
			auxiliarTestCase = new TestCase();
			//input 			 = new FileInput();

			auxiliarTestCase.setSystemConfigs( systemConfigs.get(i) );
		//	System.out.println( configs.getSystemDeclarationFileName());
		//	System.out.println("ATENCION! LEO!"+ configs.getSystemDeclarationFilePath());
			if(configs.getExecutionMode().equals(EXECUTION_MODE.SIMULATION_ITERATION)){
				auxiliarTestCase.setSystemInput  ( readSystemInputData_Iteration( configs.getSystemSpecificationFilePath()) );
			}else if(configs.getExecutionMode().equals(EXECUTION_MODE.SIMULATION_REAL_TIME)){
				auxiliarTestCase.setSystemInput  ( readSystemInputData_RealTime( configs.getSystemSpecificationFilePath()) );
			}
			testCases.add(auxiliarTestCase);
		}

		return testCases;
	}
	
//	private int readNumberOfTestCases(){
//		return systemConfigsReader.readNumberOfTestCases();
//	}
	
//	private SystemConfigs getSystemConfigs(int i){
//		//SystemConfigs systemConfigs = new SystemConfigs();
//		
////		systemConfigs.setSimulation    			 (  Settings.IS_SIMULATION    [i]  );
////		systemConfigs.setTimeUnitMillis 		 (  Settings.TIME_UNIT_MILLIS [i]  );
////		systemConfigs.setTable					 (  Settings.TABLE_NAME       [i]  );
////		systemConfigs.setLoggerFilename			 (  Settings.OUTPUTS          [i]  );
////		systemConfigs.setExecutionTime			 (  Settings.MAX_UNIT_OF_TIME  [i] );
//
//	//	return ;
//	}
	
//	private InputFilenames getInputFilenames(int i){
//		InputFilenames inputFileNames = new InputFilenames();
//		
//		inputFileNames.setEventsFileName		   (   Settings.INPUT_EVENTS     [i] );
//		inputFileNames.setIndependentStatesFileName(   Settings.INPUT_SYSTEMS    [i] );
//		inputFileNames.setRulesFileName			   (   Settings.INPUT_SYSTEMS    [i] );
//		inputFileNames.setStatesFileName		   (   Settings.INPUT_SYSTEMS    [i] );
//		
//		return inputFileNames;
//	}
//	
	

}
