package edu.casetools.mreasoner.gui.model.io.mconfigs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.io.compiler.configs.ConfigsReader;
import edu.casetools.mreasoner.io.compiler.configs.ParseException;




public class ConfigsReaderWrapper {

	private ConfigsReader checker;
		
	public MConfigurations readConfigs(String file) {
		
	    try {
	    	checker = new ConfigsReader(new FileInputStream(file));   
			MConfigurations configs = checker.readConfigs();
			return configs;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
