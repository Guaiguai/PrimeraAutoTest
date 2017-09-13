package com.yiibai.primera.testng.operation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.log4testng.Logger;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.constant.Constant;
import com.yiibai.primera.testng.pages.HomePage;

import bsh.This;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * APP欢迎界面逻辑 作为测试的基础 
 * Created by ChenXiaoGuai on 2017/08/16.
 */

public class HomeOperate extends OperateAppium {

	private HomePage homePage;
	private static Logger logger = Logger.getLogger(HomeOperate.class);

	AndroidDriver<AndroidElement> driver;
	
	public HomeOperate(AndroidDriver<AndroidElement> initdriver) {
		super(initdriver);
		System.out.println(this.getClass() + "----");
		homePage = new HomePage(initdriver);
		this.driver = initdriver;
	}
	/**
	 * 首页右上角新闻菜单编辑
	 * @return
	 */
	public boolean menusEdit() {
		if(!isHomePage()) {
			return Constant.assertFalse;
		}
		//进入菜单编辑主页面
		clickView(homePage.getHomeMenuEditBtn());
		clickView(homePage.getMenuEditBtn());
		sleep(300);
		AndroidElement menuAuto = swipTilElementAppear(homePage.menuAuto(),"UP", 300);
		
		if(menuAuto != null) {
			logger.info("menuAuto text is:" + menuAuto.getText());
			logger.info("menuAuto text is:" + menuAuto.getText());
			AndroidElement autoBtn = null;
			autoBtn = homePage.Auto();
			if(autoBtn != null) {
				logger.info("用xpath方式定位到元素");
				clickView(autoBtn);
				clickView(homePage.getMenuEditBtn());
				//TODU 需要开发配合修改，比如添加文字说明，已添加，未添加之类，根据该文字返回首页滑动菜单查找测试元素
				//返回首页
				clickView(homePage.getMenuBack());
				//在菜单上滑动知道Auto菜单出现
				boolean isDisplayed = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
				logger.info("isDisplayed:" + isDisplayed);
				//此时需要判断是否应该显示  加菜单，显示 /减菜单，不显示就OK，反之case失败  开发配合
			}
		}
		return Constant.assertTrue;
	}
	/**
	 * 首页左上角新闻搜索
	 * 两种方式搜索
	 * @return
	 */
	public boolean searchByInput() {
		if(!isHomePage()) {
			return Constant.assertFalse;
		}
		if(homePage.getHomeSearchBtn() != null)
			clickView(homePage.getHomeSearchBtn());
		inputManyText("Suspenden agua potable");
		clickView(homePage.getSearchBtn());
		//根据内容判断是否有搜索到信息
		if(homePage.getSearchNone() == null) {
			logger.info("搜索到结果");
			//链接到新闻详情页面
			clickView(homePage.getSearchAll());
		}else{
			logger.info("没有搜索到结果");
		}
		//操作完成之后返回主页
		backHome();
		return Constant.assertTrue;
	}
	/**
	 * 首页左上角新闻搜索
	 * 两种方式搜索
	 * @return
	 */
	public boolean searchByHistory() {
		if(!isHomePage()) {
			return Constant.assertFalse;
		}
		clickView(homePage.getHomeSearchBtn());
		if(homePage.getSearchHistoryBtn() == null) {
			logger.info("暂时没有搜索历史！");
			return Constant.assertTrue;
		}
		clickView(homePage.getSearchHistoryBtn());
		waitAuto();
		//根据内容判断是否有搜索到信息
		if(homePage.getSearchNone() == null) {
			logger.info("搜索到结果！");
			//链接到新闻详情页面
			clickView(homePage.getSearchAll());
		}else{
			logger.info("没有搜索到结果！");
		}
		//操作完成之后返回主页
		backHome();
		return Constant.assertTrue;
	}
	
	/**
	 * TODU首页刷新
	 * @return
	 */
	public boolean refresh() {
		if(!isHomePage()) {
			return Constant.assertFalse;
		}
//		boolean isDisplayed = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
//		logger.info("isDisplayed:" + isDisplayed);
		return Constant.assertFalse;
	}
	/**
	 *  首页晨报的测试
	 * @return
	 * @throws ParseException 
	 */
	public boolean MorningPaper() throws ParseException {
		Boolean flag = false;
		if(!isHomePage()) {
			return Constant.assertFalse;
		}
		//是否应该显示早报
		Boolean morningPaper = false;
		//当前时间和晨报显示时间对比，小于则显示晨报，大于则不显示
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date nowTime = df.parse(df.format(now));//当前时间
		Date compareTime = df.parse("10:30:00");//指定时间
		if(nowTime.getTime() - compareTime.getTime() <= 0) {
			morningPaper = true;
			logger.info("nowTime <= compareTime,应该显示早报");
		}else {
			logger.info("nowTime > compareTime,不应该显示早报");
		}
		//抓取元素
		AndroidElement element = homePage.getMorningPaperImageElement();
		if(element != null) {
			logger.info("有早报！");
			flag = true;
		}else {
			logger.info("没有早报！");
		}
		//如果正常显示早报，则点击进去，浏览一条早报信息
		if(morningPaper) {
			if(flag) {
				clickView(element);
				//点击一条早报信息,进入新闻详情页面，此时可以依赖NewsDetails.java
				press();
				System.out.println("早报验证通过");
				backHome();
				return Constant.assertTrue;
			}else {
				System.out.println("应该有早报显示，但是没有显示");
				return Constant.assertFalse;
			}
		}else {
			System.out.println("过了早报时间");
			return Constant.assertFalse;
		}
	}
	/**
	 * 测试完成之后返回到首页
	 */
	private void backHome() {
		while (!isHomePage()) {
			back();
		}
	}

	/**
	 * 页面操作---作为其他所有页面测试的基类调用
	 * 
	 * @return 是否成功登录到主页
	 */
	public boolean isHomePage() {
		boolean isHomePage = false;
		sleep(1000);
		// 是否在欢迎页面---广告动态切换的SWIPE
		if (homePage.isWelcome()) {
			print("app打开时广告动态切换的swipe...");
			for (int i = 0; i < 3; i++) {
				swipeToLeft(300);
				sleep(500);
			}
		}
		// 如果不在主页，直接退出
		if (homePage.isHomePage()) {
			isHomePage = true;
		}else {
			//
			isHomePage = false;
		}
		return isHomePage;
	}

}
