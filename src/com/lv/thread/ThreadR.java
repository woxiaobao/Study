package com.lv.thread;

public class ThreadR implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("我是线程ThreadR="+Thread.currentThread().getName()+"打印="+i);
			
			//线程死循环
//			while(true){
//			
//			
//			}
		}
	}

}
