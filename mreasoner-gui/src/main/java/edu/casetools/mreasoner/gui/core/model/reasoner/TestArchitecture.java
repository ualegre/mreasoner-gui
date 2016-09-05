package edu.casetools.mreasoner.gui.core.model.reasoner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.casetools.mreasoner.gui.architecture.design.Tester;
import edu.casetools.mreasoner.gui.core.controller.Controller;

public class TestArchitecture extends Thread{

	Tester tester;
	//InputSimulator inputSimulator;
	Controller controller;
	String configsFilePath;

	private static Lock lock = new ReentrantLock();
	
	public void terminate(){
		tester.terminateTester();
	}
	
    public TestArchitecture(Controller controller,String configsFilePath) {
        super("Reasoner");
    	tester 			= new Tester();
    	this.controller = controller;
    	this.configsFilePath = configsFilePath;
    }
    
	 public void run() {
	        if (lock.tryLock())
	        {
	            try
	            {

	    			if(tester.loadTests(configsFilePath)){
		    			tester.executeTests();
		    			tester.checkResults();
	    			}
	            }
	            finally
	            {
	               lock.unlock();
	               try {
		          		controller.getView().getMainWindow().getMainPanel().getMainMenu().enableStopButton(false);
						this.finalize();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	            
	 }
	 

}
