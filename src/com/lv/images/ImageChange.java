package com.lv.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec; 
import com.sun.image.codec.jpeg.JPEGImageEncoder; 

@SuppressWarnings("restriction")
public class ImageChange {

	public static final String PATH="D://images";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChangeImage();
	}

	public static void ChangeImage() {
		FileOutputStream out = null;
		try {
			File file = new File(PATH+"/1.jpg"); // 读入文件
			Image src = javax.imageio.ImageIO.read(file); // 构造Image对象
			int wideth = src.getWidth(null);// 得到源图宽
			int height = src.getHeight(null); // 得到源图长
			BufferedImage tag = new BufferedImage(wideth / 2, height / 2,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics()
					.drawImage(src, 0, 0, wideth / 2, height / 2, null); // 绘制缩小后的图
			out = new FileOutputStream(PATH+"/newfile.jpg"); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag); // 近JPEG编码
			System.out.print(wideth + "*" + height);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
