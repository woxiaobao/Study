package com.justcode.ns;

/**
 * 启动类
 * @author justcode
 * QQ 709255751
 */
public class StartServer {

	public static void main(String[] args) throws Exception {
		WSS wss = new WSS();
		new Thread(wss).start();
		//TCPS tcps = new TCPS();
		//new Thread(tcps).start();
	}

}
