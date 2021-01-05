package com.zhaoqiang.imageUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64AndImage {
	/**
	 * 图片转化成base64字符串
	 * 
	 * @return 转化后的字符串
	 */
	public static String GetImageStr() {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "D:\\tupian\\a.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码 
		Encoder encoder = Base64.getEncoder();
		//返回Base64编码过的字节数组字符串  
		return encoder.encodeToString(data);
	}

	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) {
			System.out.println("数据为空");
			return false; 
		} // 图像数据为空
		
			
		Decoder decoder = Base64.getMimeDecoder();
		try {
			// Base64解码
			byte[] b = decoder.decode(imgStr);
			System.out.println("b的长度： " + b.length);
//			for (int i = 0; i < b.length; ++i) {
//				if (b[i] < 0) {// 调整异常数据
//					b[i] += 256;
//				}
//			}
			System.out.println("调整后b的长度： " + b.length);
			// 生成jpeg图片
			String imgFilePath = "/Users/zhaoqiang/Desktop/temp.png";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			System.out.println("生成图片over");
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
