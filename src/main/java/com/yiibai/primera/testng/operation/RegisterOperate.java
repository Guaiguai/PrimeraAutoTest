package com.yiibai.primera.testng.operation;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.constant.Constant;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.pages.RegisterPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 登录逻辑处理 Created by ChenXiaoGuai on 2017/08/10.
 */

public class RegisterOperate extends OperateAppium {

	private RegisterPage registerPage;
	
	private HomePage homePage;

	AndroidDriver<AndroidElement> driver;

	public RegisterOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		registerPage = new RegisterPage(driver);
		homePage = new HomePage(driver);
		this.driver = driver;
	}
	/**
	 * 
	 * @param email
	 * @param name
	 * @param pass
	 * @return 是否注册成功
	 * @throws Exception
	 */
	public boolean register(String email, String name, String pass) {
		// 是否在欢迎页面---广告动态切换的SWIPE
		sleep(1000);
		if(!homePage.isHomePage()) {
			return Constant.assertFalse;
		}
		// 1、点击首页右下角的cuenta---进入个人中心
		clickView(registerPage.getCuentaBtn(), "Cuenta Btn");
		// 在个人中心界面， 验证用户是否已经登录(注册)，yes则退出
		if (registerPage.getLogOutBtn() != null) {
			System.out.println("已有账号存在");
			// 1.1点击退出
			clickView(registerPage.getLogOutBtn(), "LogOut Btn");
			clickView(registerPage.getLogOutOkBtn(), "LogOutOk Btn");
		}
		if(registerPage.getSignInBtn() != null) {
			//2、点击 Registrarse Btn --- 进入登录界面
			clickView(registerPage.getSignInBtn(), "Signin Btn");
		}
		if(registerPage.getCrearBtn() != null) {
			 //3、点击Crear Btn --- 进入注册主页面
			clickView(registerPage.getCrearBtn(), "Crear Btn");
		}
		System.out.println("开始注册啦。。。。。。");
		if (registerPage.getSingUpBtn() == null) {
			System.out.println("没有在APP注册主界面");
			return Constant.assertFalse;
		}
		// 输入内容
		inputManyText(email, name, pass);
		// 4、点击注册
		clickView(registerPage.getSingUpBtn(), "SignUp Btn");
		//注册时如果出现错误提示弹框，则点击下隐藏掉
		if(registerPage.isAlert()) {
			press();
		}
		// 返回是否成功到个人中心页面
//		return true;
		return registerPage.isRegistered();
	}

}
