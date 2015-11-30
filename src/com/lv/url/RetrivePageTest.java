package com.lv.url;


public class RetrivePageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="http://blog.csdn.net/qdujunjie/article/details/38701907";
		String path1="http://hao.360.cn/?src=bm";
		RetrivePage.downloadPage(path1);
		//String page=RetrivePage.downloadPageScanner(path1);
		//System.out.println(page);
	}

}
