package edu.casetools.mreasoner.gui.view.panels.main;

import java.awt.Color;

public class TranslationsPanel extends SystemSpecificationEditorPanel {

	private static final long serialVersionUID = -216672071026757801L;
	
	public TranslationsPanel() {
		super();
		resultsTextArea.setForeground(Color.BLACK);
		resultsTextArea.setBackground(Color.WHITE);
		resultsTextArea.setEditable(true);
		resetFileText();
	}
	
	public void resetFileText(){
		String s = "";
		fileTextPane.getTextArea().setText(s);
	}
	
	public void disableTopPanel(){
		this.fileTextPane.setEnabled(false);
		this.fileTextPane.setBackground(Color.LIGHT_GRAY);
	}
	public void disableBotPanel(){
		this.resultsTextArea.setEnabled(false);
		this.resultsTextArea.setBackground(Color.LIGHT_GRAY);
	}
	
	public void enableTopPanel(){
		this.fileTextPane.setEnabled(true);
		this.fileTextPane.setBackground(Color.WHITE);
	}
	public void enableBotPanel(){
		this.resultsTextArea.setEnabled(true);
		this.resultsTextArea.setBackground(Color.WHITE);
	}

	public void setTopText(String content) {
		this.fileTextPane.getTextArea().setText(content);
		
	}

	public void setBotText(String content) {
		this.resultsTextArea.setText(content);	
	}



	
	
	
	

}
