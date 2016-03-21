package com.lv.token;

public class StringD {
	public static void main(String args[]) {
        //String str = new String("amigoxiexiexingxing");
        //System.out.println("原始：" + str);
		StringD.sjint();
		StringD.sjdouble();
    }
	
	public static void stringTest(){
		String str=Token.getToken(66);
		char ch=str.charAt(5);	//返回指定索引处的 char 值。
		int cpa=str.codePointAt(5);	//返回指定索引处的字符（Unicode 代码点）。
		int hs=str.hashCode(); 	//返回此字符串的哈希码。
		int io=str.indexOf("#");	//返回指定子字符串在此字符串中第一次出现处的索引。
		System.out.println("str的长度="+str.length());
		String[] sp=str.split("#");	//根据给定正则表达式的匹配拆分此字符串。
		String nstr=str.substring(5,15);	//返回一个新字符串，它是此字符串的一个子字符串。
		char[] tca=str.toCharArray();	//将此字符串转换为一个新的字符数组。
		String tlc=str.toLowerCase();	//使用默认语言环境的规则将此 String 中的所有字符都转换为小写
		String tup=str.toUpperCase();	//使用默认语言环境的规则将此 String 中的所有字符都转换为大写。
		String trimstr=str.trim();		//返回字符串的副本，忽略前导空白和尾部空白。
		System.out.println(String.valueOf(10)); //返回 float 参数的字符串表示形式。
		
		System.out.println("返回指定索引处的 char 值="+ch);
		System.out.println("返回指定索引处的字符（Unicode 代码点）="+cpa);
		System.out.println("返回此字符串的哈希码="+hs);
		System.out.println("返回指定子字符串在此字符串中第一次出现处的索引="+io);
		System.out.println("根据给定正则表达式的匹配拆分此字符串="+sp);
		System.out.println("返回一个新字符串，它是此字符串的一个子字符串="+nstr);
		System.out.println("将此字符串转换为一个新的字符数组="+tca);
		System.out.println("所有字符都转换为小写="+tlc);
		System.out.println("所有字符都转换为大写="+tup);
		System.out.println("返回字符串的副本，忽略前导空白和尾部空白="+trimstr);
		
	}
	
	
	public static void sjint(){
		long old=System.currentTimeMillis();
		int n=12327;
		for(int i=0;i<1000000;i++){
			n=n*80/100;
		}
		long now=System.currentTimeMillis();
		System.out.println(now-old);
	}
	
	public static void sjdouble(){
		long old=System.currentTimeMillis();
		Double n=12327.00;
		for(int i=0;i<1000000;i++){
			n=n*80/100;
		}
		long now=System.currentTimeMillis();
		System.out.println(now-old);
	}
}
