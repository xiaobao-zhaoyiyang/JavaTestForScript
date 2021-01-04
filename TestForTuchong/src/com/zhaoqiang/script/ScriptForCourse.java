package com.zhaoqiang.script;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.zhaoqiang.bean.JavaBean;
import com.zhaoqiang.excelUtils.ReadDataFromExcel;
import com.zhaoqiang.internetUtils.ReadDataFromServer;
import com.zhaoqiang.jsonUtils.AnalysisJson;
import com.zhaoqiang.utils.CompaireTwoList;

public class ScriptForCourse {
	ReadDataFromExcel readDataFromExcel = new ReadDataFromExcel();
	ReadDataFromServer readDataFromServer = new ReadDataFromServer();
	AnalysisJson analysisJson = new AnalysisJson();
	CompaireTwoList compaireTwoList = new CompaireTwoList();

	public void course() {
		// 打开本地的excel表格
		XSSFSheet sheet = readDataFromExcel.openExcel(JavaBean.FILE_PATH, JavaBean.INDEX_OF_SHEET);
		// 获取表格的属性
		int count_rows = sheet.getLastRowNum();
		// 获取将要在返回数据中读取值的key
		List<String> content = readDataFromExcel.ReadTitle(sheet);

		// 循环获取数据
		for (int i = 1; i <= count_rows; i++) {
			// 获取行
			List<Object> data_excel = readDataFromExcel.readExcel(sheet, i);
//					System.out.println(data_excel);
			// 获取接口数据
			if (data_excel.size() != 0) {
				List<Object> data_url = new ArrayList<>();
				String course_id = String.valueOf(data_excel.get(0));
				if (course_id != null && course_id != "") {
					String response = readDataFromServer
							.connectServerByGet("https://tuchong.com/gapi/misc/paid-course/course-group/detail"
									+ "?course_group_id=" + course_id);
					// 异步获取数据，循环等待数据返回
					boolean isBack = false;
					while (!isBack) {
//								System.out.println(response);
						if (response != null && response != "") {
							data_url = analysisJson.AnalysisJsonFromUrl(response, content);
							isBack = true;
						}
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
         
					boolean isTrue = compaireTwoList.compaireList(data_excel, data_url);
					if (isTrue) {
						System.out.println("TRUE " + course_id);
						System.out.println("本地数据：" + data_excel);
						System.out.println("接口数据：" + data_url);
					} else {
						System.out.println("ERROR " + course_id);
						System.out.println("本地数据：" + data_excel);
						System.out.println("接口数据：" + data_url);
					}
				}
			}
		}
		System.out.println("验证结束啦.........！！！！！..........");
	}

}
