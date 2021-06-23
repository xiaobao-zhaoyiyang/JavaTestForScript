package com.zhaoqiang.script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zhaoqiang.bean.JavaBean;
import com.zhaoqiang.internetUtils.OKHTTPClientBuilder;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class ScriptForDeleteData {

	// 用于存放作品的post_id
//	private List<String> post_id = new ArrayList<>();
	private List<HashMap<String, String>> post_id = new ArrayList<>();
	private List<JSONObject> post = new ArrayList<>();
	private String getDataFromUrl(String url, String type) {
		try {
			System.out.println("token = " + JavaBean.TOKEN);
			Request request = null;
			if (type.equals("GET")) {
				request = new Request.Builder().url(url)
						.header("token", JavaBean.TOKEN)
						.header("platform", "android")
						.get().build();
			} else if (type.equals("DELETE")) {
				request = new Request.Builder().url(url)
						.header("token", JavaBean.TOKEN)
						.header("platform", "android")
						.delete().build();
			}

			Response response = OKHTTPClientBuilder.builderOKHttpClientBuilder().build().newCall(request).execute();

			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void AnalysisPostId(String uid) {
		// 通过网页形式获取图文博数据
//		String result = getDataFromUrl("https://tuchong.com/rest/2/sites/" + uid + "/posts", "GET");
//		System.out.println(result);
//		JSONObject object = new JSONObject(result);
//		JSONArray post_list = object.getJSONArray("post_list");
//		for (int i = 0; i < post_list.length(); i++) {
//			JSONObject entry = post_list.getJSONObject(i);
//			String post_id = entry.getString("post_id");
////			String type = entry.getString("type");
//			this.post_id.add(post_id);
//		}
//		System.out.println(post_id);
		
		// 通过app方式获取我的作品数据
		String result = getDataFromUrl("https://api.tuchong.com/sites/" + uid + "/works", "GET");
		System.out.println(result);
		JSONObject object = new JSONObject(result);
		int count = object.getInt("count");
		if (count != 0) {
			JSONArray work_list = object.getJSONArray("work_list");
			for (int i = 0; i < work_list.length(); i++) {
				JSONObject jsonObject = work_list.getJSONObject(i);
				String type = jsonObject.getString("type");
				JSONObject entry = jsonObject.getJSONObject("entry");
				String post_id = null;
				if (type.equals("post")) {
					post_id = entry.getString("post_id");
				} else if (type.equals("video") || type.equals("new_film")) {
					post_id = entry.getString("vid");
				}
				boolean is_self = false;		
				if ("new_film".equals(type)) {
					is_self = entry.getBoolean("is_self");
				}
//				try {
//					is_self = entry.getBoolean("is_self");
//					System.out.println(i + "..is_self:get取值" + is_self);
//				} catch (Exception e) {
//					// TODO: handle exception
//					System.out.println(i + "..is_self:默认值" + is_self);
//				}
				System.out.println(i + "..is_self:" + is_self);
				boolean is_top = entry.optBoolean("is_top");
				System.out.println(i + "..is_top:" + is_top);
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("post_id", post_id);
				jsonObject2.put("type", type);
				jsonObject2.put("is_top", is_top);
				jsonObject2.put("is_self", is_self);
				HashMap<String, String> map = new HashMap<>();
				map.put(post_id, type);
				this.post_id.add(map);
				post.add(jsonObject2);
			}
		}
		System.out.println(post_id);
	}

	/**
	 * 删除单一数据，即根据作品id删除一个作品
	 * @param index 删除作品的下标
	 */
	public void deleteData(int index) {
		// app获取数据后根据类型type不同调用不同的删除接口
//		HashMap<String, String> map = post_id.get(4);
//		Set<String> key = map.keySet();
//		Iterator<String> iterator = key.iterator();
		String id = null;
//		while (iterator.hasNext()) {
//			id = iterator.next();
//		}
		JSONObject object = post.get(index);
		System.out.println(object);
		id = object.getString("post_id");
		System.out.println(id + ":map");
//		String type = map.get(id);
		String type = object.getString("type");
		System.out.println(type + ":map");
		boolean is_top = object.getBoolean("is_top");
		System.out.println(is_top + ":map");
		if (!"film".equals(type) && is_top == false) {
			if ("post".equals(type)) {
				String result = getDataFromUrl("https://api.tuchong.com/posts/" + id, "DELETE");
				System.out.println("删除post：" + result);
			} else if (type.equals("video") || type.equals("new_film")) {
				String result = getDataFromUrl("https://api.tuchong.com/video/" + id, "DELETE");
				System.out.println("删除非post：" + result);
			}
		}else {
			System.out.println("不建议删除");
		}
		
		
		// 网页方式获取的图文博进行单一删除
//		String id = post_id.get(2);
//		String result = getDataFromUrl("https://tuchong.com/rest/posts/" + id, "DELETE");
//		System.out.println("删除post：" + result);

	}
	public void deleteDataAll() {
		for (int i = 0; i < post.size(); i++) {
			deleteData(i);
		}
	}
	
}
