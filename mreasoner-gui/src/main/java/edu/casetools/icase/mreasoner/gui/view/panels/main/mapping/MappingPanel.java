package edu.casetools.icase.mreasoner.gui.view.panels.main.mapping;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import edu.casetools.icase.mreasoner.gui.view.panels.models.types.SensorsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.utils.SpringUtilities;


public class MappingPanel extends JPanel{

private static final long serialVersionUID = 1L;
	
	JLabel deviceLabel,stateLabel;

	 JLabel implementationLabel;
	 JButton addButton,deleteButton;
	 JPanel topPanel,tablePanel;//,botButtonsPanel;
	 JComboBox<String> implementationComboBox;
	 JTextField sensorTf,stateTf;
	 JTable mappingTable;
	 JScrollPane mappingScroll;
	
	public MappingPanel(){
		mappingTable = new JTable();
		//mainPanel = new JPanel();
		this.setLayout(new GridLayout(1,2));
		this.setBorder(BorderFactory.createTitledBorder("Sensor Mapping"));
		this.createTopPanel();
		this.createTablePanel();
		this.add(topPanel);
		this.add(mappingScroll);
		//this.add(new JPanel());
	//	this.add(mainPanel);
		


	}
	
	private void createTablePanel() {
	//	SensorTable st = new SensorsTableModel();
		mappingTable = new JTable();

		//mappingTable.getTableHeader().setReorderingAllowed(false);
//		tablePanel.add(mappingTable);
		mappingScroll = new JScrollPane(mappingTable);

//	 	mappingScroll.setBorder(BorderFactory.createEmptyBorder(0,0,0,150));

	}

	private void createTopPanel(){
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(0,50,150,5));

        JPanel p = new JPanel(new SpringLayout());
        deviceLabel  = new JLabel("Device id: ");
        implementationLabel     = new JLabel("Sensor Implementation: ");
        stateLabel    = new JLabel("State: ");
        implementationComboBox = new JComboBox<String>();
    
        sensorTf =  new JTextField(20);   
        stateTf =  new JTextField(20);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,100,5,100));
        addButton 		= new JButton("Add Relation");
		deleteButton 	= new JButton("Delete Selected Relation");
        addButton.setFocusable(false);
        deleteButton.setFocusable(false);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        
        stateLabel.setLabelFor(sensorTf);
        
        p.add(deviceLabel);
        p.add(sensorTf);
        p.add(implementationLabel);
        p.add(implementationComboBox);
        p.add(stateLabel);
        p.add(stateTf);
 

        p.setBorder(BorderFactory.createEmptyBorder(0,50,10,50));

        SpringUtilities.makeCompactGrid(p,
                                        3, 2, //rows, cols
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
		return sensorTf;
	}
	
	public JTextField getStateTextField(){
		return stateTf;
	}
	
	public void resetTextFields() {
		sensorTf.setText("");
		stateTf.setText("");
		if(implementationComboBox.getItemCount() > 0)
			implementationComboBox.setSelectedIndex(0);
	}

	public JLabel getImplementationLabel() {
		return implementationLabel;
	}

	public String getSelectedImplementation() {
		int index =  implementationComboBox.getSelectedIndex();
		return implementationComboBox.getItemAt(index);
	}

	public JComboBox<String> getImplementationComboBox() {
		return implementationComboBox;
		
	}
	
	public JTable getTable(){
		return mappingTable;
	}

	public void setModel(SensorsTableModel mappingModel) {
		mappingTable.setModel(mappingModel);
	
	}
	
}