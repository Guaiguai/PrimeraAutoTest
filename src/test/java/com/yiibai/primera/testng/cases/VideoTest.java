package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.VideoOperate;
import com.yiibai.primera.testng.util.MethodUtil;
import com.yiibai.primera.testng.util.ResultUtil;

/**
 * 视频详情页面测试用例
 * 2.1、新闻点赞，收藏，评论，字体设置、分享的测试
 * 
 * Created by ChenXiaoGuai on 2017/09/25.
 */
public class VideoTest extends InitAppium {

	private VideoOperate videoOperate;

	@BeforeMethod
	public void initDriver() {
		Assert.assertNotNull(driver);
		videoOperate = new VideoOperate(driver);
	}
	/**
	 * 验证视频评论
	 */
	@Test(priority = 1)
	public void comment() {
		ResultUtil result = videoOperate.comment("this video is very good!");
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print(MethodUtil.getFileLineMethod() + "验证视频评论:" + result.getActual() + ",返回信息为:" + result.getMessage());
	}
	/**
	 * 验证视频收藏
	 */
	@Test(priority = 2)
	public void collect() {
		ResultUtil result = videoOperate.collect();
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print(MethodUtil.getFileLineMethod() + "验证视频收藏:" + result.getActual());
	}
	/**
	 * 验证视频点赞
	 */
	@Test(priority = 3)
	public void like() {
		ResultUtil result = videoOperate.like();
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print(MethodUtil.getFileLineMethod() + "验证视频点赞:" + result.getActual());
	}
	/**
	 * 验证新闻详情页的字体的设置
	 */
	@Test(priority = 4)
	public void fontSize() {
		ResultUtil result = videoOperate.fontSize();
		Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
		print(MethodUtil.getFileLineMethod() + "验证视频字体设置:" + result.getMessage());
	}
}
