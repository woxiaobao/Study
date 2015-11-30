package com.lv.url;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressD {
	//获取本地的ip地址
	public static String getHostIp(){
		InetAddress ia;
		String ip=null;
		try {
			ia=InetAddress.getLocalHost();
			ip=ia.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ip;
		
	}
	//获取本地的主机名
		public static String getHost(){
			InetAddress ia;
			String host=null;
			try {
				ia=InetAddress.getLocalHost();
				host=ia.getHostName();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return host;
			
		}
}
