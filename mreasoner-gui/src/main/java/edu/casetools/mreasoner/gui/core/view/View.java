package edu.casetools.mreasoner.gui.core.view;

import javax.swing.JPanel;

import edu.casetools.mreasoner.configurations.data.MConfigurations;
//import javax.swing.UIManager;
import edu.casetools.mreasoner.gui.core.view.window.MainWindow;




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
