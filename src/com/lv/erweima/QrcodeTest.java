package com.lv.erweima;

import java.io.IOException;

public class QrcodeTest {
	

	public String imgPath = System.getProperty("user.dir")
			+ "/WebRoot/upload/www1.png";
	public String IMGPATH = "D://images/1.png";
	public void testencoderQRCode() {

		String content = "http://www.baidu.com";
		int size = 7;
		QRCoder qr = new QRCoder();
		try {
			qr.encoderQRCode(content, IMGPATH, "png", size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testdecoderQRCode() {

		QRCoder qr = new QRCoder();
		String con = "";
		try {
			con = qr.decoderQRCode(IMGPATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(con);
	}
	
	public static void main(String[] args) {
		QrcodeTest t=new QrcodeTest();
		t.testdecoderQRCode();
	}
}
