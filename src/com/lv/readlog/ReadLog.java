package com.lv.readlog;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadLog {
	
	public static void log() {
		FileReader fr;
		try {
			fr = new FileReader("logs/info.log");
			BufferedReader bufr = new BufferedReader(fr);

			String line = null;
			int i = 0;
			while ((line = bufr.readLine()) != null) {
				//System.out.println(line);
				String[] data=line.split(" - ");
				String content=data[1];
				String url=data[2];
				if (url.startsWith("http://")) {
					i++;
					System.out.println("url="+url);
				}
				
			}
			System.out.println("i=" + i);
			bufr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
