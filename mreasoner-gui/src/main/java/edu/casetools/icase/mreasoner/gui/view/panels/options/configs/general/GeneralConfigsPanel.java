package edu.casetools.icase.mreasoner.gui.view.panels.options.configs.general;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.core.elements.time.conf.TimeConfigs.EXECUTION_MODE;
import edu.casetools.icase.mreasoner.gui.view.panels.options.panels.RadioButtonsPanel;

public class GeneralConfigsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel stratification,type;
	JComboBox<String> executionMode;
	//JTextField systemNameTf;
	RadioButtonsPanel stratificationPanel;


	public GeneralConfigsPanel(MConfigs configs){
		TitledBorder titledBorder = BorderFactory.createTitledBorder("General Configurations");
		this.setBorder(titledBorder);
		this.setLayout(new GridLayout(2,1));
		
		
	     JPanel p = new JPanel(new GridLayout(4,1));
	     
	     type = new JLabel("Execution type: ");
	     String s[] = {"Simulation: Time expressed in iterations","Simulation: Time expressed in real time","Real Environment: With Sensors/Actuators"};
	     executionMode = new JComboBox<String>(s);
	     executionMode.setPreferredSize(new Dimension(5, 100));

	     stratification = new JLabel("Use automatic stratification: ");
		 String[] stratificationString =  {"Yes","No"};
	     stratificationPanel = new RadioButtonsPanel(stratificationString);
         //systemNameTf =  new JTextField(20);

         p.add(type);
         p.add(executionMode);
         p.add(stratification);
         p.add(stratificationPanel);


		this.setGeneralConfigs(configs);
		

    	
//        SpringUtilities.makeCompactGrid(p,
//                                        2, 2, //rows, cols
//                                        6, 6,        //initX, initY
//                                        6, 6);       //xPad, yPad
        
    	this.add(p,BorderLayout.CENTER);
    	this.add(new JPanel());
		
	}
	

	
	public MConfigs getGeneralConfigs(MConfigs configs){
		if (configs == null) configs = new MConfigs();	
		//String value = systemNameTf.getText();
		if(stratificationPanel.getLeftRadioButton().isSelected()) configs.useStratification(true);
		else configs.useStratification(false);
		//configs.getDBConfigs().setTable(value);
		configs.getTimeConfigs().setExecutionMode(getExecutionMode());
		//else configs.setExecutionMode(false);
		
		return configs;
	}
	
	public EXECUTION_MODE getExecutionMode(){
		if(executionMode.getSelectedIndex()==2) return EXECUTION_MODE.REAL_ENVIRONMENT;
		else if(executionMode.getSelectedIndex()==1) return EXECUTION_MODE.SIMULATION_REAL_TIME;
		else return EXECUTION_MODE.SIMULATION_ITERATION;
		
	}
	
	public void setExecutionMode(EXECUTION_MODE mode){
		switch(mode){
		case REAL_ENVIRONMENT:
			executionMode.setSelectedIndex(2);
			break;
		case SIMULATION_ITERATION:
			executionMode.setSelectedIndex(0);
			break;
		case SIMULATION_REAL_TIME:
			executionMode.setSelectedIndex(1);
			break;
		default:
			break;
		
		}
	}
	
	public void setGeneralConfigs(MConfigs configs){
		setExecutionMode(configs.getTimeConfigs().getExecutionMode());
		if(configs.useStratification()){
			this.stratificationPanel.getLeftRadioButton().setSelected(true);
			this.stratificationPanel.getRightRadioButton().setSelected(false);
		}else{
			this.stratificationPanel.getLeftRadioButton().setSelected(false);
			this.stratificationPanel.getRightRadioButton().setSelected(true);
		}

	}


	 public JComboBox<String> getExecutionModeComboBox(){
		 return executionMode;
	 }

	 public RadioButtonsPanel getStratificationPanel(){
		 return stratificationPanel;
	 }


	public void addActionListener(ActionListener actionListener) {
			executionMode.addActionListener(actionListener);
			stratificationPanel.getLeftRadioButton().addActionListener(actionListener);
			stratificationPanel.getRightRadioButton().addActionListener(actionListener);
		
	}
	
	public void addStratificationPanelActionListener(ActionListener actionListener){
		this.stratificationPanel.getLeftRadioButton().addActionListener(actionListener);
		this.stratificationPanel.getRightRadioButton().addActionListener(actionListener);
		
	}
	
}
