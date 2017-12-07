package edu.casetools.mreasoner.gui.model.io.nusmv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.casetools.dcase.m2nusmv.M2NuSMV;
import edu.casetools.dcase.m2nusmv.data.MData;
import edu.casetools.dcase.m2nusmv.data.elements.BoundedOperator;
import edu.casetools.dcase.m2nusmv.data.elements.BoundedOperator.BOP_TYPE;
import edu.casetools.dcase.m2nusmv.data.elements.Rule;
import edu.casetools.dcase.m2nusmv.data.elements.RuleElement;
import edu.casetools.dcase.m2nusmv.data.elements.State;
import edu.casetools.dcase.m2nusmv.data.elements.Time;
import edu.casetools.mreasoner.core.MSpecification;
import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.core.configs.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.core.data.rules.SameTimeRule;
import edu.casetools.mreasoner.core.data.states.Internal.CalendarAt;
import edu.casetools.mreasoner.core.data.states.Internal.CalendarBetween;
import edu.casetools.mreasoner.core.data.states.Internal.ClockAt;
import edu.casetools.mreasoner.core.data.states.Internal.ClockBetween;
import edu.casetools.mreasoner.core.data.states.Internal.WeekDayAt;
import edu.casetools.mreasoner.core.data.states.Internal.WeekDayBetween;
import edu.casetools.mreasoner.core.data.time.TemporalOperator;
import edu.casetools.mreasoner.core.data.time.TemporalOperator.TOP_TYPE;
import edu.casetools.mreasoner.io.MSpecificationLoader;
import edu.casetools.mreasoner.io.compiler.iterations.ParseException;

public class ExportManager {

	private MConfigurations configs;
	private MSpecificationLoader loader;
	private M2NuSMV translator;
	private MSpecification systemData;
	private MData verificationData;
	private int strNo;
	private int ntrNo;
	private int bopNo;
	private final int DAY_SECONDS = 86400;
	
	public ExportManager() {
		loader = new MSpecificationLoader();
		strNo = 0;
		ntrNo = 0;
		bopNo = 0;
	}

