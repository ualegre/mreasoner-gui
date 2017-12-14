package edu.casetools.icase.mreasoner.gui.view.panels.menu;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.casetools.icase.mreasoner.configs.data.MConfigs;





public class MainMenu extends JMenuBar{
	private JFileChooser 	    fileChooser;
	private static final long serialVersionUID = 1L;
	private JMenu fileMenu,systemSpecificationMenu,resultLogMenu,translatorMenu,configsMenu,exportMenu;
	private JButton startTest,stopTest;
	private JMenuItem newSSMenu,loadSSMenu,saveSSMenu,saveAsSSMenu,saveResultLog,
					  saveResultLogAs,loadFile,translate,clear,newConfigs,loadConfigs,
					  saveConfigs,saveConfigsAs,exportToNuSMV;
	public enum FILETYPE {CONF,SMV,MTPL, LFPUBS};
	public enum FILECHOOSER {OPEN,SAVE};

	//private String fileName,resultsPath,configsPath,LFPUBSFileName;


	
	public MainMenu(MConfigs configs){
        
       fileMenu        			= new JMenu("Main Menu");
	   systemSpecificationMenu  = new JMenu("System Specification File");
	   resultLogMenu   			= new JMenu("System Results Log");
	   translatorMenu  			= new JMenu("LFPUBS Output File");
	   configsMenu              = new JMenu("Session");
	   exportMenu				= new JMenu("Export");
       newSSMenu		   	 	= new JMenuItem("New File");
       loadSSMenu        		= new JMenuItem("Load File");
       saveSSMenu        		= new JMenuItem("Save File");
       saveAsSSMenu 	    	= new JMenuItem("Save File As");
       exportToNuSMV        	= new JMenuItem("Export to NuSMV");
       saveResultLog   			= new JMenuItem("Save Result");
       saveResultLogAs 			= new JMenuItem("Save Result As");
       loadFile        			= new JMenuItem("Load LFPUBS Output File");
       translate 	   			= new JMenuItem("Translate LFPUBS Rules");
       clear                    = new JMenuItem("Clear LFPUBS Rule Translations Tab");
       newConfigs				= new JMenuItem("New Session");
       loadConfigs				= new JMenuItem("Load Session");
       saveConfigs 				= new JMenuItem("Save Session");
       saveConfigsAs 		    = new JMenuItem("Save Session As");

 
       
//       fileMenu.add(newMenu);
//       fileMenu.add(loadMenu);
//       fileMenu.add(saveMenu);
//       fileMenu.add(saveAsMenu);
//       fileMenu.addSeparator();
       resultLogMenu.add(saveResultLog);
       resultLogMenu.add(saveResultLogAs);
        
       systemSpecificationMenu.add(newSSMenu);
       systemSpecificationMenu.add(loadSSMenu);
       systemSpecificationMenu.add(saveSSMenu);
       systemSpecificationMenu.add(saveAsSSMenu);
       systemSpecificationMenu.add(exportMenu);
       
       translatorMenu.add(loadFile);
       translatorMenu.addSeparator();
       translatorMenu.add(translate);
       translatorMenu.add(clear);
       
       configsMenu.add(newConfigs);
       configsMenu.add(loadConfigs);
       configsMenu.add(saveConfigs);
       configsMenu.add(saveConfigsAs);
       
       startTest = new JButton("Start");
       startTest.setFocusable(false);
       
       stopTest = new JButton("Stop");
       stopTest.setFocusable(false);
       stopTest.setEnabled(false);
       
       fileMenu.add(configsMenu);
       fileMenu.addSeparator();
       fileMenu.add(systemSpecificationMenu);
       fileMenu.add(resultLogMenu);
       fileMenu.add(translatorMenu);

       exportMenu.add(exportToNuSMV);


       this.add(fileMenu);
       this.add(startTest);
       this.add(stopTest);
      
       this.enableButtons(false);
       this.enableResultsButtons(false);
       this.enableTranslateButtons(false);
       this.enableConfigurationButtons(false);
       
	}
	
	public void enableConfigurationButtons(boolean b) {
		this.saveConfigs.setEnabled(b);
		
	}

	public void enableTranslateButtons(boolean b) {
		this.translate.setEnabled(b);
		
	}

	public void enableButtons(boolean enable){
		this.saveSSMenu.setEnabled(enable);
		this.startTest.setEnabled(enable);

	}
	
	public void enableStopButton(boolean enable){
		this.stopTest.setEnabled(enable);
	}
	
	public void enableSaveResultsLogAs(){
		this.saveResultLogAs.setEnabled(true);
	}
	
	
	public void enableSaveResultsLog(){
		this.saveResultLog.setEnabled(true);
	}
	
	public void enableResultsButtons(boolean enable){
		this.saveResultLog.setEnabled(enable);
		this.saveResultLogAs.setEnabled(enable);
	}
	
