package edu.casetools.icase.mreasoner.gui.view.panels.main.mapping;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;

import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;


public class ImplementationPanel extends JPanel{

private static final long serialVersionUID = 1L;
	
	JLabel implementationLabel,isOnOffLabel,maxValueLabel,minValueLabel,configsLabel;
	JButton addButton,deleteButton;
	JPanel topPanel;//,botButtonsPanel;
	JTextField implementationTf,maxValueTf,minValueTf,configsTf;
	JCheckBox  isOnOffCb;
	JTable table;
	JScrollPane scrollPane;
	boolean hasConfigs;


	 
	public ImplementationPanel(boolean hasConfigs){
		this.hasConfigs = hasConfigs;
		this.setBorder(BorderFactory.createTitledBorder("Implementation Manager"));
		this.createTopPanel();
		this.createTablePanel();
		this.add(topPanel);
		this.add(scrollPane);
	}
	
	private void createTablePanel() {
		table = new JTable();
		
		scrollPane = new JScrollPane(table);

	}

	private void createTopPanel(){
		int rows = 0;
		if(hasConfigs) rows = 5;
		else rows = 4;
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(0,50,150,50));

        JPanel p = new JPanel(new SpringLayout());
        implementationLabel  = new JLabel("Implementation name: ");
        isOnOffLabel     	 = new JLabel("Has boolean values only:");
        minValueLabel     	 = new JLabel("Max. value: ");
        maxValueLabel    	 = new JLabel("Min. value: ");
        if(hasConfigs)configsLabel         = new JLabel("Additional configs. :");
    
        implementationTf	 =  new JTextField(20);   
        isOnOffCb			 =  new JCheckBox();
        maxValueTf 			 =  new JTextField(20);
        minValueTf 			 =  new JTextField(20);
        if(hasConfigs)configsTf 			 =  new JTextField();
        
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,100,5,100));
        addButton 		= new JButton("Add Relation");
		deleteButton 	= new JButton("Delete Selected Relation");
        addButton.setFocusable(false);
        deleteButton.setFocusable(false);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        
        maxValueLabel.setLabelFor(implementationTf);
        
        p.add(implementationLabel);
        p.add(implementationTf);
        p.add(isOnOffLabel);
        p.add(isOnOffCb);
        p.add(maxValueLabel);
        p.add(maxValueTf);
        p.add(minValueLabel);
        p.add(minValueTf);
        if(hasConfigs)p.add(configsLabel);
        if(hasConfigs)p.add(configsTf);


 

        p.setBorder(BorderFactory.createEmptyBorder(0,50,10,50));

        SpringUtilities.makeCompactGrid(p,
                                        rows, 2, //rows, cols
                                        16, 16,        //initX, initY
                                        26, 26);       //xPad, yPad
        
        topPanel.add(p,BorderLayout.CENTER);
        topPanel.add(buttonPanel,BorderLayout.SOUTH);

	}

	public void addActionListener(ActionListener actionListener){
		addButton.addActionListener(actionListener);
		deleteButton.addActionListener(actionListener);
	}
	
	public JTextField getDeviceTextField(){
		return implementationTf;
	}
	
	public JTextField getStateTextField(){
		return maxValueTf;
	}
	

	public void resetTextFields() {
		implementationTf.setText("");
		maxValueTf.setText("");
		minValueTf.setText("");
		
	}

	public JLabel getImplementationLabel() {
		return minValueLabel;
	}
	
	public JTable getTable(){
		return table;
	}
	
	public void setModel(TableModel model){
		this.table.setModel(model);
	}
	
}