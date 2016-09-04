package edu.casetools.mreasoner.gui.Model.Files;

import java.io.IOException;




public class TesterModel {

	FileReaderModel fileReader;
	FileWriterModel fileWriter;
	
	public TesterModel(){
		fileReader = new FileReaderModel();
		fileWriter = new FileWriterModel();
	}
	
	public String read(String file) throws IOException{

			return fileReader.read(file);
	}
	
//	public Configs readConfigs(String file){
//		return fileReader.readConfigs(file);
//	}
	
	public void writeFile(String fileName,String text){
		 fileWriter.write(fileName,text);
	}
	
}
