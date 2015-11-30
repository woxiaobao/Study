package com.lv.url.chat;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatDemo {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		DatagramSocket send = new DatagramSocket();
		
		DatagramSocket rece = new DatagramSocket(10002);
		new Thread(new Send(send)).start();
		new Thread(new Rece(rece)).start();
	}

}
