package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.NewsDetailsOperate;

/**
 * 新闻详情页面测试用例
 * 2.1、点击图片，如果有多张图片，可以图片切换，下载的测试 
 * 2.2、新闻点赞，收藏，评论，字体设置、分享的测试
 * 
 * Created by ChenXiaoGuai on 2017/08/16.
 */
@Test(priority = 5)
public class NewsDetailsTest extends InitAppium {

	private NewsDetailsOperate newsdetailsOperate;

	@BeforeClass
	public void initDriver() {
		Assert.assertNotNull(driver);
		newsdetailsOperate = new NewsDetailsOperate(driver);
	}
	/**
	 * 验证新闻详情页的字体的设置
	 */
	@Test(priority = 6)
	public void share() {
		boolean flag = newsdetailsOperate.share();
		Assert.assertTrue(flag);
		print("验证文章分享:" + flag);
	}
	
	/**
	 * 验证新闻详情页的字体的设置
	 */
	@Test(priority = 4)
	public void fontSize() {
		String msg = newsdetailsOperate.fontSize();
		Assert.assertEquals(msg, null);
		print("验证文章字体设置:" + msg);
	}
	
	/**
	 * 验证文章配图查看
	 */
	@Test(priority = 4)
	public void imageSwitch() {
		String msg = newsdetailsOperate.imageSwitcher();
		Assert.assertEquals(msg, null);
		print("验证文章配图查看:" + msg);
	}
	/**
	 * 验证文章评论
	 */
	@Test(priority = 5)
	public void comment() {
		boolean flag = newsdetailsOperate.comment("very good!");
		Assert.assertTrue(flag, "验证文章评论");
		print("验证文章评论:" + flag);
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
