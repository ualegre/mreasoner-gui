package edu.casetools.icase.mreasoner.gui.view.panels.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


public class SystemSpecificationEditorPanel extends JPanel {

	private   static final long serialVersionUID = -216672071026757801L;
	private   final int FONT_SIZE = 12;
	private   JPanel midPanel;
	protected EditorPanel fileTextPane;
	protected JTextArea resultsTextArea;
	private   JScrollPane resultsScrollPane;
	private   JSplitPane midMidPanel;


	public SystemSpecificationEditorPanel() {
		setLayout(new BorderLayout());
		this.createMidPanel();	
		
	}
	
	private void createMidPanel(){
		midPanel = new JPanel(new BorderLayout());
		this.createMidMidPanel();
		this.add(midPanel,BorderLayout.CENTER);
	}
	
	private void createMidMidPanel() {
		midMidPanel = new JSplitPane();
		midMidPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		fileTextPane    = new EditorPanel();
		fileTextPane.setFont(new Font("Consolas", Font.PLAIN, FONT_SIZE));
		setUndoFeature();
		resetFileText();

		resultsTextArea = new JTextArea();
		resultsTextArea.setForeground(Color.WHITE);
		resultsTextArea.setBackground(Color.BLACK);
		resultsTextArea.setFont(new Font("Consolas", Font.PLAIN, FONT_SIZE));
		resultsTextArea.setAutoscrolls(true);
		resultsTextArea.setEditable(false);
	
		DefaultCaret caret = (DefaultCaret) resultsTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		

		resultsScrollPane = new JScrollPane(resultsTextArea);

		
		midMidPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));		
	
		midMidPanel.setLeftComponent(fileTextPane);
		midMidPanel.setRightComponent(resultsScrollPane);
		midMidPanel.setResizeWeight(0.5);
		midMidPanel.setDividerSize(5);

		midPanel.add(midMidPanel,BorderLayout.CENTER);
	}

	
	
	public void resetFileText(){
		String s = " states( s, s, s, s, s);\n\n is( #s );\n is( s );\n\n holdsAt(#s ,0);\n\n"+
				" occurs( ingr( #s ), time );\n occurs( ingr( s ), time );\n\n ssr( (# s, "+
				"s, #s, s)   -> s );\n\n sEr( (#s ,s ,#s ,s )   -> s );";
		fileTextPane.getTextArea().setText(s);
	}
	


	public JTextArea getFileTextPane() {
		return fileTextPane.getTextArea();
	}

	public JTextArea getResultsTextArea() {
		return resultsTextArea;
	}
	
	public void refreshMidPanel(){
		midMidPanel.revalidate();
		midMidPanel.repaint();
	}
	
	public void centerSplitPaneDivider(){
		midMidPanel.setDividerLocation(0.5);
	}
	

	
	/*THIS FUNCTION IS NOT WELL REGARDING TO THE MVC STRUCTURE*/
	private void setUndoFeature(){
		final UndoManager undo = new UndoManager();
		Document doc = fileTextPane.getTextArea().getDocument();
		
		 doc.addUndoableEditListener(new UndoableEditListener() {
				public void undoableEditHappened(UndoableEditEvent arg0) {
					  undo.addEdit(arg0.getEdit());
					
				}
		    });
		 
		 fileTextPane.getActionMap().put("Undo", new AbstractAction("Undo") {
			private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent arg0) {
					 try {
				          if (undo.canUndo()) {
				            undo.undo();
				          }
				        } catch (CannotUndoException e) {
				        }
				}
		    });
		 
		 fileTextPane.getActionMap().put("Redo", new AbstractAction("Redo") {
			private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent arg0) {
					 try {
				          if (undo.canRedo()) {
				            undo.redo();
				          }
				        } catch (CannotUndoException e) {
				        }
				}
		    });
		 
		 	fileTextPane.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
		 	fileTextPane.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		
	}

	public JSplitPane getSplitPane() {
		return this.midMidPanel;	
	}
	
	
	
	

}
