package edu.casetools.mreasoner.gui.architecture.implementation.events.data;

public class VeraData {
	private int id;
	private VeraVariable variable;
	private VeraEvent event;
	
	public VeraData(){
		id = 0;
		variable = new VeraVariable();
		event = new VeraEvent();
	}
	
	
	public VeraVariable getVariable(){
		return variable;
	}
	
	public VeraEvent getEvent() {
		return event;
	}


	public void setEvent(VeraEvent event) {
		this.event = event;
	}


	public void setVariable(VeraVariable variable) {
		this.variable = variable;			
		
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	
	
}
