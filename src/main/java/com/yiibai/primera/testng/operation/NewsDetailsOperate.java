package com.yiibai.primera.testng.operation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;

import org.apache.commons.validator.util.Flags;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.yiibai.primera.testng.base.ImageAppium;
import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.constant.Constant;
import com.yiibai.primera.testng.pages.NewsDetailsPage;

import bsh.This;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 登录逻辑处理 Created by ChenXiaoGuai on 2017/08/16.
 */

public class NewsDetailsOperate extends OperateAppium {

	private NewsDetailsPage newsDetailsPage;

	private HomeOperate homeOperate;

	AndroidDriver<AndroidElement> driver;

	public NewsDetailsOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		newsDetailsPage = new NewsDetailsPage(driver);
		homeOperate = new HomeOperate(driver);
		this.driver = driver;
	}

	public boolean isNewsDetailsPage() {
		Boolean flag = false;
		sleep(1000);
		if (homeOperate.isHomePage()) {
			print("在App主页面..........");
			press();
		}
		if (newsDetailsPage.likeBtn() != null) {
			flag = true;
		}
		waitAuto(1000);
		return flag;
	}
	/**
	 * 文章分享
	 * 
	 * @return
	 */
	public boolean share() {
		Boolean flag = false;
		System.out.println("share....");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":share---没有在新闻详情页面！！！");
			return Constant.assertFalse;
		}
		// clickView(newsDetailsPage.shareBtn());
		// System.out.println("page source is:" + driver.getPageSource());
		// TODU 测试分享的真实性，比如分享到QQ，是否去QQ验证下分享的内容，是否确实是分享的内容
		// System.out.println("当前URL is:" + driver.getCurrentUrl());
		try {
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println("contextName is:" + contextName);
				if (contextName.equalsIgnoreCase("NATIVE_APP")) {
					driver.context("NATIVE_APP");
					System.out.println("[-设备-] :  xxx"
							+ " [-Success-]<<  步骤: 切换WEBVIEW  Url:"
							+ driver.getCurrentUrl());
				}
				if (contextName.toString().toLowerCase().contains("webview")) {
					driver.context(contextName);
					System.out.println("[-设备-] :  xxx"
							+ " [-Success-]<<  步骤: 切换NATIVE_APP  Url:"
							+ driver.getCurrentUrl());
				}
			}
		} catch (Exception Error) {
			Error.printStackTrace();
		}
		sleep(10000);
		return Constant.assertTrue;
	}
	
	/**
	 * 新闻详情页面操作 之 字体设置
	 * 截屏进行比较，根据页面的图片比较进行判断是否字体设置成功
	 * TODU 截屏比较的方式被CUT掉，需要重新思路测试
	 * @return 是否设置成功
	 */
	public String fontSize() {
		String msg = null;
		System.out.println("fontSize....");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":fontSize---没有在新闻详情页面！！！");
			return msg;
		}
		sleep(300);
		switchToWeb();
		//截屏保存
//		ImageAppium.snapshot((TakesScreenshot) driver, "newsDetail_fontSize_before.png");
//		//点击设置字体按钮---进行字体设置
//		clickView(newsDetailsPage.fontSize());
//		clickView(newsDetailsPage.fontSizeRadio());
//		press();
//		ImageAppium.snapshot((TakesScreenshot) driver, "newsDetail_fontSize_after.png");
//		//图片比较，验证是否设置成功
//		String dataDir = ImageAppium.setImgFolder();
//		System.out.println(dataDir + "/newsDetail_fontSize_before.png");
//		BufferedImage before = ImageAppium.getImageFromFile(new File(dataDir + "/newsDetail_fontSize_before.png"));
//        BufferedImage after = ImageAppium.getImageFromFile(new File(dataDir + "/newsDetail_fontSize_after.png"));
//        boolean isSame = ImageAppium.sameAs(before,after,0.0);
//        System.out.println("字体设置前后对比结果为：" + isSame);
		return msg;
	}
	/**
	 * 新闻详情页面操作 之 图片查看
	 * 
	 * @return 是否查看成功
	 */
	public String imageSwitcher() {
		String msg = null;
		System.out.println("imageSwitcher....");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":imageSwitcher---没有在新闻详情页面！！！");
			return msg;
		}
		// 如果有配图，则点击查看 如果有多张图片的话，则向左滑动  图片下载
		if(newsDetailsPage.imageElement() == null) {
			return msg = "新闻详情页面没有配图";
		}
		clickView(newsDetailsPage.imageElement());
		if(newsDetailsPage.imageCount() > 1) {
			for (int i = 0; i < newsDetailsPage.imageCount(); i++) {
				swipeToLeft(300);
			}
		}else {
			msg = "新闻详情页---新闻配图只有一张";
		}
		ImageAppium.snapshot((TakesScreenshot) driver, "newsDetail_imageSwitcher");
		//点击下载图片 有问题下载之后本地查看不到
		press();
		System.out.println("msg is:" + msg);
		return msg;
	}
	
	/**
	 * 详情页面下面的相关阅读
	 * @return
	 */
	public boolean readingsMore() {
		return false;
	}
	/**
	 * 新闻详情页面操作 之 点赞
	 * 
	 * @return 是否点赞成功
	 */
	public boolean like() {
		System.out.println("like....");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":like---没有在新闻详情页面！！！");
			return Constant.assertFalse;
		}
		// 如果没有点赞则点赞
		clickView(newsDetailsPage.likeBtn());
		sleep(3000);
		return newsDetailsPage.isLiked();
	}

	/**
	 * 新闻详情页面操作 之 收藏
	 * 
	 * @return 是否收藏成功
	 */
	public boolean collect() {
		System.out.println("collect.....");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":collect---没有在新闻详情页面！！！");
			return Constant.assertFalse;
		}
		// 如果没有点赞则点赞
		clickView(newsDetailsPage.collectBtn());
		sleep(3000);
		return newsDetailsPage.isCollected();
	}
	/**
	 * 新闻详情页面操作 之 评论
	 * 
	 * @return
	 */
	public boolean comment(String details) {
		System.out.println("comment.......");
		if (!isNewsDetailsPage()) {
			System.out.println(This.class + ":comment---没有在新闻详情页面！！！");
			return Constant.assertFalse;
		}
		clickView(newsDetailsPage.commentBtn());
		inputManyText(details);
		clickView(newsDetailsPage.commentSendBtn());
		//返回
		back();
		return true;
	}

}
