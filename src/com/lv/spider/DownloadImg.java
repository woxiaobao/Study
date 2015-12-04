package com.lv.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.lv.log.TimeCache;

public class DownloadImg {

	public static void download(List<String> list) {
		String date = getTime();
		
		String filePath = String.format("data/%s", date);
		
		for (String imgUrl : list) {
			URL url;
			try {
				File file=ImageFile.getInstance(filePath);
				url = new URL(imgUrl);
				URLConnection uc = url.openConnection();
				InputStream is = uc.getInputStream();
				FileOutputStream out = new FileOutputStream(file);
				int i = 0;
				while ((i = is.read()) != -1) {
					out.write(i);
				}
				is.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static String getTime() {
    	long mts = System.currentTimeMillis();
    	
    	long time = mts/1000;
    	
    	Calendar cal = new GregorianCalendar();
    	cal.setTimeInMillis(mts);
    	
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH)+1;
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	int date = year*10000 + month*100 + day;
    	return date+""+hour;
    }


}
