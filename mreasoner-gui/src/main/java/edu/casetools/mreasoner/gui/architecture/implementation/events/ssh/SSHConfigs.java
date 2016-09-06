package edu.casetools.mreasoner.gui.architecture.implementation.events.ssh;

public class SSHConfigs {

	private String  hostname,username,password;
	private boolean silence;
	
	public SSHConfigs(){
		
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isSilence(){
		return silence;
	}

	public void setSilence(boolean silence) {
		this.silence = silence;
		
	}
	
}
