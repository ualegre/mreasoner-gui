package edu.casetools.icase.mreasoner.gui.view.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;
import edu.casetools.icase.mreasoner.gui.view.panels.MainPanel;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;

	public MainWindow(MConfigs configs) {
		mainPanel = new MainPanel(configs);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setContentPane(mainPanel);	
		new Console(this);
		this.setSystemLookAndFeel();
		this.iconConfigs();
		this.setWindowConfigs();
	}
	
	private void iconConfigs() {
	    ImageIcon img = new ImageIcon(".\\icons\\main.png");
	    this.setIconImage(img.getImage());
		
	}

	private void setWindowConfigs(){
		this.setTitle("M Specification File Editor - New File");
		this.setWindowBounds();
		//
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setWindowBounds(){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)(screenSize.width/2),(int)(screenSize.height/2));
		int x=(int)(frameSize.width/2);
		int y=(int)(frameSize.height/2);
		this.setBounds(x,y,frameSize.width,frameSize.height);
	}

	
	public MainPanel getMainPanel() {
		return mainPanel;
	}

	
}
