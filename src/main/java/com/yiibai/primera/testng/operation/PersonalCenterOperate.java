package com.yiibai.primera.testng.operation;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.base.UiAutomatorAppium;
import com.yiibai.primera.testng.pages.LoginPage;
import com.yiibai.primera.testng.pages.PersonalCenterPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 个人中心 Created by ChenXiaoGuai on 2017/08/23.
 */

public class PersonalCenterOperate extends OperateAppium {

	private PersonalCenterPage personalCenterPage;
	
	private HomeOperate homeOperate;

	AndroidDriver<AndroidElement> driver;

	public PersonalCenterOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		personalCenterPage = new PersonalCenterPage(driver);
		homeOperate = new HomeOperate(driver);
		this.driver = driver;
	}
	/**
	 * APP分享
	 * @return
	 */
	public boolean share() {
		if(!isPersonalCenter()) {
			return false;
		}
		clickView(personalCenterPage.getShareBtn());
		
		return true;
	}
	/**
	 * 我的收藏
	 * @return
	 */
	public boolean myCollectionNew() {
		if(!isPersonalCenter()) {
			return false;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		UiAutomatorAppium uiAutomatorAppium = new UiAutomatorAppium(driver);
		AndroidElement theEnd = uiAutomatorAppium.swipToElementAppear();
		if(theEnd != null) {
			System.out.println(theEnd.getText());
			System.out.println("我的收藏里面没有收藏的新闻列表");
			return true;
		}else {
			System.out.println("chucuo ");
		}
		
//		clickView(personalCenterPage.getCollectionEditBtn());
//		clickView(personalCenterPage.getCollectionDeleteBtn());
//		clickView(personalCenterPage.getCollectionUpdateBtn());
//		AndroidElement firstNews = personalCenterPage.getCollectionParent();
//		if(firstNews != null && firstCollection.getText() == firstNews.getText()) {
//			System.out.println("我的收藏---取消收藏的第一条新闻---结果失败");
//			return false;
//		}
//		System.out.println("我的收藏---取消收藏的第一条新闻---结果成功");
//		sleep(3000);
		return false;
	}
	
	/**
	 * 我的收藏
	 * @return
	 */
	public boolean myCollection() {
		if(!isPersonalCenter()) {
			return false;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		AndroidElement firstCollection = personalCenterPage.getCollectionParent();
		if(firstCollection == null) {
			System.out.println("我的收藏里面没有收藏的新闻列表");
			return true;
		}
		clickView(personalCenterPage.getCollectionEditBtn());
		clickView(personalCenterPage.getCollectionDeleteBtn());
		clickView(personalCenterPage.getCollectionUpdateBtn());
		AndroidElement firstNews = personalCenterPage.getCollectionParent();
		if(firstNews != null && firstCollection.getText() == firstNews.getText()) {
			System.out.println("我的收藏---取消收藏的第一条新闻---结果失败");
			return false;
		}
		System.out.println("我的收藏---取消收藏的第一条新闻---结果成功");
		sleep(3000);
		return true;
	}
	/**
	 * 是否在个人中心主页面
	 * @return
	 */
	private boolean isPersonalCenter() {
		if(!homeOperate.isHomePage()) {
			return false;
		}
		clickView(personalCenterPage.getCuentaBtn());
		return true;
	}

}
