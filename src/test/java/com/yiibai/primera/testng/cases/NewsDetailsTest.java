package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.NewsDetailsOperate;
import com.yiibai.primera.testng.util.ResultUtil;

/**
 * 新闻详情页面测试用例
 * 2.1、点击图片，如果有多张图片，可以图片切换，下载的测试 
 * 2.2、新闻点赞，收藏，评论，字体设置、分享的测试
 * 
 * Created by ChenXiaoGuai on 2017/08/16.
 */
public class NewsDetailsTest extends InitAppium {

	private NewsDetailsOperate newsdetailsOperate;

	@BeforeMethod
	public void initDriver() {
		Assert.assertNotNull(driver);
		newsdetailsOperate = new NewsDetailsOperate(driver);
	}
	/**
	 * 验证新闻详情页的字体的设置
	 */
	@Test(priority = 6,enabled = false)
	public void share() {
		boolean flag = newsdetailsOperate.share();
		Assert.assertTrue(flag);
		print("验证文章分享:" + flag);
	}
	
	/**
	 * 验证新闻详情页的字体的设置
	 */
	@Test(priority = 4,enabled = false)
	public void fontSize() {
		String msg = newsdetailsOperate.fontSize();
		Assert.assertEquals(msg, null);
		print("验证文章字体设置:" + msg);
	}
	
	/**
	 * 验证文章配图查看
	 */
	@Test(priority = 1)
	public void imageSwitch() {
		ResultUtil result = newsdetailsOperate.imageSwitcher();
		System.out.println("getActual is:" + result.getActual());
		System.out.println("getExcepted is:" + result.getExcepted());
		System.out.println("message is:" + result.getMessage());
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print("验证文章配图查看:" + result.getMessage());
	}
	/**
	 * 验证文章评论
	 */
	@Test(priority = 5)
	public void comment() {
		ResultUtil result = newsdetailsOperate.comment("very good!");
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print("验证文章评论:" + result.getActual() + ",返回信息为:" + result.getMessage());
	}
	/**
	 * 验证文章收藏
	 */
	@Test(priority = 2)
	public void collect() {
		boolean flag = newsdetailsOperate.collect();
		Assert.assertTrue(flag, "验证文章收藏");
		print("验证文章收藏:" + flag);
	}
	/**
	 * 验证文章点赞
	 */
	@Test(priority = 3)
	public void like() {
		boolean flag = newsdetailsOperate.like();
		Assert.assertTrue(flag, "验证文章点赞");
		print("验证文章点赞:" + flag);
	}

}
