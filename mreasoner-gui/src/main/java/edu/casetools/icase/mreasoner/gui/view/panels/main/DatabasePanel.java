package edu.casetools.icase.mreasoner.gui.view.panels.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import edu.casetools.icase.mreasoner.core.elements.time.conf.TimeConfigs;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.EventsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.InternalEventsTableModel;
import edu.casetools.icase.mreasoner.gui.view.panels.models.types.ResultsTableModel;

public class DatabasePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JTable resultsTable, eventsTable, internalEventsTable;
	JPanel resultsPanel,eventsPanel,internalEventsPanel;
	JScrollPane resultScroll, eventsScroll,internalEventsScroll;
	TitledBorder  resultsBorder,eventsBorder,internalEventsBorder;
	ResultsTableModel 		 resultsModel;
	EventsTableModel 		 eventsModel;
	InternalEventsTableModel internalEventsModel;
	JTable table;
	
	public DatabasePanel(TimeConfigs configs){
		this.setLayout(new GridLayout(3,1));
		resultsPanel = new JPanel(new BorderLayout());
		eventsPanel  = new JPanel(new BorderLayout());
		internalEventsPanel = new JPanel(new BorderLayout());
		
		resultsBorder 		 = BorderFactory.createTitledBorder("Results");
		eventsBorder 		 = BorderFactory.createTitledBorder("Events");
		internalEventsBorder = BorderFactory.createTitledBorder("Internal Events");
		
		resultsPanel.setBorder(resultsBorder);
		eventsPanel.setBorder(eventsBorder);
		internalEventsPanel.setBorder(internalEventsBorder);

		
		resultsModel 		 = new ResultsTableModel();
		eventsModel 		 = new EventsTableModel(configs);
		internalEventsModel  = new InternalEventsTableModel(configs);
		
		resultsTable 		 = new JTable(resultsModel);
		eventsTable			 = new JTable(eventsModel);
		internalEventsTable  = new JTable(internalEventsModel);
		
		resultsTable.getTableHeader().setReorderingAllowed(false);
		eventsTable.getTableHeader().setReorderingAllowed(false);
		internalEventsTable.getTableHeader().setReorderingAllowed(false);
		
		resultScroll 		 = new JScrollPane(resultsTable);
		eventsScroll		 = new JScrollPane(eventsTable);
		internalEventsScroll = new JScrollPane(internalEventsTable);
		
		resultsPanel.add(resultScroll,BorderLayout.CENTER);
		eventsPanel.add(eventsScroll,BorderLayout.CENTER);
		internalEventsPanel.add(internalEventsScroll,BorderLayout.CENTER);
		this.add(resultsPanel);
		this.add(eventsPanel);
		this.add(internalEventsPanel);
		
	}
	
	public JTable getTable(){
		return table;
	}
	
	public JTable getResultsTable(){
		return resultsTable;
	}
	
	public JTable getEventsTable(){
		return eventsTable;
	}
	
	public JTable getInternalEventsTable(){
		return internalEventsTable;
	}
	
	
	
	
}
