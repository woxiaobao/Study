package com.lv.token;

public class Token {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// char[] ch=new
		// char[]{'!','@','$','%','^','&','*','(',')','_','+','0','1','2','3','4','5','6','7','8','9','/','-','+','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		String token = getToken(23);
		System.out.println(token);
		token = getToken(23);
		System.out.println(token);
	}

	public static String getToken(int id) {
		// TODO Auto-generated method stub
		String token = "";
		char[] ch = new char[] { '!', '@', '$', '%', '^', '&', '*', '(', ')',
				'_', '+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'/', '-', '+', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int n = 1 + (int) (Math.random() * 25) + 10;
		int m = 1 + (int) (Math.random() * 25) + 10;
		for (int i = 0; i < n; i++) {
			int x = 1 + (int) (Math.random() * 75);
			token += ch[x];
		}
		token += "#" + id + "#";
		for (int i = 0; i < m; i++) {
			int x = 1 + (int) (Math.random() * 75);
			token += ch[x];
		}
		return token;
	}

	public static String getString() {
		// TODO Auto-generated method stub
		String str = "";
		char[] ch = new char[] { '!', '@', '$', '%', '^', '&', '*', '(', ')',
				'_', '+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'/', '-', '+', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		//int n = 1 + (int) (Math.random() * 25) ;
		int n=8;
		for (int i = 0; i < n; i++) {
			int x = 1 + (int) (Math.random() * 75);
			str += ch[x];
		}

		return str;
	}

}
