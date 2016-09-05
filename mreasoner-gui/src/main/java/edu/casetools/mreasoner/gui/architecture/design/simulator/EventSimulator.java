package edu.casetools.mreasoner.gui.architecture.design.simulator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MDBTypes.DB_IMPLEMENTATION;
import edu.casetools.mreasoner.core.MReasoner;
import edu.casetools.mreasoner.core.elements.events.Event;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperations;
import edu.casetools.mreasoner.database.core.operations.DatabaseOperationsFactory;



public class EventSimulator extends Thread{
	
	Vector<Event>     		  eventHistory;
	DatabaseOperations 	      connection;
	boolean                   running;
	boolean 				  isRealTime;
	
	public EventSimulator(Vector<Event> eventHistory,MConfigurations configs){
		this.eventHistory = eventHistory;
		this.connection = 
				DatabaseOperationsFactory.getDatabaseOperations( DB_IMPLEMENTATION.POSTGRESQL,
																 configs.getDBConfigs()  );
		this.isRealTime = !configs.getTimeIsGivenInIterations();
		running = true;
	
	}
	public void setEventsHistory( Vector<Event> eventHistory){
		this.eventHistory = eventHistory;

	}
	
	public void setDatabase(DatabaseOperations connection){
		this.connection		  = connection;
	}
	
	public Vector<Event> getEventsAt_Simulation(){
		Vector<Event> events = new Vector<Event>();

			for(int i = 0; i < eventHistory.size(); i++){
			//	System.out.println("ITERATION!: "+MTPL.MTPL.systemStatus.getTime().getIteration());
				if(eventHistory.get(i).getTime() == MReasoner.systemStatus.getTime().getIteration()){
					events.add(eventHistory.get(i));
				//	System.out.println("EVENTO:"+eventHistory.get(i).getName());
					eventHistory.removeElementAt(i);
					i--;
				}
			}

		return events;
	}
	
	public Vector<Event> getEventsAt_Real(){
		Vector<Event> events = new Vector<Event>();
		
		for(int i = 0; i < eventHistory.size(); i++){
			//System.out.println("�������� eventHistory:"+eventHistory.get(i).getName()+"  "+eventHistory.get(i).getTime()+ " vs "+Reasoner.Reasoner.systemStatus.getTime().getSystemRealTime());
			//compareTimes(eventHistory.get(i).getTime(),Reasoner.Reasoner.systemStatus.getTime().getSystemRealTime());
			if(compareTimes(eventHistory.get(i)) >= 0){
			//	System.out.println("ENTROO!");
				events.add(eventHistory.get(i));
				eventHistory.removeElementAt(i);
				i--;
			}
		}
		return  events;
	}
	
	private int compareTimes(Event event){
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		
		c1.setTimeInMillis(MReasoner.systemStatus.getTime().getSystemRealTime());
		c2.setTimeInMillis(MReasoner.systemStatus.getTime().getSystemRealTime());
		
		c2 = event.setValuesIntoCalendar(c2);
		
	//	System.out.println("__________________________");
	//	printTime(c1);
	//	printTime(c2);
	//	System.out.println("__________________________");
		
		return c1.compareTo(c2);
		
	}
	
	
	
//	private void printTime(Calendar c1){
//		System.out.println(c1.get(Calendar.DAY_OF_MONTH)+"/"+c1.get(Calendar.MONTH)+"/"+c1.get(Calendar.YEAR)+" - "+c1.get(Calendar.HOUR)+":"+c1.get(Calendar.MINUTE)+":"+c1.get(Calendar.SECOND));
//	}
	
	public void insertEventsAt() {
		Vector<Event> events = null;
	//	if(time.isSimulation()){
			MReasoner.semaphore.inputTake();
				if(!isRealTime) events = getEventsAt_Simulation();
				else events = getEventsAt_Real();
				for(int i=0;i<events.size();i++){
					
					connection.insertEvent( 
							
											events.get(i).getName(),
											""+events.get(i).getStatus(),
											""+MReasoner.systemStatus.getTime().getIteration(),
											MReasoner.systemStatus.getTime().getDateFromRealTimeMillis(),
											MReasoner.systemStatus.getTime().getDayTimeFromRealTimeMillis()
											
										  );
					//connection.insertEvent(events.get(i));
				}
				MReasoner.semaphore.reasonerPut();
	//	}else{
	//		getEventsAt_RealTime(time.g)
	//	}
		
	}
	
	@Override
	public void run() {
		while(running){		
			insertEventsAt();
		}
		// TODO Auto-generated method stub
	}
	
	public void terminate() {
		
		running = false;
		MReasoner.semaphore.inputPut();
		
	}
}
