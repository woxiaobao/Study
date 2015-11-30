package com.lv.design.template;

public abstract class Benchmark {
	/**
	 * 下面操作是我们希望在子类中完成
	 */
	public abstract void benchmark();
	/**
	 * 重复执行benchmark次数
	 * @param count
	 * @return
	 */
	public final long repeat (int count) {
		if(count==0){
			return 0;
		}else{
			long start=System.currentTimeMillis();
			for(int i=0;i<count;i++){
				benchmark();
			}
			long stop=System.currentTimeMillis();
			return start-stop;
		}
	}
		

}
