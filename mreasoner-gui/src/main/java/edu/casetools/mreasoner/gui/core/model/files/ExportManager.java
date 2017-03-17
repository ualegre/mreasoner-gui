package edu.casetools.mreasoner.gui.core.model.files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.casetools.dcase.m2nusmv.M2NuSMV;
import edu.casetools.dcase.m2nusmv.data.MData;
import edu.casetools.dcase.m2nusmv.data.elements.BoundedOperator;
import edu.casetools.dcase.m2nusmv.data.elements.BoundedOperator.BOP_TYPE;
import edu.casetools.dcase.m2nusmv.data.elements.Rule;
import edu.casetools.dcase.m2nusmv.data.elements.RuleElement;
import edu.casetools.dcase.m2nusmv.data.elements.State;
import edu.casetools.mreasoner.configurations.data.MConfigurations;
import edu.casetools.mreasoner.configurations.data.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.core.compiler.iterations.ParseException;
import edu.casetools.mreasoner.core.elements.MInputData;
import edu.casetools.mreasoner.core.elements.rules.SameTimeRule;
import edu.casetools.mreasoner.core.elements.time.TemporalOperator;
import edu.casetools.mreasoner.core.elements.time.TemporalOperator.TOP_TYPE;
import edu.casetools.mreasoner.gui.architecture.design.TestCaseLoader;

public class ExportManager {

	private MConfigurations configs;
	private TestCaseLoader loader;
	private M2NuSMV translator;
	private MInputData systemData;
	private MData verificationData;
	private int strNo;
	private int ntrNo;
	private int bopNo;
	
	public ExportManager() {
		loader = new TestCaseLoader();
		strNo = 0;
		ntrNo = 0;
		bopNo = 0;
	}

	public void export(String filename, MConfigurations configs){
			this.configs = configs;
			try {
				if(configs.getExecutionMode() == EXECUTION_MODE.SIMULATION_ITERATION){
					systemData = loader.readSystemInputData_Iteration(configs.getSystemSpecificationFilePath());
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
			if(existingBop.getId().equals(bop.getId())){
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
		List<BoundedOperator> bops = new ArrayList<>();
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
			return "sip_"+top.getName();
		case WEAK_RELATIVE:
			return "wip_"+top.getName();
		case STRONG_ABSOLUTE:
			return "sap_"+top.getName();
		case WEAK_ABSOLUTE:
			return "wap_"+top.getName();
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
		return rule;
	}

	private RuleElement stateToRuleElement(edu.casetools.mreasoner.core.elements.states.State state){
		RuleElement ruleElement = new RuleElement();
		ruleElement.setName(state.getName());
		ruleElement.setStatus(Boolean.toString(state.getStatus()).toUpperCase());
		return ruleElement;
	}
	
	private List<RuleElement> getRuleAntecedents(SameTimeRule str) {
		List<RuleElement> antecedents = new ArrayList<>();
		for(edu.casetools.mreasoner.core.elements.states.State state : str.getAntecedents()){
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
		for(edu.casetools.mreasoner.core.elements.states.State systemState : systemData.getSystemStatus().getSystemStatus()){
			for(int i=0; i < verificationData.getStates().size(); i++){
				if(systemState.getName().equals(verificationData.getStates().get(i).getName())){
					verificationData.getStates().get(i).setInitialValue(Boolean.toString(systemState.getStatus()).toUpperCase());
				}
			}
		}
	}

	private void setIndependentStates() {
		for(edu.casetools.mreasoner.core.elements.states.State systemState : systemData.getIndependentStates()){
			for(int i=0; i < verificationData.getStates().size(); i++){
				if(systemState.getName().equals(verificationData.getStates().get(i).getName())){
					verificationData.getStates().get(i).setIndepedence(true);
				}
			}
		}
	}
	
}
