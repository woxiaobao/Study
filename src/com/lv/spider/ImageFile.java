package com.lv.spider;

import java.io.File;

public class ImageFile{
	
	//public static String WORKDIR = Configuration.getInstance().getProperty("path.workdir", "/tmp/workdir/store");

		public static String WORKDIR ="D:\\images";
		
		protected String name;
		
		public static File getInstance(String name) {
			File file = new File(WORKDIR + "/" + name);
//			File dir = file.getParentFile();
			if(!file.exists()) file.mkdirs();
			System.currentTimeMillis();
			File fileimg = new File(WORKDIR + "/" + name+"/"+System.currentTimeMillis()+".jpg");
			return fileimg;
		}
		
}
