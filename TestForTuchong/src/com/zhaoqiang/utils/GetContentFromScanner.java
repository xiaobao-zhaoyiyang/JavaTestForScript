package com.zhaoqiang.utils;

import java.util.Scanner;

import com.zhaoqiang.bean.JavaBean;

public class GetContentFromScanner {
	
	public static void getUserNameAndPwd() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名，按Enter键结束\n");
		String userName = scanner.next();
		System.out.println("请输入密码，按Enter键结束\n");
		String pwd = scanner.next();
		JavaBean.USERNAME = userName;
		JavaBean.PASSWORD = pwd;
		System.out.println("用户名：" + JavaBean.USERNAME + "， 密码：" + JavaBean.PASSWORD);
	}

}
