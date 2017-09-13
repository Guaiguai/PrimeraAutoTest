package com.yiibai.primera.testng.base;
import org.openqa.selenium.NoSuchElementException;
import static com.yiibai.primera.testng.base.InitAppium.appPackage;

import com.yiibai.primera.testng.wait.WaitAutoDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 通过UiAutomator的方式查找元素，
 * 即封装findElementByAndroidUiAutomator() 
 * Created by ChenXiaoGuai on 2017/08/24
 */

public class UiAutomatorAppium extends WaitAutoDriver{

	AndroidDriver<AndroidElement> driver;

	public UiAutomatorAppium(AndroidDriver<AndroidElement> androidDriver) {
		super(androidDriver);
		this.driver = androidDriver;
	}
	/**
	 * 当我们碰到很长的ListView，且需要在这个ListView里面查询指定的元素的时候。我们如
	 * 何做呢？生硬的swipe+findElement适用吗？
	 * 场景：在XX列表中找到带有 标记  字样的选项，然后点击。
	 * BUG  详情帖子见   https://testerhome.com/topics/4064
	 * @return
	 */
	public AndroidElement swipToElementAppear() {
		//UiAutomator原生
		//此方法的含义是先获取当前页面可滑动的元素，然后在这个元素的基础上，找到包含`上课中`这三个字的项目，再点击
		//UiObject cl = new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelect
		try {
			if(driver != null) {
				String AutomatorStr = 
					"new UiScrollable(new UiSelector().scrollable(true))";
				AndroidElement element = driver.findElementByAndroidUIAutomator(AutomatorStr);
				return element;
			}else {
				System.out.println("driver为空");
			}
		} catch (Exception e) {
			System.out.println("找不到控件:" + " 异常信息:" + e.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * @param text
	 * 
	 * @return
	 */
	public AndroidElement findById(String id) {
		try {
			if (driver != null) {    
				String automatorId = "new UiSelector().resourceId(\"com.turbo.turbo.mexico:id/lin_parent\")";
				return driver.findElementByAndroidUIAutomator(automatorId);
			} else {
				System.out.println("driver为空");
			}
		} catch (NoSuchElementException e) {
			System.out.println("找不到控件:" + " 异常信息:" + e.getMessage());

		}
		return null;
	}

}
