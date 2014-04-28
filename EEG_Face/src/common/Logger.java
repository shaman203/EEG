package common;

import gui.ILogViewer;

public class Logger {
	
	private boolean logToFile;
	private String logfile;
	private ILogViewer viewer;
	
	public Logger(String logFile, ILogViewer viewer)
	{
		this.viewer = viewer;
		this.logToFile = true;
		this.logfile = logFile;
	}
	
	public void setLogFile(String logfile)
	{
		this.logfile = logfile;
	}
	
	public void saveLogToFile(boolean save)
	{
		this.logToFile = save;
		//TODO close previous file
	}
	
	public void log(String msg)
	{
		this.viewer.viewMessage(msg);
		if(logToFile)
			addToFile(msg);
	}

	private void addToFile(String msg) {
		// TODO Auto-generated method stub
		
	}

	
}
