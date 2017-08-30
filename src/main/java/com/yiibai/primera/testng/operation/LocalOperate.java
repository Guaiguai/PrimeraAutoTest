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

	AndroidDriver<AndroidElement> driver;

	public LocalOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		loginPage = new LoginPage(driver);
		this.driver = driver;
	}

	/**
	 * 传递帐号密码
	 * 
	 * @param name
	 *            帐号
	 * @param pass
	 *            密码
	 * @return 是否成功登录到主页
	 */
	public boolean login(String name, String pass) {

		sleep(1000);
		// 是否在欢迎页面---广告动态切换的SWIPE
		if (loginPage.isWelcome()) {
			print("app打开时广告动态切换的swipe...");
			for (int i = 0; i < 4; i++) {
				swipeToLeft(300);
				sleep(500);
			}
		}
		// 如果不在主页，直接退出
		if (loginPage.isHomePage()) {
			System.out.println("在APP主界面");
			// 1、点击首页右下角的cuenta---进入个人中心
			clickView(loginPage.getCuentaBtn(), "Cuenta Btn");
		}else {
			//TODU 退出APP
		}
		// 在个人中心界面， 验证用户是否已经登录，yes则退出重新登录
		if (loginPage.getLogOutBtn() != null) {
			System.out.println("退出重新登录...");
			// 1.1点击退出
			clickView(loginPage.getLogOutBtn(), "LogOut Btn");
			clickView(loginPage.getLogOutOkBtn(), "LogOutOk Btn");
		}
		if (loginPage.getSignInBtn() != null) {
			// 2、点击 Registrarse Btn --- 进入登录界面
			clickView(loginPage.getSignInBtn(), "Signin Btn");
		}
		// 输入内容
		inputManyText(name, pass);
		// 3、点击登录
		if(loginPage.getLoginBtn().isEnabled()) {
			clickView(loginPage.getLoginBtn(), "login Btn");
		}
		// 登录时如果出现错误提示弹框，则点击下隐藏掉
		if (loginPage.isAlert()) {
			press();
		}
		sleep(1000);
		// 返回是否成功到个人中心页面
		return loginPage.isLogined();
	}

}
