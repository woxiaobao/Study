package com.lv.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RetrivePage {
	public static String downloadPage(String path){
		StringBuilder pageBuffer=null;
		try {
			URL pageURL=new URL(path);
			System.out.println("URL的授权="+pageURL.getAuthority());
			System.out.println("URL的内容="+pageURL.getContent());
			System.out.println("URL的文件名="+pageURL.getFile());
			System.out.println("URL的主机名="+pageURL.getHost());
			BufferedReader reader=new BufferedReader(new InputStreamReader(pageURL.openStream(),"UTF-8"));
			String line;
			pageBuffer=new StringBuilder();
			while((line=reader.readLine())!=null){
				pageBuffer.append(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBuffer.toString();
	}
	
	public static String downloadPageScanner(String path){
		StringBuilder pageBuffer=null;
		try {
			URL pageURL=new URL(path);
			Scanner scanner=new Scanner(new InputStreamReader(pageURL.openStream(),"utf-8"));
			scanner.useDelimiter("\\z");
			pageBuffer=new StringBuilder();
			while(scanner.hasNext()){
				pageBuffer.append(scanner.next());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBuffer.toString();
	}



}
