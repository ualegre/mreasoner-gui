package edu.casetools.icase.mreasoner.gui.model.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterModel {


	FileWriter fileWriter = null;
    PrintWriter printWriter = null;

    
	public void open(String fileName){
        try {
        	
			fileWriter = new FileWriter(fileName);
			printWriter = new PrintWriter(fileWriter); 
		
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String fileName,String text){	
		this.open(fileName);
			printWriter.print(text);	
		this.close();
	}

	public void close(){
		try{  
		if (null != fileWriter)
			  fileWriter.close();
          } catch (Exception e) {
             e.printStackTrace();
          }
	}
	
}
