package edu.casetools.icase.mreasoner.gui.view.window;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class Console extends WindowAdapter implements WindowListener, ActionListener, Runnable
{
	private MainWindow mainWindow;
	//private JTextArea textArea;
	private Thread reader;
	private Thread reader2;
	private boolean quit;
					
	private final PipedInputStream pin  = new PipedInputStream(); 
	private final PipedInputStream pin2 = new PipedInputStream(); 

	Thread errorThrower; // just for testing (Throws an Exception at this Console
	
	public Console(MainWindow mainWindow)
	{
		this.mainWindow = mainWindow;

		
		mainWindow.addWindowListener(this);		
		
		try
		{
			PipedOutputStream pout=new PipedOutputStream(this.pin);
			System.setOut(new PrintStream(pout,true)); 
		} 
		catch (java.io.IOException io)
		{
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("Couldn't redirect STDOUT to this console\n"+io.getMessage());
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
		}
		catch (SecurityException se)
		{
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("Couldn't redirect STDOUT to this console\n"+se.getMessage());
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
	    } 
		
		try 
		{
			PipedOutputStream pout2=new PipedOutputStream(this.pin2);
			System.setErr(new PrintStream(pout2,true));
		} 
		catch (java.io.IOException io)
		{
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("Couldn't redirect STDERR to this console\n"+io.getMessage());
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
		}
		catch (SecurityException se)
		{
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("Couldn't redirect STDERR to this console\n"+se.getMessage());
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
	    } 		
			
		quit=false; // signals the Threads that they should exit
				
		// Starting two seperate threads to read from the PipedInputStreams				
		//
		reader=new Thread(this);
		reader.setDaemon(true);	
		reader.start();	
		//
		reader2=new Thread(this);	
		reader2.setDaemon(true);	
		reader2.start();
							
	}
	
	public synchronized void windowClosed(WindowEvent evt)
	{
		quit=true;
		this.notifyAll(); // stop all threads
		try { reader.join(1000);pin.close();   } catch (Exception e){}		
		try { reader2.join(1000);pin2.close(); } catch (Exception e){}
		System.exit(0);
	}		
	
	public synchronized void actionPerformed(ActionEvent evt)
	{
		mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().setText("");
		mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
	}

	public synchronized void run()
	{
		try
		{			
			while (Thread.currentThread()==reader)
			{
				try { this.wait(100);}catch(InterruptedException ie) {}
				if (pin.available()!=0)
				{
					String input=this.readLine(pin);
					mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append(input);
					mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
				}
				if (quit) return;
			}
		
			while (Thread.currentThread()==reader2)
			{
				try { this.wait(100);}catch(InterruptedException ie) {}
				if (pin2.available()!=0)
				{
					String input=this.readLine(pin2);
					mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append(input);
					mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
				}
				if (quit) return;
			}			
		} catch (Exception e)
		{
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("\nConsole reports an Internal error.");
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().getResultsTextArea().append("The error is: "+e);	
			mainWindow.getMainPanel().getSystemSpecificationEditorPanel().refreshMidPanel();
		}
		
		// just for testing (Throw a Nullpointer after 1 second)
		if (Thread.currentThread()==errorThrower)
		{
			try { this.wait(1000); }catch(InterruptedException ie){}
			throw new NullPointerException("Application test: throwing an NullPointerException It should arrive at the console");
		}

	}
	
	public synchronized String readLine(PipedInputStream in) throws IOException
	{
		String input="";
		do
		{
			int available=in.available();
			if (available==0) break;
			byte b[]=new byte[available];
			in.read(b);
			input=input+new String(b,0,b.length);														
		}while( !input.endsWith("\n") &&  !input.endsWith("\r\n") && !quit);
		return input;
	}	
		
		
}