package com.yiibai.primera.testng.operation;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.pages.LoginPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 登录逻辑处理 Created by LITP on 2016/9/23.
 */

public class LocalOperate extends OperateAppium {

	private LoginPage loginPage;
	
	private HomeOperate homeOperate;

	AndroidDriver<AndroidElement> driver;

	public LocalOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		loginPage = new LoginPage(driver);
		homeOperate = new HomeOperate(driver);
		this.driver = driver;
	}

	

}
