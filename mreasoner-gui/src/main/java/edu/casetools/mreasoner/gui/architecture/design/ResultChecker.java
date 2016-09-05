package edu.casetools.mreasoner.gui.architecture.design;


import java.util.Vector;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;





public class ResultChecker {
	
//	private 	  File 	     			 file,expectedFile;
//	private 	  FileReader 		     fileReader,expectedFileReader;
//	private 	  BufferedReader   		 bufferedReader,expectedBufferedReader;
//	private 	  FileWriter 		     fileWriter;
 //   private  	  PrintWriter 			 printWriter;

    public ResultChecker(){
    
    }
	 
    
	 public void checkResults(Vector<TestCase> testCases){

		 for(int i=0;i<testCases.size();i++){
			String inputFile = testCases.get(i).getSystemConfigs().getSystemSpecificationFilePath();

			 System.out.println("TEST "+i+" VEREDICT FOR "+inputFile+
					 ": VEREDICT STILL NOT IMPLEMENTED");	
// 			 open(inputFile,testCases.get(i).getSystemConfigs().get);	 
//			 if(read()){
//
//				 System.out.println("TEST "+i+" VEREDICT FOR "+inputFile+": CORRECT");
//				 printWriter.println("TEST "+i+" VEREDICT FOR "+inputFile+": CORRECT");
//			 }
//			 else {
//				 System.out.println("TEST "+i+" VEREDICT FOR "+inputFile+": INCORRECT");
//				 printWriter.println("TEST "+i+" VEREDICT FOR "+inputFile+": INCORRECT");
//			 }
//			 
//			 close();
		 }
		
	 }
	 
//	 private String treatLine(String line){
//		 line = line.replaceAll("\\s","");
//		 Pattern 	 p = Pattern.compile("\\((.*?)\\)"); 
//		 Matcher 	 m = p.matcher(line); 
//		 if( m.find() ){
//			line = line.replaceAll("\\("+m.group(0)+"\\)", "");
//		 }
//		 return line;
//	 }
//	 
//	 private long getLineTime(String line){
//		 long lineTime = -1;
//		 String auxiliar = null;
//		 Pattern 	 p = Pattern.compile("\\((.*?)\\)"); 
//		 Matcher 	 m = p.matcher(line); 
//		 if( m.find() ){
//			 
//			auxiliar = m.group(0);
//			auxiliar = auxiliar.substring(0, auxiliar.length()-1);
//			auxiliar = auxiliar.substring(1, auxiliar.length());
//			lineTime =  Long.parseLong( auxiliar );
//		 }
//
//		 return lineTime;
//	 }
//	 
	 
	 
//	 private boolean read(){
//		 long lineTime;
//		 long expectedLineTime;
//		 int counter = 1;
//		 String line, expectedLine;
//		 boolean testCorrect = true;
//		 try {
//			 
//			line 		 = bufferedReader.readLine();
//			System.out.println(line);
//			expectedLine = expectedBufferedReader.readLine();
//			
//			while( (line != null) && (expectedLine != null) ){
//				lineTime = getLineTime(line);
//				expectedLineTime = getLineTime(expectedLine);
//				line = treatLine(line);
//				expectedLine = treatLine(expectedLine);
//
//				if((line != null)||(expectedLine != null)){
//					if(!checkLines(line, expectedLine)){
//						System.out.println("Error in line "+counter+": "+"The lines:");
//						System.out.println(line);
//						System.out.println(expectedLine);
//						System.out.println("Are different");
//						System.out.println("__________________________________________");
//						testCorrect = false;
//					}
//					if(!checkTimes(lineTime, expectedLineTime)){
//						System.out.println("Error in line "+counter+": "+"The times:");
//						System.out.println(lineTime);
//						System.out.println(expectedLineTime);
//						System.out.println("Are different");
//						System.out.println("__________________________________________");
//						testCorrect = false;
//					}
//				
//				}else  System.out.println("Warning in line "+counter+" is empty! \nCheck the simulation time and the expeced simulation time.");					
//					
//
//					
//				
//
//				line     		 = bufferedReader.readLine();
//				System.out.println(line);
//				expectedLine     = expectedBufferedReader.readLine();
//				counter++;
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return testCorrect;
//	 }
	 
//	 private boolean checkTimes(long lineTime, long expectedLineTime,long error_margin_time_units_top, long error_margin_time_units_bot){
//		 boolean isCorrect = true;
//		 if(lineTime != expectedLineTime){
//			 if(lineTime > (expectedLineTime + error_margin_time_units_top) ) isCorrect = false;
//			 if(lineTime < (expectedLineTime - error_margin_time_units_bot) ) isCorrect = false;
//		 }
//		 
//		 return isCorrect;
//	 }
//	 
//	 private boolean checkLines(String line, String expectedLine){
//		 boolean isCorrect = false;
//		 
//		 if(line.equalsIgnoreCase(expectedLine)) isCorrect = true;
//		 
//		 return isCorrect;
//	 }
	 
//	 private void open(String fileName, String expectedFileName, String resultsFileName){
//			try {
//				
//			    file  		 		     = new File          (  fileName );
//				fileReader 				 = new FileReader 	 (          file	     );
//				bufferedReader			 = new BufferedReader(       fileReader      );
//				
//			    expectedFile  		 	 = new File          (  expectedFileName  );
//				expectedFileReader 		 = new FileReader 	 (      expectedFile	 );
//				expectedBufferedReader	 = new BufferedReader(   expectedFileReader  );	
//				
//				fileWriter  			 = new FileWriter ( resultsFileName );
//				printWriter 			 = new PrintWriter( fileWriter ); 
//				
//
//		
//				
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	 }
	 
//	private void close(){
//		try {
//			
//			bufferedReader.close();
//			fileReader.close();
//			expectedBufferedReader.close();
//			expectedFileReader.close();
//			
//			fileWriter.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
