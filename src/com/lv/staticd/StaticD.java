package com.lv.staticd;

public class StaticD {

	static{
		System.out.println("我是静态代码块，我优先于类加载，而且我只加载一次！");
	}
	
	{
		System.out.println("我是构造代码块，我的加载依赖于对象。");
	}
	StaticD(){
		System.out.println("我初始化了…………");
	}

}
