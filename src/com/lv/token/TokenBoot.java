package com.lv.token;

public class TokenBoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str=Token.getString();
		try {
			String test = "123456789";
			DesUtils des =DesUtils.getInstance(str);// 自定义密钥
			System.out.println("加密前的字符：" + test);
			System.out.println("加密后的字符：" + des.encrypt(test));
			System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));
			
			DesUtils mdes = DesUtils.getInstance();// 默认密钥
			System.out.println("默认加密前的字符：" + test);
			System.out.println("默认加密后的字符：" + mdes.encrypt(test));
			System.out.println("默认解密后的字符：" + mdes.decrypt(mdes.encrypt(test)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
