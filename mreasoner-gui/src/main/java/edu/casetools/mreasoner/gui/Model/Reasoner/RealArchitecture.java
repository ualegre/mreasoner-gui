package edu.casetools.mreasoner.gui.Model.Reasoner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Launcher.Launcher;

public class RealArchitecture extends Thread{
	Launcher launcher;
	String configsFilePath;

	private static Lock lock = new ReentrantLock();
	
    public RealArchitecture(String configsFilePath) {
        super("Reasoner");
        launcher = new Launcher();
        this.configsFilePath = configsFilePath;
    }
    
    public void terminate(){
    	launcher.terminateReasoner();
    }
    
	 public void run() {
	        if (lock.tryLock())
	        {
	            try
	            {
	            	
	            	if(launcher.loadSystem(configsFilePath)){
	            		launcher.initializeReasoner();
	            	}
	            }
	            finally
	            {
	               lock.unlock();
	               try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	        }
	        System.out.println("SYSTEM ARCHITECTURE THREAD FINISHED");   
	 }
}
