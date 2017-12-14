package edu.casetools.icase.mreasoner.gui.view.panels.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import edu.casetools.icase.mreasoner.database.core.operations.DatabaseOperations;

public abstract class DefaultModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	public abstract void updateTable(DatabaseOperations con);
	
	public void defaultUpdateTable(ResultSet rs) {
	    if(rs!=null){  
	    		ResultSetMetaData rsmd;
	
				try {
					rsmd         = rs.getMetaData();
					int cols     = rsmd.getColumnCount();
				    String c[]   = new String[cols];
				    Object row[] = new Object[ cols ];
				    for( int i = 0; i < cols; i++ ){
				    	
			            c[i] = rsmd.getColumnName( i+1 );
			            this.addColumn( c[i] ); 
				    }
	
				    while( rs.next() ){
				    	
				         for( int i = 0; i < cols ; i++ )
				                 row[i] = rs.getString( i+1 );
				                
				         this.addRow(row);
	
				    }
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	}

	 public boolean isCellEditable(int row, int column) {
	        return false;
	 }
}
