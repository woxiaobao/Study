package com.lv.bigDecimal;

import java.math.BigDecimal;

/**
 *
 * @author LvBaolin
 * @date: 2016年7月12日
 * @time: 上午10:41:00
 */

public class BigDecimalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}
	
	
	/**
	 * BigDecimal 由任意精度的整数非标度值 和32 位的整数标度 (scale) 组成。
	 * BigDecimal一共有4个构造方法
	 * 通常建议优先使用String构造方法。
		BigDecimal(int) 创建一个具有参数所指定整数值的对象。
		BigDecimal(double) 创建一个具有参数所指定双精度值的对象。
		BigDecimal(long) 创建一个具有参数所指定长整数值的对象。
		BigDecimal(String) 创建一个具有参数所指定以字符串表示的数值的对象。
		
		BigDecimal 的运算方式 不支持 + - * / 这类的运算 它有自己的运算方法
		BigDecimal add(BigDecimal augend) 加法运算
		BigDecimal subtract(BigDecimal subtrahend) 减法运算
		BigDecimal multiply(BigDecimal multiplicand) 乘法运算
		BigDecimal divide(BigDecimal divisor) 除法运算
	 */
	public static void test(){
		BigDecimal volumn = new BigDecimal("1");
	    volumn = volumn.add(new BigDecimal("2"));
	    
	    System.out.println(volumn);
	    //打印结果为：3
	    
	    volumn = volumn.divide(new BigDecimal("100"));
	    
	    System.out.println(volumn);
	    //打印结果为：0.03
	    
	    /**
	     * BigDecimal是通过使用compareTo(BigDecimal)来比较的
	     * 即左边比右边数大，返回1，相等返回0，比右边小返回-1
	     */
	    BigDecimal a = new BigDecimal("1");
	    BigDecimal b = new BigDecimal("2");
	    BigDecimal c = new BigDecimal("1");
	    int result1 = a.compareTo(b);
	    int result2 = a.compareTo(c);
	    int result3 = b.compareTo(a);
	    System.out.println(result1);
	    System.out.println(result2);
	    System.out.println(result3);
	    //打印结果是：-1、0、1
	}

}
