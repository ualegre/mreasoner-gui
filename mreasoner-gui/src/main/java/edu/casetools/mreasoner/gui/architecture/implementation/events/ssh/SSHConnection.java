package edu.casetools.mreasoner.gui.architecture.implementation.events.ssh;

/*
 *  SSHTools - Java SSH2 API
 *
 *  Copyright (C) 2002-2003 Lee David Painter and Contributors.
 *
 *  Contributions made by:
 *
 *  Brett Smith
 *  Richard Pernavas
 *  Erwin Bolwidt
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;

import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.gui.architecture.implementation.events.data.VeraData;
import edu.casetools.mreasoner.gui.architecture.implementation.events.data.VeraEvent;
import edu.casetools.mreasoner.gui.architecture.implementation.events.data.VeraVariable;
import edu.casetools.mreasoner.gui.architecture.implementation.events.interpreters.VeraInterpreter;





public class SSHConnection extends Thread{
	SSHConfigs sshConfigs;
	SshClient ssh;
	SessionChannelClient session;
	DatabaseOperations databaseOperations;
	boolean running;
	boolean initFinished;
	boolean finalFinished;
	BufferedReader br;
	int connectionStatus;
	
	public SSHConnection(DatabaseOperations databaseOperations, SSHConfigs sshConfigs){
		initFinished = false;
		finalFinished = false;
		this.sshConfigs = sshConfigs;
		this.databaseOperations = databaseOperations;
		running = true;
		connectionStatus = 0;
	}
	
  public int initialize() throws IOException{
	      // Setup a logfile
	    //  ConfigurationLoader.initialize(false);
	  	  databaseOperations.eraseEventsTable();
		  databaseOperations.createEventsTable();
	      ssh = new SshClient();

	      ssh.connect(sshConfigs.getHostname());
	      
	      PasswordAuthenticationClient pwAuth = new PasswordAuthenticationClient();
	      //System.out.println(sshConfigs.getUsername()+" pw; "+sshConfigs.getPassword()+" "+sshConfigs.getHostname());
	      pwAuth.setUsername(sshConfigs.getUsername());
	      pwAuth.setPassword(sshConfigs.getPassword());

	      int result = ssh.authenticate(pwAuth);

	      
	      if ( result == AuthenticationProtocolState.COMPLETE) {
//	        // The connection is authenticated we can now do some real work!
	    	 System.out.println("\n***********CORRECT AUTHENTICATION*************");
	        session = ssh.openSessionChannel();
	        if(!session.requestPseudoTerminal("vt100", 80, 24, 0, 0, ""))
	          System.out.println("Failed to allocate a pseudo terminal");
	        if(session.startShell()) {
	        	System.out.println("\n**********SHELL STARTED*********************");
	          OutputStream out = session.getOutputStream();

	         String cmd = "tail -f  /var/log/cmh/LuaUPnP.log\n";
	 
	         out.write(cmd.getBytes()); 
	         
	         br = new BufferedReader(new InputStreamReader(session.getInputStream(),"US-ASCII"));
	       } else{
	            System.out.println("Failed to start the users shell");
	         	result = 0;
	       }
	       if (result == AuthenticationProtocolState.PARTIAL) {
		             System.out.println("Further authentication requried!");
		        	}
		        if (result == AuthenticationProtocolState.FAILED) {
		             System.out.println("Authentication failed!");
		        }
	       
  		}else {
  			System.out.println("AUTHENTICATION ERROR FOR:");
  			System.out.println("HOSTNAME: "+sshConfigs.getHostname());
  			System.out.println("USER: "+sshConfigs.getUsername());
  			System.out.println("PASSWORD: "+sshConfigs.getPassword());
  		}
	        return result;
  }
	
  public void run() {
    try {
	  	connectionStatus = initialize();

    	if( connectionStatus == AuthenticationProtocolState.COMPLETE){ 
    		main();
    		Thread.sleep(100);
    	}
    	initFinished = true;
	     if ( connectionStatus == AuthenticationProtocolState.COMPLETE) {
	         while (running)
	         {
	        	 	main();
	
	         }
	      }
	         session.close();
	      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
	public void interrupt(){
		try {
			System.out.println("\n\n Disconnecting...");
			ssh.disconnect();
			session.close();
			Thread.sleep(50);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.interrupt();
		finalFinished = true;
	}
  
  private void main(){
  	VeraData data = null;
  	VeraInterpreter interpreter = new VeraInterpreter();
  	
      String line;
	try {
			line = br.readLine();
       
	      if (line != null){
	     	 data = interpreter.interpret(line);
	     	 if(!sshConfigs.isSilence())System.out.println(line);
	      }
	      if(data != null){
	    	  switch(data.getId()){
	    	  case 6:
	    		  VeraVariable variable = data.getVariable();
	 	    	 if(!variable.isEmpty()){	
	 	    		 if(variable.getVariable().equals(variable.VAR_TRIPPED)){
	 	    			 
	 		     	 String state = databaseOperations.getState(variable.getDeviceId());

	 		     	 if(state != null){
		 	    		 System.out.println("******************Change on State: "+state+"**********************************");

	 			     	 databaseOperations.insertEvent(state, ""+variable.getNewValue(),"-1", variable.getDate(), variable.getTime());
	 			     	 System.out.println("\n VERA LOG READER: Variable Detection: "+state+ " - "
	 			     	 +variable.getNewValue()+" - "+ variable.getDate()+" "+ variable.getTime());
	 			     	 }else System.out.println("WARNING! Device id:"+variable.getDeviceId()+
	 			     			 " not recogniced in database table "+
	 			     			 databaseOperations.getDBConnection().getDBConfigs().getTable()+
	 			     			 "_events");
	 		     	      System.out.println("***************************************************************************");

	 	    		 }
	 	    	 }
	    		  break;
	    	  case 7:
	    		  VeraEvent event = data.getEvent();
	 	    	 if(event != null){	 
	 		     	 String state = databaseOperations.getState(event.getDevice());
	 		     	 if(state != null){
	 			     	 databaseOperations.insertEvent(state, ""+event.getStatus(),"-1", event.getDate(), event.getTime());
	 			     	 System.out.println("\n VERA LOG READER: Variable Detection: "+state+ " - "
	 			     	 +event.getStatus()+" - "+ event.getDate()+" "+ event.getTime());
	 			     	 }else System.out.println("WARNING! Device id:"+event.getDevice()+
	 			     			 " not recogniced in database table "+
	 			     			 databaseOperations.getDBConnection().getDBConfigs().getTable()+
	 			     			 "_events");
	    		  break;
	    	  }
	    	 
	    	 }
	      }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }
  
  public boolean isInitializationFinished(){
	  return initFinished;
  }
  
  public boolean isFinalizationFinished(){
	  return finalFinished;
  }
  
  public void terminate(){
	  running = false;
  }

	public boolean checkConnection() {
		if( connectionStatus == AuthenticationProtocolState.COMPLETE)
		return true;
		else return false;
	}
}