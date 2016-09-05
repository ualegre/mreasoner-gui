package edu.casetools.mreasoner.gui.architecture.design;

import java.io.FileNotFoundException;
import java.util.Vector;


import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.reader.ParseException;
import edu.casetools.mreasoner.core.MReasoner;
import edu.casetools.mreasoner.gui.architecture.design.simulator.EventSimulator;


public class Tester {

	Vector<TestCase>   testCases;
	TestCaseLoader 	   testCaseLoader;
	EventSimulator 	   inputSimulator;
	ResultChecker  	   resultChecker;
	MReasoner 		  	   mtpl;
	
	public Tester(){
		testCaseLoader = new TestCaseLoader();
		resultChecker  = new ResultChecker();
		
	}
	
	public boolean loadTests(String configFileName) {
		try {
			testCases = testCaseLoader.getTestCases(configFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (edu.casetools.mreasoner.core.compiler.iterations.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (edu.casetools.mreasoner.core.compiler.realtime.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void executeTests(){
		

		for(int i=0;i<testCases.size();i++){
			System.out.println("TEST CASE: "+testCases.size());
			MConfigurations configs = testCases.get(i).getSystemConfigs();
			mtpl = new MReasoner(testCases.get(i).getSystemInput(),configs);
			//mtpl.MTPLInitialization();

        	inputSimulator  = new EventSimulator(testCases.get(i).getSystemInput().getEventsHistory(),configs);
			inputSimulator.start();
			mtpl.start();
			
			try {
		
				mtpl.join();
				inputSimulator.terminate();
				inputSimulator.join();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	
	public void checkResults(){
		resultChecker.checkResults(testCases);
	}
	

	public void terminateTester() {

		try {
			mtpl.terminate();
			mtpl.join();
			inputSimulator.terminate();
			inputSimulator.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
}
