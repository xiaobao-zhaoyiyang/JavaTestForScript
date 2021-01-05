package com.zhaoqiang.internetUtils;

import java.io.IOException;

import com.zhaoqiang.bean.JavaBean;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ReadDataFromServer {
	private Response response;

	public String connectServerByGet(String url) {
		try {
			Headers headers = JavaBean.headers;
			Request request = new Request.Builder().url(url).headers(headers).get().build();
			response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request).execute();

			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
