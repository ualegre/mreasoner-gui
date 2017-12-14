package edu.casetools.icase.mreasoner.gui.view;

import javax.swing.JPanel;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.gui.view.window.MainWindow;




public class View {
	
	MainWindow mainWindow;

	public enum PANEL {MAIN_PANEL,TEST_CASE_MGR};
	
	public View(MConfigs configs){
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
