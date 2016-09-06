package edu.casetools.mreasoner.gui.architecture.implementation.events.data;

public class VeraVariable {
	public final String VAR_TRIPPED = "Tripped";
	private String date;
	private String time;
	private String deviceVariable;
	private String deviceId;
	private String service;
	private String variable;
	private String oldValue;
	private String newValue;
	
	public VeraVariable(){
		this.date = "";
		this.time = "";
		this.deviceVariable = "";
		this.deviceId = "";
		this.service = "";
		this.variable = "";
		this.oldValue = "";
		this.newValue = "";
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDeviceVariable() {
		return deviceVariable;
	}
	
	public void setDeviceVariable(String deviceVariable) {
		this.deviceVariable = deviceVariable;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public String getOldValue() {
		return oldValue;
	}
	
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	
	public String getNewValue() {
		return newValue;
	}
	
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public boolean isEmpty(){
		return 	(this.date.equals("") && this.time.equals("") && this.deviceVariable.equals("") 
				&& this.deviceId.equals("") && this.service.equals("") && this.variable.equals("") 
				&& this.oldValue.equals("") && this.newValue.equals(""));

	}
	
	
}
