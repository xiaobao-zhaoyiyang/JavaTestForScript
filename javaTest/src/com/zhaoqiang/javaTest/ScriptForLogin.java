package com.zhaoqiang.javaTest;

import java.io.IOException;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.Headers;
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
	private String path_login = "https://api.tuchong.com/accounts/login";
	private String path_captcha = "https://api.tuchong.com/captcha/image";
	private boolean isCaptcha = false;
	public void login() {
		try {
			RequestBody body = null;
			if (!isCaptcha) {
				body = new FormBody.Builder()
				.add("account", "我和你一起看")
				.add("password", "abc@123")
				.build();
			}else {
				body = new FormBody.Builder()
						.add("account", "我和你一起看")
						.add("password", "abc@123")
						.add("captcha_id", "")
						.add("captcha_token", "")
						.build();
			}
					
			Request request_login = new Request.Builder().url(path_login).post(body).build();
			
			Response response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request_login).execute();
			String content = response.body().string();
			System.out.println("login: " + content);
			JSONObject object = new JSONObject(content);
			String result = object.getString("result");
			if ("SUCCESS".equals(result)) {
				JavaBean.UID = object.getString("identity");
				JavaBean.TOKEN = object.getString("token");
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
	
	private void requestCaptcha() {
		try {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			RequestBody body = RequestBody.create(JSON, "");
			Request request_captcha = new Request.Builder().url(path_captcha).post(body).build();
			Response response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request_captcha).execute();
			String content = response.body().string();
			JSONObject object = new JSONObject(content);
			System.out.println("captcha: " + object);
			String imageString = object.getString("captchaBase64");
			System.out.println("base64:" + imageString);
			boolean is = imageString.endsWith("=");
			if (imageString.endsWith("=")) {
				imageString = imageString.substring(0, imageString.length()-1);
			}else if (imageString.endsWith("==")) {
				imageString = imageString.substring(0, imageString.length()-2);
			}
			System.out.println("sub:" + imageString);
			Base64AndImage.GenerateImage(imageString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
