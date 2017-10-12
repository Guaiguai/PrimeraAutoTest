package com.yiibai.primera.testng.cases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.LocalOperate;
import com.yiibai.primera.testng.utils.MethodUtils;
import com.yiibai.primera.testng.utils.ResultUtils;


/**
 * Local频道测试用例
 * 4.1、local频道的定位，自动定位和选择区域的新闻的显示
 * 4.2、视频的播放的问题，需要翻墙才可以播放，监测网络
 *
 * Created by ChenXiaoGuai 2017/09/29.
 */
//@Listeners({com.yiibai.primera.testng.listener.ReportListener.class})
public class LocalTest extends InitAppium{
	private LocalOperate localOperate;

    @BeforeMethod
    public void initDriver(){
        Assert.assertNotNull(driver);
        localOperate = new LocalOperate(driver);
    }
    /**
     * 测试定位
     */
	@Test(priority = 1)
	public void location() {
		ResultUtils result = localOperate.location();
		Assertion.verifyEquals(result.getActual(), result.getExcepted(),
				result.getMessage());
		if (result.getActual() != result.getExcepted()) {
			System.out.println(MethodUtils.getFileLineMethod() + "local频道定位:"
					+ result.getActual() + ",提示信息是：" + result.getMessage());
		}

	}
    /**
     * 不再感兴趣
     */
    @Test(priority = 2)
    public void dislike(){
        ResultUtils result = localOperate.cancleUnlikednews();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        if (result.getActual() != result.getExcepted()) {
			System.out.println(MethodUtils.getFileLineMethod() + "local频道不再有兴趣:"
					+ result.getActual() + ",提示信息是：" + result.getMessage());
		}
    }
    /**
     * 新闻列表刷新（滑动定位到关键字，点击刷新）
     */
    @Test(priority = 3)
    public void refreshByKeyword(){
        ResultUtils result = localOperate.refreshByKeyword();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        if (result.getActual() != result.getExcepted()) {
			System.out.println(MethodUtils.getFileLineMethod() + "local频道页面新闻点击关键字刷新:"
					+ result.getActual() + ",提示信息是：" + result.getMessage());
		}
    }
    /**
     * 阅读更多
     */
    @Test(priority = 4)
    public void readingMore(){
        ResultUtils result = localOperate.readingMore();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        if (result.getActual() != result.getExcepted()) {
			System.out.println(MethodUtils.getFileLineMethod() + "local新闻阅读更多:"
					+ result.getActual() + ",提示信息是：" + result.getMessage());
		}
    }

}
