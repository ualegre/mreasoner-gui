package edu.casetools.mreasoner.gui.model.io.lfpubs;

import edu.casetools.lfpubs2m.LFPUBS2MTranslator;

public class LFPUBSTranslatorWrapper {

	LFPUBS2MTranslator translator;
	
	public LFPUBSTranslatorWrapper(){
		new LFPUBS2MTranslator(true);
	}
	
	public String translate(String filename, boolean debug){
		return translator.getTranslation(filename);
	}

	
}
