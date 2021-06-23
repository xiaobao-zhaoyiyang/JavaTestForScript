package com.zhaoqiang.test;

import com.zhaoqiang.bean.JavaBean;
import com.zhaoqiang.script.ScriptForCourse;
import com.zhaoqiang.script.ScriptForDeleteData;
import com.zhaoqiang.script.ScriptForLogin;
import com.zhaoqiang.utils.GetContentFromScanner;

public class CaseTest {
	private static ScriptForCourse scriptForCourse = new ScriptForCourse();
	private static ScriptForDeleteData scriptForDeleteData = new ScriptForDeleteData();

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		ScriptForLogin.login();
		scriptForCourse.course();
//		scriptForDeleteData.AnalysisPostId(JavaBean.UID);
//		scriptForDeleteData.deleteData(1);
		
		}	

}
