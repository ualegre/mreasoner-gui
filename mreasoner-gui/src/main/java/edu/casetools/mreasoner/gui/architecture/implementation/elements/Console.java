package edu.casetools.mreasoner.gui.architecture.implementation.elements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends JFrame{

	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	
	JScrollPane scrollPane;
	
	public Console(String title){
		this.setTitle(title);
		textArea = new JTextArea(title+"\n");
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolas",Font.PLAIN,12));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);

		scrollPane = new JScrollPane(textArea);
		this.add(scrollPane);
		this.setBounds(600, 300, 600, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void addText(String text){
		textArea.append(text+"\n");
		textArea.setCaretPosition(textArea.getText().length() - 1);
	}
	
	
}
