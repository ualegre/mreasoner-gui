package edu.casetools.mreasoner.gui.architecture.implementation.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LibraryThread extends Thread{

	InputStream in;
	Console console;
	String command;
	
	public LibraryThread(String command){
		this.command = command;
		console = new Console(command);
		 Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			in = p.getInputStream();
			//this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
    public void run() {
	      BufferedReader reader = null;
	      try {
	        reader = new BufferedReader(new InputStreamReader(in));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	        //  System.out.println(line);
	          console.addText(line);
	        }
	      } catch (Exception e) {
	        // TODO
	      } finally {
	        if (reader != null) {
	          try {
	            reader.close();
	          } catch (IOException e) {
	            // ignore
	          }
	        }
	      }
	      
	      console.addText("FINISHED THE THREAD WITH THE COMMAND "+command);
	    }
	
}
