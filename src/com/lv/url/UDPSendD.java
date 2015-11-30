package com.lv.url;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSendD {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//UDP的发送端
		System.out.println("发送端启动…………");
		/*
		 * 创建UDP传输的发送端。
		 * 思路：
		 * 1，建立udp的socket服务。
		 * 2，将要发送的数据封装到数据包中。 
		 * 3，通过udp的socket服务将数据包发送出去。
		 * 4，关闭socket服务。
		 */
		//1,udpsocket服务。使用DatagramSocket对象。
		DatagramSocket ds = new DatagramSocket(8888);
		
		//2,将要发送的数据封装到数据包中。
		String str = "udp传输演示：哥们来了！";
			//使用DatagramPacket将数据封装到的该对象包中。
		byte[] buf = str.getBytes();
		
		DatagramPacket dp = 
				new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.3.78"),10001);
		
		
		//3，通过udp的socket服务将数据包发送出去。使用send方法。
		ds.send(dp);
		
		//4，关闭资源。
		ds.close();
	}
}
