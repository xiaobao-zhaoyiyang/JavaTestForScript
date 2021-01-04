package com.zhaoqiang.javaTest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class AnalysisJson {
	public List<Object> AnalysisJsonFromUrl(String response, List<String> content){
		List<Object> data_url = new ArrayList<>();
		JSONObject jsonObject = new JSONObject(response);
		JSONObject course_group = jsonObject.getJSONObject("course_group");
		JSONObject site = course_group.getJSONObject("site");
		JSONArray tags = course_group.getJSONArray("tags");
		for (int i = 0; i < content.size(); i++) {
			String title = (String) content.get(i);
			if(title == "name" || title == "site_id") {
				data_url.add((String) site.get(title));
			}else if (title == "tag_id" || title == "tag_name") {
				data_url.add((String) tags.getJSONObject(0).get(title));
			}else if (title == "price" || title == "original_price") {
				int result = (int) course_group.get(title);
				data_url.add(String.valueOf((result / 100)));
			}else if (title == "") {
				data_url.add("");
			}
			else {
				data_url.add(String.valueOf(course_group.get(title)));
			}
		}
		
		return data_url;
	}
}
