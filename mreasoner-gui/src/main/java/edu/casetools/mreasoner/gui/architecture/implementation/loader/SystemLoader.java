package edu.casetools.mreasoner.gui.architecture.implementation.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.reader.ConfigsReader;
import edu.casetools.mreasoner.configurations.reader.ParseException;
import edu.casetools.mreasoner.core.compiler.realtime.MCompiler;
import edu.casetools.mreasoner.core.elements.MInputData;
import edu.casetools.mreasoner.gui.architecture.implementation.elements.SystemData;






public class SystemLoader {

	
	
	public SystemLoader(){
		
	}
	
	public MConfigurations readConfigs(String configsFileName) throws FileNotFoundException, ParseException{
	
			ConfigsReader reader = new ConfigsReader(new FileReader(configsFileName));
			return reader.readConfigs();

	}
	
	public MInputData readSystemInputData(String configsFileName) throws FileNotFoundException, edu.casetools.mreasoner.core.compiler.realtime.ParseException{
		MCompiler reader = new MCompiler(new FileReader(configsFileName));
		return reader.readSystemSpecifications();
	}
	
	public SystemData getSystemLoadData(String configsFileName) throws FileNotFoundException, ParseException, edu.casetools.mreasoner.core.compiler.realtime.ParseException{
		SystemData auxiliarTestCase = new SystemData();
		MConfigurations configs = readConfigs(configsFileName);
		MInputData systemSpecification = readSystemInputData( configs.getSystemSpecificationFilePath());
		auxiliarTestCase.setSystemConfigs( configs );
		auxiliarTestCase.setSystemInput  ( systemSpecification  );
		

		return auxiliarTestCase;
	}
	

	

}
