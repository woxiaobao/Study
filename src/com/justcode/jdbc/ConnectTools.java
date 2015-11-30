package com.justcode.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectTools {
	private static Connection cn = null;
	private static String user = "root"; // 数据库用户
	private static String password = "123456"; // 数据库密码
	private static String url = "jdbc:mysql://localhost:3306/gift"; // 数据库路径

	// 连接数据库的方法
	public static Connection getDBConn() {
		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 得到数据库连接
			cn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	// 字符串转日期格式
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 当前时间
	public static String NowDate() {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss");
		Date date = new Date();
		String newDate=null;
		try {
			newDate = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static void main(String[] args) {
		getDBConn();
		System.out.println("1");
	}

}
