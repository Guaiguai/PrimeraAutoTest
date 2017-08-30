package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.LocalOperate;
import com.yiibai.primera.testng.operation.NewsDetailsOperate;

/**
 * Local频道测试用例
 * 4.1、local频道的定位，自动定位和选择区域的新闻的显示
 * 4.2、视频的播放的问题，需要翻墙才可以播放，监测网络
 *
 * Created by ChenXiaoGuai 2017/08/16.
 */
public class LocalTest extends InitAppium{

	private LocalOperate localOperate;
	
	@BeforeClass
	public void initDriver() {
		Assert.assertNotNull(driver);
		localOperate = new LocalOperate(driver);
	}
}
