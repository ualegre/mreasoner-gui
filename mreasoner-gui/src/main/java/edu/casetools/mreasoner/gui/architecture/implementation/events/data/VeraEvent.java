package edu.casetools.mreasoner.gui.architecture.implementation.events.data;

public class VeraEvent {
	
	String device;
	boolean status;
	String time;
	String date;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(String state) {
		if(state!= null){
			if(state.equalsIgnoreCase("255")) this.status = true;
			else 							this.status = false;
		}else{
			System.out.println("WARNING INVALID EVENT FROM DEVICE "+device);
		}
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setStatus(boolean state) {
		this.status = state;
	}
	
	

}
