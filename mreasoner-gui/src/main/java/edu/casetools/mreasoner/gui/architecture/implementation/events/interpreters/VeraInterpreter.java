package edu.casetools.mreasoner.gui.architecture.implementation.events.interpreters;

import edu.casetools.mreasoner.gui.architecture.implementation.events.data.VeraData;

public class VeraInterpreter {

	EventInterpreter eventInterpreter;
	VariableInterpreter variableInterpreter;
	
	public VeraInterpreter(){
		eventInterpreter	 = new EventInterpreter();
		variableInterpreter  = new VariableInterpreter();
	}
	
	public VeraData interpret(String line){
		VeraData data = new VeraData();
		int id = interpretId(line);
		data.setId(id);
		switch(id){ 
			case 5:
				
				break;
			case 6:
					data.setVariable(variableInterpreter.translateVariable(line));
				break;
			case 7:
					data.setEvent(eventInterpreter.translateEvent(line));
				break;
			default:
				break;
		}
		return data;
		
	}
	
	public int interpretId(String line){
		String[] id;

		if(line.startsWith("0")){
			id = line.split("	");
			if(id.length > 0){
				return Integer.parseInt(id[0]);
			}
		}
		return -1;
	}
	
}