	public void addActionListener(ActionListener actionListener){

		this.newSSMenu.addActionListener(actionListener);
		this.loadSSMenu.addActionListener(actionListener);
		this.saveSSMenu.addActionListener(actionListener);
		this.saveAsSSMenu.addActionListener(actionListener);
		this.exportToNuSMV.addActionListener(actionListener);
		this.saveResultLog.addActionListener(actionListener);
		this.startTest.addActionListener(actionListener);
		this.stopTest.addActionListener(actionListener);
		this.saveResultLogAs.addActionListener(actionListener);
		this.loadFile.addActionListener(actionListener);
		this.translate.addActionListener(actionListener);
		this.clear.addActionListener(actionListener);
		this.newConfigs.addActionListener(actionListener);
		this.loadConfigs.addActionListener(actionListener);
		this.saveConfigs.addActionListener(actionListener);
		this.saveConfigsAs.addActionListener(actionListener);
	}
	
//	public String displayOpenFileChooser(){
//		this.fileChooser = new JFileChooser(fileName);
//		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//		int fileChooserVal = fileChooser.showOpenDialog(this);
//		 if(fileChooserVal == JFileChooser.APPROVE_OPTION) {
//			fileName        = fileChooser.getSelectedFile().getAbsolutePath();
//			return fileChooser.getSelectedFile().getAbsolutePath();
//		 }else return null;
//	}
//	public String displayOpenLFPUBSFileChooser(){
//		this.fileChooser = new JFileChooser(fileName);
//		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//		int fileChooserVal = fileChooser.showOpenDialog(this);
//		 if(fileChooserVal == JFileChooser.APPROVE_OPTION) {
//			 LFPUBSFileName        = fileChooser.getSelectedFile().getAbsolutePath();
//			return fileChooser.getSelectedFile().getAbsolutePath();
//		 }else return null;
//	}
	
//	public void resetLFPUBSFilename(){
//		LFPUBSFileName = "";
//	}
//	
//	public String getLFPUBSFileName() {
//		return LFPUBSFileName;
//	}
//	
//	public void setLFPUBSFileName(String LFPUBSFileName) {
//		this.LFPUBSFileName = LFPUBSFileName;
//	}
//	
//	public void resetFilename(){
//		fileName = "";
//	}
//	
//	public String getResultsPath() {
//		return resultsPath;
//	}
//	
//	public void setResultsPath(String resultsPath) {
//		this.resultsPath = resultsPath;
//	}
//	
//	public String getFileName() {
//		return fileName;
//	}
//	
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	
//	public void setConfigsPath(String configsPath){
//		this.configsPath = configsPath;
//	}
//	
//	public String getConfigsPath() {
//		return configsPath;
//	}
	
//	public String displaySaveFileChooser(){
//		this.fileChooser = new JFileChooser(fileName);
//		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		int fileChooserVal = fileChooser.showSaveDialog(this);
//		 if(fileChooserVal == JFileChooser.APPROVE_OPTION) {
//		//	savePath	= saveFileChooser.getCurrentDirectory().getPath();
//			fileName        = fileChooser.getSelectedFile().getAbsolutePath();
//			
//			return fileChooser.getSelectedFile().getAbsolutePath();
//		 }else return null;
//	}
//	
//	public String displaySaveConfigsFileChooser(){
//		this.fileChooser = new JFileChooser(configsPath);
//		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//		int fileChooserVal = fileChooser.showSaveDialog(this);
//		 if( fileChooserVal == JFileChooser.APPROVE_OPTION ) {
//		     //	savePath	= saveFileChooser.getCurrentDirectory().getPath();
//			 configsPath        = fileChooser.getSelectedFile().getAbsolutePath();			
//			 return fileChooser.getSelectedFile().getAbsolutePath();
//		 }else return null;
//	}
	
//	public String displayLoadFileChooser(String actualPath){
//		this.fileChooser = new JFileChooser(actualPath);
//		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//		int fileChooserVal = fileChooser.showOpenDialog(this);
//		 if(fileChooserVal == JFileChooser.APPROVE_OPTION) {
//		//	 configsPath        = fileChooser.getSelectedFile().getAbsolutePath();
//			return fileChooser.getSelectedFile().getAbsolutePath();
//		 }else return null;
//	}
	
	public String displayFileChooser(String currentDirectory,FILECHOOSER type, FILETYPE extension){
		fileChooser = new JFileChooser(currentDirectory);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(getFileFilter(extension));
		int fileChooserVal = showFileChooser(type);
		 if(fileChooserVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		 }else return null;
	}

	private int showFileChooser(FILECHOOSER type) {
		switch(type){
			case OPEN:
				return fileChooser.showOpenDialog(this);
			case SAVE:
				return fileChooser.showSaveDialog(this);
			default:
				return -1;
		}
	}

	private FileNameExtensionFilter getFileFilter(FILETYPE extension) {
		switch(extension){
			case CONF:
				return new FileNameExtensionFilter("M Config File", new String[] {"mses","txt"});
			case SMV:
				return new FileNameExtensionFilter("NuSMV File", new String[] {"smv","txt"});
			case MTPL:
				return new FileNameExtensionFilter("M File", new String[] {"mtpl","txt"});
			case LFPUBS:
				return new FileNameExtensionFilter("LFPUBS File", new String[] {"txt"});
			default:
				return null;
		}
		
	}
	


}
