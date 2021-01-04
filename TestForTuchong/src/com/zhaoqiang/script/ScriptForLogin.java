package com.zhaoqiang.script;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhaoqiang.bean.JavaBean;
import com.zhaoqiang.imageUtils.BaiduOcr;
import com.zhaoqiang.internetUtils.OKHTTPClientBuilder;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScriptForLogin {
	
	/**
	 * https://api.tuchong.com/accounts/login
	 * account	LUPoy8EjLs4NVuUCU3vzVw==
	 * password	abc@123
	 * 
	 * account	LUPoy8EjLs4NVuUCU3vzVw==
	 * captcha_id	s4AFfSseGP6p6Pks1aDs
	 * captcha_token	2433
	 * password	abc@123
	 * 
	 * https://api.tuchong.com/captcha/image
	 * 
	 */
	private static String path_login = "https://api.tuchong.com/accounts/login";
	private static String path_captcha = "https://api.tuchong.com/captcha/image";
	private static boolean isCaptcha = false;
	private static String captcha;
	private static String captchaId;
	private static String uidString;
	private static String tokenString;
	public static void login() {
		try {
			RequestBody body = null;
			if (!isCaptcha) {
				body = new FormBody.Builder()
				.add("account", JavaBean.USERNAME)
				.add("password", JavaBean.PASSWORD)
				.build();
			}else {
				body = new FormBody.Builder()
						.add("account", JavaBean.USERNAME)
						.add("password", JavaBean.PASSWORD)
						.add("captcha_id", captchaId)
						.add("captcha_token", captcha)
						.build();
			}
					
			Request request_login = new Request.Builder().url(path_login).post(body)
					.addHeader("platform", "android")
					.build();
			
			Response response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request_login).execute();
			String content = response.body().string();
			System.out.println("login: " + content);
			JSONObject object = new JSONObject(content);
			String result = object.getString("result");
			if ("SUCCESS".equals(result)) {
				uidString = object.getString("identity");
				if(object.has("token"))
					tokenString = object.getString("token");
				JavaBean.UID = uidString;
				JavaBean.TOKEN = tokenString;
				isCaptcha = false;
				System.out.println(JavaBean.UID + ",,,," + JavaBean.TOKEN);
			}else if ("ERROR".equals(result)) {
				int code = object.getInt("code");
				if (code == 11) {
					requestCaptcha();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void requestCaptcha() {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(JSON, "");
			Request request_captcha = new Request.Builder().url(path_captcha).post(body).build();
			Response response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request_captcha).execute();
			String content = response.body().string();
			JSONObject object = new JSONObject(content);
			System.out.println("captcha: " + object);
			captchaId = object.getString("captchaId");
			String imageString = object.getString("captchaBase64");
			System.out.println("base64:" + imageString);
			if (imageString != null || imageString != "") {
				String imgStr = imageString.substring(21, imageString.length());
				String result_captcha = BaiduOcr.accurateBasic(imgStr);
				JSONObject object_cpatcha = new JSONObject(result_captcha);
				JSONArray array = object_cpatcha.getJSONArray("words_result");
				JSONObject words = array.getJSONObject(0);
				captcha = words.getString("words");
				isCaptcha = true;
				login();
			}else{
				System.out.println("获取base64字符串失败");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
