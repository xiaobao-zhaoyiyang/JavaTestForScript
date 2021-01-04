package com.zhaoqiang.test;

import com.zhaoqiang.script.ScriptForCourse;
import com.zhaoqiang.script.ScriptForDeleteData;
import com.zhaoqiang.script.ScriptForLogin;

public class CaseTest {
	private static ScriptForCourse scriptForCourse = new ScriptForCourse();
	private static ScriptForDeleteData scriptForDeleteData = new ScriptForDeleteData();

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ScriptForLogin.login();
		scriptForCourse.course();
//		scriptForDeleteData.AnalysisPostId(JavaBean.UID);
//		scriptForDeleteData.deleteData();
		
		}	

}
