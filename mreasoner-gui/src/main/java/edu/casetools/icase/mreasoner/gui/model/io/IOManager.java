package edu.casetools.icase.mreasoner.gui.model.io;

import java.io.IOException;




public class IOManager {

	FileReaderModel fileReader;
	FileWriterModel fileWriter;
	
	public IOManager(){
		fileReader = new FileReaderModel();
		fileWriter = new FileWriterModel();
	}
	
	public String read(String file) throws IOException{

			return fileReader.read(file);
	}
	
	public void writeFile(String fileName,String text){
		 fileWriter.write(fileName,text);
	}
	
}
