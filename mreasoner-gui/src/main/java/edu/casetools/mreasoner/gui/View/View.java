package edu.casetools.mreasoner.gui.View;

import javax.swing.JPanel;

import edu.casetools.mreasoner.gui.View.Window.MainWindow;
import edu.casetools.mreasoner.input.configurations.MConfigurations;
//import javax.swing.UIManager;




public class View {
	
	MainWindow mainWindow;

	public enum PANEL {MAIN_PANEL,TEST_CASE_MGR};
	
	public View(MConfigurations configs){
		mainWindow = new MainWindow(configs);
		//setLookAndFeel();
		

	}
	
//	private void setLookAndFeel(){
//		try {
//			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//			} catch (Exception e) {
//			  }
//	}
	
	public void changePanel(JPanel panel){
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(panel);
	}

	

	
	public void closeMainWindow(){
		this.mainWindow.dispose();
	}
	
	

	public MainWindow getMainWindow(){
		return mainWindow;
	}
	
	
	
}
