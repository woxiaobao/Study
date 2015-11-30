package com.lv.thread;

/*有一个买票的系统，多人同时进行买票，一共有100张票
 * 这个是没有没有加线程安全锁的，在买票的最后能够买到负数
 * */
public class Ticket implements Runnable {
	private int num=100;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(num>0)
			{
				try{Thread.sleep(50);}catch (InterruptedException e){}
				
				System.out.println(Thread.currentThread().getName()+".....sale...."+num--);
			}
		}
		
	}

}
