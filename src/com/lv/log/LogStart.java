package com.lv.log;

import java.io.IOException;

import com.lv.token.Token;



public class LogStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeCache.start();
		//testLog();
	}

	private static boolean loop = true;
	static String lineSeparator = System.getProperty("line.separator"); 
	
	public static void quit() {
		loop = false;
	}
	
	public static void WLog(String line) {
		// TODO Auto-generated method stub
		int date = TimeCache.date*100+TimeCache.hour;
		SimpleFileWriter logFile = getLogFile();
		if(TimeCache.date != date) {
			logFile.close();
			date = TimeCache.date*100+TimeCache.hour;
			logFile = getLogFile();
		}
		try {
			//System.out.println(line);
			logFile.write(line +""+lineSeparator);
		} catch (IOException e1) {
			
		}
	}

	private static void testLog() {
		// TODO Auto-generated method stub
		String line="";
		int date = TimeCache.date*100+TimeCache.hour;
		SimpleFileWriter logFile = getLogFile();
		int i=0;
		while (loop) {
			i++;
			if(i==1000)	quit();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			line=Token.getString();
			if(TimeCache.date != date) {
				logFile.close();
				date = TimeCache.date*100+TimeCache.hour;
				logFile = getLogFile();
			}
			try {
				//System.out.println(line);
				logFile.write(line +""+lineSeparator);
			} catch (IOException e1) {
			}
		}
	}

	private static SimpleFileWriter getLogFile() {
		// TODO Auto-generated method stub
		String filePath = String.format("data/%d/%02d.log", TimeCache.date, TimeCache.hour);
		SimpleFileWriter logFile = SimpleFileWriter.getInstance(filePath);
		return logFile;
	}

}
