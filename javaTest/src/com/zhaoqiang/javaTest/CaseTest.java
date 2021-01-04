package com.zhaoqiang.javaTest;

import java.io.File;
import java.io.IOException;

public class CaseTest {
	private static ScriptForCourse scriptForCourse = new ScriptForCourse();
	private static ScriptForDeleteData scriptForDeleteData = new ScriptForDeleteData();

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		scriptForCourse.course();
//		scriptForDeleteData.AnalysisPostId(JavaBean.UID);
//		scriptForDeleteData.deleteData();
		ScriptForLogin scriptForLogin = new ScriptForLogin();
//		scriptForLogin.login();
//		Base64AndImage.GenerateImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAAoCAMAAAACNM4XAAAAP1BMVEUAAABXRzSVhXJKOidZSTZaSjecjHksHAmnl4SFdWJnV0RRQS6/r5yXh3S2ppNrW0jGtqOMfGm5qZZhUT6gkH3mWDoaAAAAAXRSTlMAQObYZgAAAZlJREFUeJzslsuuhCAMhsETSbwkLnz/dz0ZEWhLKQUZV8NiBhX79YdeNL/xG9dYwPx8k7sk8nkOJM9VMpiP5M6z6IK141iEDFygEBsGvPEUmJuY6S1/Ge8SJ3q55ZvrfbVgF/0fBre6Ifq9rp6cwjmA/YXLzVij2YvqCqrY3FL9a865ZMjGiQB2wPu2gfc4cU0CC4IuTzvOJEjZ7Wyg6zaCk3DeiCtEVBV8zfY9ZDzAoMMvq+rY5SkJIorB9ke7BYAGS0riNE1ZGmVEmatTC5rA4ckmSO4D17DxeeIeB3oMrFt0zBJYozZbg7lUsNGAlSFVXgbBMWVhNrFgfSgXHLYouGLmpmrFg5syiPYmWAqZ6iSAmwsVSzZX12bqMa5YsG23YYU3PlUr7wOYiMNgGJldDKOb4NvJDS/C5OY+E76INoDcdbaPyYO+R7tEf362l9GhiG3bc3Ij956N4Ko1ZwtXdtkGfqsWe9pa/Dwm3OsU1GdRY7OPHyu+bZd9GpFAEhkRYj/+KpPQQxuUoH8vOZRx/zTk/wAAAP//09gE/cdBB7IAAAAASUVORK5CYII");
//		
		String path = "/Users/zhaoqiang/Eclipse-workspace/javaTest";//我的项目存放路径
//    	
    	File file = new File(path + "/photo.jpg");
        ITesseract instance = new Tesseract();
 
        /**
         *  获取项目根路径，例如： D:\IDEAWorkSpace\tess4J
         */
        File directory = new File(path);
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("courseFile=" + courseFile);
        //设置训练库的位置
        instance.setDatapath("/Users/zhaoqiang/eclipse-workspace/javaTest/tessdata");
 
        instance.setLanguage("eng");//chi_sim ：简体中文， eng	根据需求选择语言库
		 
		
		
		
//		ITesseract instance = new Tesseract();
	        //如果未将tessdata放在根目录下需要指定绝对路径
	        //instance.setDatapath("the absolute path of tessdata");
	        
//	        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
//	        instance.setLanguage("chi_sim");
	        
	        // 指定识别图片
	        File imgDir = new File("/Users/zhaoqiang/Desktop/temp.jpg");
	        long startTime = System.currentTimeMillis();
	        String ocrResult = instance.doOCR(imgDir);
	        
	        // 输出识别结果
	        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
		
		}	

}
