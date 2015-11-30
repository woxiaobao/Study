package com.lv.thread;

public class ThreadE extends Thread{
	public void run(){
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是线程ThreadE="+this.getName()+"打印="+i);
		}
	}
}
