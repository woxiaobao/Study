package com.lv.thread;


public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//两种不同的线程方法
//		ThreadE thread1=new ThreadE();
//		ThreadE thread2=new ThreadE();
//		ThreadE thread3=new ThreadE();
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		ThreadR thread4=new ThreadR();
//		new Thread(thread4).start();
		//没有安全锁
//		Ticket t=new Ticket();
//		new Thread(t).start();
//		new Thread(t).start();
//		new Thread(t).start();
//		new Thread(t).start();
		//有安全锁的方法
		TicketCare t=new TicketCare();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
	}

}