	public void export(String filename, MConfigurations configs){
			this.configs = configs;
			try {
				if(configs.getExecutionMode() == EXECUTION_MODE.SIMULATION_ITERATION){
					systemData = loader.readSystemSpecifications_Iteration(configs.getSystemSpecificationFilePath());
					translator = new M2NuSMV();
					exportData(filename);
					translator.writeModel(verificationData);
				} else {
					
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

	private void exportData(String filename) {
		verificationData = new MData();
		verificationData.setFilePath(filename);
		verificationData.setMaxIteration((int) configs.getExecutionTime());
	    generateStates();
	    generateRules();
	    generateTemporalOperators();
	}

	private void generateTemporalOperators() {
		generateStrTemporalOperators();
		generateNtrTemporalOperators();
	}

	private void generateStrTemporalOperators() {
		for(Rule rule : verificationData.getStrs()){
			getTemporalOperator(rule);
		}
	}
	
	private void generateNtrTemporalOperators() {
		for(Rule rule : verificationData.getNtrs()){
			getTemporalOperator(rule);
		}
	}

	private void getTemporalOperator(Rule rule) {
		for(BoundedOperator bop : rule.getBops()){
			if(!boundedOperatorExists(bop)){
				verificationData.getBops().add(bop);
			}
		}
	}

	private boolean boundedOperatorExists(BoundedOperator bop) {
		boolean exists = false;
		for(BoundedOperator existingBop : verificationData.getBops()){
			if(existingBop.getOperatorName().equals(bop.getOperatorName())){
				exists = true;
			}
		}
		return exists;
	}

	private void generateRules() {
		getStrRules();
		getNtrRules();
	}



	private List<BoundedOperator> getRuleTemporalOperators(SameTimeRule str) {
		List<BoundedOperator> bops = new ArrayList<BoundedOperator>();
		for(TemporalOperator bop : str.getTemporalOperators()){
			bops.add(getBoundedOperator(bop));
		}
		return bops;
	}

	private BoundedOperator getBoundedOperator(TemporalOperator top) {
		BoundedOperator bop = new BoundedOperator();
		bop.setType(getTemporalOperatorType(top));
		bop.setId(Integer.toString(bopNo));
		bopNo++;
		bop.setOperatorName(getOperatorName(top));
		bop.setLowBound(Long.toString(top.getSinceValue().getSimulation_value()));
		bop.setUppBound(getUpperBound(top));
		bop.setStateName(top.getName());
		bop.setStatus(Boolean.toString(top.getStatus()).toUpperCase());
		return bop;
	}

	private String getUpperBound(TemporalOperator top) {
		if((top.getType() == TOP_TYPE.STRONG_ABSOLUTE) || (top.getType() == TOP_TYPE.WEAK_ABSOLUTE)){
			return Long.toString(top.getUntilValue().getSimulation_value());
		} else return "";
	}

	private String getOperatorName(TemporalOperator top) {
		switch(top.getType()){
			case STRONG_RELATIVE:
				return top.getName()+"_sip_"+top.getSinceValue().getSimulation_value();
			case WEAK_RELATIVE:
				return top.getName()+"_wip_"+top.getSinceValue().getSimulation_value();
			case STRONG_ABSOLUTE:
				return top.getName()+"_sap_"+top.getSinceValue().getSimulation_value()+
						"_"+top.getUntilValue().getSimulation_value();
			case WEAK_ABSOLUTE:
				return top.getName()+"_wap_"+top.getSinceValue().getSimulation_value()+"_"+top.getUntilValue().getSimulation_value();
		}
		return "";
	}

	private BOP_TYPE getTemporalOperatorType(TemporalOperator top) {
		switch(top.getType()){
			case STRONG_RELATIVE:
				return BOP_TYPE.STRONG_IMMEDIATE_PAST;
			case WEAK_RELATIVE:
				return BOP_TYPE.WEAK_IMMEDIATE_PAST;
			case STRONG_ABSOLUTE:
				return BOP_TYPE.STRONG_ABSOLUTE_PAST;
			case WEAK_ABSOLUTE:
				return BOP_TYPE.WEAK_ABSOLUTE_PAST;
		}
		return null;
	}

	private void getStrRules() {
		for(SameTimeRule str : systemData.getSystemRules().getSameTimeRules()){
			verificationData.getStrs().add(getRule(str,strNo));
			strNo++;
		}
		
	}
	
	private void getNtrRules() {
		for(SameTimeRule ntr : systemData.getSystemRules().getNextTimeRules()){
			verificationData.getNtrs().add(getRule(ntr,ntrNo));
			ntrNo++;
		}
		
	}

	private Rule getRule(SameTimeRule ntr, int ruleNo) {
		Rule rule = new Rule();
		rule.setId(Integer.toString(ruleNo));
		rule.setAntecedents(getRuleAntecedents(ntr));
		rule.setBops(getRuleTemporalOperators(ntr));
		rule.setConsequent(stateToRuleElement(ntr.getConsequence()));
		rule.setTimeReferences(getTimeReferences(ntr));
		return rule;
	}

	private List<Time> getTimeReferences(SameTimeRule ntr) {
		List<Time> timeReferences = new ArrayList<>();
		for(int i=0; i < ntr.getInternalStates().size();i++){
			Time time = getTimeToSeconds(ntr, i);
			timeReferences.add(time);
		}
		return timeReferences;
	}

	private Time getTimeToSeconds(SameTimeRule ntr, int i) {
		Time time = new Time();
			if (ntr.getInternalStates().get(i) instanceof ClockAt){
				ClockAt clockAt = (ClockAt) ntr.getInternalStates().get(i);
				time.setLowBound(clockAt.getTime().getTimeOfDayInSeconds());
			}  else if (ntr.getInternalStates().get(i) instanceof ClockBetween){
				ClockBetween clockBetween = (ClockBetween) ntr.getInternalStates().get(i);
				time.setLowBound(clockBetween.getSince().getTimeOfDayInSeconds());
				time.setHighBound(clockBetween.getUntil().getTimeOfDayInSeconds());
			}  else if (ntr.getInternalStates().get(i) instanceof WeekDayAt){
				WeekDayAt weekDayAt = (WeekDayAt) ntr.getInternalStates().get(i);
				time.setLowBound(weekDayAt.getWeekDay()*DAY_SECONDS);
			}  else if (ntr.getInternalStates().get(i) instanceof WeekDayBetween){
				WeekDayBetween weekDayBetween = (WeekDayBetween) ntr.getInternalStates().get(i);
				time.setLowBound(weekDayBetween.getSince()*DAY_SECONDS);
				time.setLowBound(weekDayBetween.getUntil()*DAY_SECONDS);
			}  else if (ntr.getInternalStates().get(i) instanceof CalendarAt){
				CalendarAt calendarAt = (CalendarAt) ntr.getInternalStates().get(i);
				long since = getCalendarAtBoundNumber(calendarAt);
				if(since > 0){
					time.setLowBound(since);
				}else return null;
			}  else if (ntr.getInternalStates().get(i) instanceof CalendarBetween){
				CalendarBetween calendarBetween = (CalendarBetween) ntr.getInternalStates().get(i);
				long currentTime = new Date().getTime();	
				long since = getCalendarBetweenSinceNumber(currentTime, calendarBetween);
				long until = getCalendarBetweenUntilNumber(currentTime, calendarBetween);
				if(since > 0 && until > 0 ){
					time.setLowBound(since);
					time.setHighBound(until);
				}else return null;
			}  
		return time;
	}

	private long getCalendarBetweenUntilNumber(long currentTime, CalendarBetween calendarBetween) {
		Calendar c = Calendar.getInstance();
		c = calendarBetween.getSince().setValuesIntoCalendar(c);
		return (c.getTime().getTime() - currentTime);
	}

	private long getCalendarBetweenSinceNumber(long currentTime, CalendarBetween calendarBetween) {	
		Calendar c = Calendar.getInstance();
		c = calendarBetween.getUntil().setValuesIntoCalendar(c);
		return (c.getTime().getTime() - currentTime);
	}

	private long getCalendarAtBoundNumber(CalendarAt calendarAt) {
		long currentTime = new Date().getTime();	
		Calendar c = Calendar.getInstance();
		c = calendarAt.getDate().setValuesIntoCalendar(c);
		return (c.getTime().getTime() - currentTime);
	}

	private RuleElement stateToRuleElement(edu.casetools.mreasoner.core.data.states.State state){
		RuleElement ruleElement = new RuleElement();
		ruleElement.setName(state.getName());
		ruleElement.setStatus(Boolean.toString(state.getStatus()).toUpperCase());
		return ruleElement;
	}
	
	private List<RuleElement> getRuleAntecedents(SameTimeRule str) {
		List<RuleElement> antecedents = new ArrayList<RuleElement>();
		for(edu.casetools.mreasoner.core.data.states.State state : str.getAntecedents()){
			antecedents.add(stateToRuleElement(state));
		}
		return antecedents;
		
	}

	private void generateStates() {
		
		setStateNames();		
		setIndependentStates();		
		setInitialStates();
		
	}

	private void setStateNames() {
		for(String stateName : systemData.getDeclaredStates()){
			State state = new State();
			state.setName(stateName);
			verificationData.getStates().add(state);
		}
	}

	private void setInitialStates() {
		for(edu.casetools.mreasoner.core.data.states.State systemState : systemData.getSystemStatus().getSystemStatus()){
			for(int i=0; i < verificationData.getStates().size(); i++){
				if(systemState.getName().equals(verificationData.getStates().get(i).getName())){
					verificationData.getStates().get(i).setInitialValue(Boolean.toString(systemState.getStatus()).toUpperCase());
				}
			}
		}
	}

	private void setIndependentStates() {
		for(edu.casetools.mreasoner.core.data.states.State systemState : systemData.getIndependentStates()){
			for(int i=0; i < verificationData.getStates().size(); i++){
				if(systemState.getName().equals(verificationData.getStates().get(i).getName())){
					verificationData.getStates().get(i).setIndepedence(true);
				}
			}
		}
	}
	
}
