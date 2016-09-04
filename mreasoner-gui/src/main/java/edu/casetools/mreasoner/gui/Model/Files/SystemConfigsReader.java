package edu.casetools.mreasoner.gui.Model.Files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.casetools.mreasoner.input.configurations.MConfigurations;




public class SystemConfigsReader {
	public enum GRAMMAR_STATUS{CORRECT,FILE_ERROR,GRAMMAR_ERROR};
	private ConfigsReader checker;
	
	public SystemConfigsReader(){

	}
	
	
	public MConfigurations readConfigs(String file) {
       try {
    	checker = new ConfigsReader(new FileInputStream(file));   
		MConfigurations configs = checker.readConfigs();
		return configs;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		return null;
//	} catch (ParseException e) {
//		System.out.println("The following errors were found: " + e.getMessage());
//		return GRAMMAR_STATUS.GRAMMAR_ERROR;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

	}
	

}
