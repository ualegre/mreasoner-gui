package edu.casetools.icase.mreasoner.gui.view.panels.options.panels;

import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class RadioButtonsPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	JRadioButton left,right;
	
	public RadioButtonsPanel(String[] names){
		
        left = new JRadioButton(names[0]);
        left.setFocusable(false);
        right   = new JRadioButton(names[1]);
        right.setFocusable(false);
        
	    this.add(left);
	    this.add(right);
		
	}
	
	public JRadioButton getLeftRadioButton(){
		return left;
	}
	
	public JRadioButton getRightRadioButton(){
		return right;
	}
	
	public void enableRadioButtons(boolean enable){
		left.setEnabled(enable);
		right.setEnabled(enable);
	}
	
	public void setValue(boolean value){
		if(value){
			this.getLeftRadioButton().setSelected(true);
			this.getRightRadioButton().setSelected(false);
		}else{
			this.getLeftRadioButton().setSelected(false);
			this.getRightRadioButton().setSelected(true);
		}
	}


}
