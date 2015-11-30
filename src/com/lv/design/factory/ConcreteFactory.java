package com.lv.design.factory;


public class ConcreteFactory extends Factory {
	/**设计模式之工厂模式factory
	 * 三个构成元素
	 * 1、工厂类
	 * 2、抽象产品
	 * 3、具体产品
	 * 
	 * 工厂模式是隐藏new的这个过程，是通过工厂的方式进行产生对象，主要的目的是更好的扩张和收缩
	 */

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IProduct> T createProduct(Class<T> c) {
		// TODO Auto-generated method stub
		T product = null; 
	      try { 
	        product = (T) Class.forName(c.getName()).newInstance();
	      } catch (Exception e) { 
	        e.printStackTrace(); 
	      } 
	      return product; 
	    
	}

}
