package com.zhaoqiang.javaTest;

import java.util.List;
import java.util.stream.Collectors;

public class CompaireTwoList {
	
	public boolean compaireList(List<Object> list1, List<Object> list2) {
		// 比较并输出结果
		String excelString  = list1.stream().map(Object::toString).collect(Collectors.joining());
		String urlString = list2.stream().map(Object::toString).collect(Collectors.joining());
		if (!excelString.equals(urlString)) {
			return false;
		}else {
			return true;
		}           
	}

}
