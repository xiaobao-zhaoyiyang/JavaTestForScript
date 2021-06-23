package com.zhaoqiang.bean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;

public class JavaBean {

	// 文件地址
	public static final String FILE_PATH = "/Users/zhaoqiang/Documents/MyFiles/上架课程.xlsx";

	// 读取表格下标
	public static final int INDEX_OF_SHEET = 2;

	// 标题
	public static HashMap<String, String> titleMap = new HashMap<>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("id", "id");
			put("课程名称", "title");
			put("亮点缩略", "sub_title");
			put("图虫用户名称", "name");
			put("图虫id", "site_id");
			put("圈子id", "tag_id");
			put("圈子名称", "tag_name");
			put("秒杀价", "price");
			put("原价", "original_price");
			put("课时", "course_count");
			put("课程类别", "");
			put("apple product id", "apple_product_id");
		}
	};

	// 用户名
	public static String USERNAME = null;
	// 密码
	public static String PASSWORD = null;

	/**
	 * 生成Header
	 * TOKEN 用户的token值
	 * UID 用户的uid值
	 */
	public static String TOKEN = null;
	public static String UID = null;
	private static Map<String, String> headerMap = new HashMap<>() {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		{
			put("scheme", "https");
			put("accept", "application/json, text/plain, */*");
			put("x-requested-with", "XMLHttpRequest");
//			put("accept-encoding", "gzip, deflate");
			put("accept-language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7");
			put("cookie", "webp_enabled=1, "
					+ "token=" + TOKEN + ","
					+ "lang=zh, " + "PHPSESSID=inejn5iuengq7muiqos0eeuaef, " + "_ga=GA1.2.1056662492.1606553476,"
					+ " _gid=GA1.2.304345705.1606553476, " + "MONITOR_WEB_ID=49b75dbd-30e5-49b8-b40f-1fc031cb4a7a, "
					+ "_gat=1");
			put("cookie", "token=" + TOKEN);
			put("content-type", "application/json");
			put("content-encoding", "gzip");
		}

	};
	public static Headers headers = Headers.of(headerMap);
}
