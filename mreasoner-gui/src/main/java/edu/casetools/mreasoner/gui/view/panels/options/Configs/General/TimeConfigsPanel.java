package edu.casetools.mreasoner.gui.view.panels.options.Configs.General;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import edu.casetools.mreasoner.core.configs.MConfigurations;
import edu.casetools.mreasoner.core.configs.MConfigurations.EXECUTION_MODE;
import edu.casetools.mreasoner.gui.view.panels.options.Panels.RadioButtonsPanel;
import edu.casetools.mreasoner.gui.view.panels.utils.SpringUtilities;

public class TimeConfigsPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	JLabel timeUnit,executionTime,useFixedIterationLabel,relativeTimeLabel;
	JTextField timeUnitTf,executionTimeTf;
	RadioButtonsPanel iterationTimeRB,relativeTimeRB;
	
	public TimeConfigsPanel(MConfigurations configs){
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Time Configurations");
		this.setBorder(titledBorder);
		this.setLayout(new BorderLayout());

		 String[] accept =  {"Yes","No"};
		 iterationTimeRB = new RadioButtonsPanel(accept);
		 relativeTimeRB = new RadioButtonsPanel(accept);
		
	     JPanel p = new JPanel(new SpringLayout());
	     useFixedIterationLabel = new JLabel("Use a fixed iteration time: ");
	     executionTime = new JLabel("Execution time (in iterations): ");
         timeUnit = new JLabel("Iteration Time (in milliseconds): ");
         relativeTimeLabel = new JLabel("Use a maximum execution time:");
    //     relativeTimeValueLabel = new JLabel("Relative Time Unit (in milliseconds):");
         
         timeUnitTf =  new JTextField(20);
         executionTimeTf =  new JTextField(20);
     //    relativeTimeValueTf =  new JTextField(20);
        
         executionTime.setLabelFor(executionTimeTf);
         timeUnit.setLabelFor(timeUnitTf);
		
         p.add(useFixedIterationLabel);
         p.add(iterationTimeRB);
         p.add(timeUnit);
         p.add(timeUnitTf);
         p.add(relativeTimeLabel);
         p.add(relativeTimeRB);
         p.add(executionTime);
         p.add(executionTimeTf);

//         p.add(relativeTimeValueLabel);
//         p.add(relativeTimeValueTf);
//		   this.setTimeConfigs(configs);
		
    	this.add(p,BorderLayout.CENTER);
    	

        SpringUtilities.makeCompactGrid(p,
                                        4, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
    	
        this.setTimeConfigs(configs);
		
	}
	
	public RadioButtonsPanel getIterationTimePanel(){
		return iterationTimeRB;
	}
	
	public RadioButtonsPanel getRelativeTimeRBPanel(){
		return relativeTimeRB;
	}
	
	
	public void configureExecutionMode(EXECUTION_MODE mode){
		switch(mode){
		case REAL_ENVIRONMENT:
			setExecutionTimeLabelInMilliseconds(true);
			break;
		case SIMULATION_ITERATION:
			setExecutionTimeLabelInMilliseconds(false);
			break;
		case SIMULATION_REAL_TIME:
			setExecutionTimeLabelInMilliseconds(true);
			break;
		default:
			break;
		
		}
	}
	
	public MConfigurations getTimeConfigs(MConfigurations configs){	
		
		if(iterationTimeRB.getLeftRadioButton().isSelected()) configs.setUseFixedIterationTime(true);
		else configs.setUseFixedIterationTime(false);
		
		if(relativeTimeRB.getLeftRadioButton().isSelected()) configs.setUseMaxExecutionTime(true);
		else configs.setUseMaxExecutionTime(false);
		
		String value = executionTimeTf.getText();
		configs.setExecutionTime(Long.parseLong(value));

		value = timeUnitTf.getText();
		configs.setTimeUnit(Long.parseLong(value));

		return configs;
	}
	
	public void addIterationTimeRBListener(ActionListener actionListener){
		this.iterationTimeRB.getLeftRadioButton().addActionListener(actionListener);
		this.iterationTimeRB.getRightRadioButton().addActionListener(actionListener);
	}
	
	public void addRelativeTimeRBListener(ActionListener actionListener){
		this.relativeTimeRB.getLeftRadioButton().addActionListener(actionListener);
		this.relativeTimeRB.getRightRadioButton().addActionListener(actionListener);
	}

	
	public void setTimeConfigs(MConfigurations configs){
		timeUnitTf.setText(""+configs.getTimeUnit());
		executionTimeTf.setText(""+configs.getExecutionTime());
		iterationTimeRB.setValue(configs.isFixedIterationTime());
		if(configs.isFixedIterationTime()) timeUnitTf.setEditable(true);
		else timeUnitTf.setEditable(false);
		relativeTimeRB.setValue(configs.useMaxExecutionTime());
		if(configs.useMaxExecutionTime()) executionTimeTf.setEditable(true);
		else executionTimeTf.setEditable(false);
//		relativeTimeValueTf.setText(""+configs.getRelativeTimeValue());
//		if(configs.getRelativeTimeType()) relativeTimeValueTf.setEditable(false);
//		else relativeTimeValueTf.setEditable(true);
	}
	

	
	public void enableExecutionTimeTf(boolean enable){
		this.executionTimeTf.setEditable(enable);
	}

//	public JTextField getRelativeTimeValueTf() {
//		return this.relativeTimeValueTf;
//	}

	public  JTextField getTimeUnit() {
		return timeUnitTf;
	}

	public void setExecutionTimeLabelInMilliseconds(boolean isInMillis) {
		if(isInMillis){
			executionTime.setText("Execution time (in milliseconds): ");
		}else{
			executionTime.setText("Execution time (in iterations): ");
		}
		
	}

}
