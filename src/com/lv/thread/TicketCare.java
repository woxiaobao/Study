package com.lv.thread;

public class TicketCare implements Runnable {

	private int num=100;
	Object obj=new Object();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			synchronized(obj){
				if(num>0)
				{
					try{Thread.sleep(150);}catch (InterruptedException e){}
					
					System.out.println(Thread.currentThread().getName()+".....sale...."+num--);
				}
			}
			
		}
		
	}

}
