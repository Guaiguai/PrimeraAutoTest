package com.yiibai.primera.testng.operation;

import java.awt.image.BufferedImage;
import java.io.File;

import org.openqa.selenium.TakesScreenshot;

import com.yiibai.primera.testng.base.ImageAppium;
import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.pages.VideoPage;
import com.yiibai.primera.testng.utils.ConstantUtils;
import com.yiibai.primera.testng.utils.ResultUtils;

import bsh.This;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 登录逻辑处理 Created by ChenXiaoGuai on 2017/09/25.
 */

public class VideoOperate extends OperateAppium {

	private VideoPage videoPage;

	private HomePage homePage;

	AndroidDriver<AndroidElement> driver;

	public VideoOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		videoPage = new VideoPage(driver);
		homePage = new HomePage(driver);
		this.driver = driver;
	}

	/**
	 * 视频详情页面操作 之 字体设置 截屏进行比较，根据页面的图片比较进行判断是否字体设置成功
	 * TODU 此处测试有问题，因为视频是动态的，截图下来的结果会始终不相同的
	 * @return 是否设置成功
	 */
	public ResultUtils fontSize() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if (!isVideoDetailsPage()) {
			result.setMessage(This.class + ":fontSize---没有在视频详情页面！！！");
			return result;
		}
		sleep(5000);
		// 截屏保存
		ImageAppium.snapshot((TakesScreenshot) driver,
				"videoDetail_fontSize_before.png");
		sleep(5000);
		// 点击设置字体按钮---进行字体设置
		clickView(videoPage.getFontSize());
		clickView(videoPage.getOneUncheckedRadio());
		back();
		sleep(5000);
		ImageAppium.snapshot((TakesScreenshot) driver,
				"videoDetail_fontSize_after.png");
		// 图片比较，验证是否设置成功
		String dataDir = ImageAppium.setImgFolder();
		BufferedImage before = ImageAppium.getImageFromFile(
				new File(dataDir + "/videoDetail_fontSize_before.png"));
		BufferedImage after = ImageAppium.getImageFromFile(
				new File(dataDir + "/videoDetail_fontSize_after.png"));

		result.setMessage(
				"字体设置前后对比结果为：" + ImageAppium.sameAs(before, after, 1.0));
		if (!ImageAppium.sameAs(before, after, 1.0)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
		}

		return result;
	}

	/**
	 * 视频详情页面操作 之 点赞
	 * 
	 * @return 是否点赞成功
	 */
	public ResultUtils like() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if (!isVideoDetailsPage()) {
			result.setMessage(This.class + ":like---没有在视频详情页面！！！");
			return result;
		}
		// 如果没有点赞则点赞
		if(!videoPage.isLiked()) {
			clickView(videoPage.getLikeBtn());
		}
		sleep(5000);
		result.setActual(videoPage.isLiked());
		return result;
	}

	/**
	 * 视频详情页面操作 之 收藏
	 * 
	 * @return 是否收藏成功
	 */
	public ResultUtils collect() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if (!isVideoDetailsPage()) {
			result.setMessage(This.class + ":collect---没有在视频详情页面！！！");
			return result;
		}
		// 如果没有点赞则点赞
		if(!videoPage.isCollected())
			clickView(videoPage.getCollectBtn());
		sleep(3000);
		result.setActual(videoPage.isCollected());
		return result;
	}
	/**
	 * 视频详情页面操作 之 评论
	 * 
	 * @return
	 */
	public ResultUtils comment(String details) {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if (!isVideoDetailsPage()) {
			result.setMessage(this.getClass() + ":comment---没有在视频详情页面！！！");
			return result;
		}
		// 点击操作的评论
		clickView(videoPage.getCommentBtn());
		inputManyText(details);
		clickView(videoPage.getCommentSubmitBtn());
		sleep(1000);
		// 在所有的评论中查找测试提交的评论,目前评论区的第一条即是新增的评论
		AndroidElement firstInCommentList = videoPage.getFirstComment();
		// 返回
		back();
		// 获得评论的第一条
		AndroidElement firstInCommentDetail = videoPage.getFirstComment();
		if (firstInCommentList != null
				&& firstInCommentList.getText().equals(details)
				&& firstInCommentDetail != null
				&& firstInCommentDetail.getText().equals(details)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
		} else {
			result.setMessage("用户提交评论，但是没有看到用户的评论内容，测试不通过!");
		}
		//返回到视频列表
//		back();
//		clickView(videoPage.getBackBtn());
		return result;
	}
	
	/**
	 * 定位到视频详情页面
	 * @return
	 */
	private boolean isVideoDetailsPage() {
		Boolean flag = false;
		sleep(1000);
		if (homePage.isHomePage()) {
//			print("在App主页面..........");
			// 在主页面定位视频进去，而不是视频
			clickView(videoPage.getVideosMenu());
		}
		// 点击第一条视频进入到视频详情
		if(!videoPage.isInVideoDetail()) {
			clickView(videoPage.getFirstVideo());
		}
		
		//验证
		if(videoPage.isInVideoDetail()) {
			flag = true;
		}
		return flag;
	}

}
