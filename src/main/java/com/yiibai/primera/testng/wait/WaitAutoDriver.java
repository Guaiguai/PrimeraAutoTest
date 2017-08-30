package com.yiibai.primera.testng.wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.yiibai.primera.testng.wait.AndroidDriverWait;
import com.yiibai.primera.testng.wait.ExpectedCondition;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 页面UI获取定位父类，供给Page层使用 Created by ChenXiaoGuai on 2017/08/24.
 */

public class WaitAutoDriver {

	AndroidDriver<AndroidElement> driver;

	private static int WAIT_TIME = 3; // 默认的等待控件时间

	/**
	 * 构造函数，初始化
	 * 
	 * @param androidDriver
	 */
	public WaitAutoDriver(AndroidDriver<AndroidElement> androidDriver) {
		this.driver = androidDriver;
		waitAuto(WAIT_TIME);
	}
	/**
	 * 线程休眠秒数，单位秒
	 *
	 * @param s
	 *            要休眠的秒数
	 */
	public void sleep(long s) throws InterruptedException {
		Thread.sleep(s);
	}

	/**
	 * 显示等待，等待Id对应的控件出现time秒，一出现马上返回，time秒不出现也返回
	 * wait for 60s if WebElemnt show up less than 60s , then return , until 60s
	 */
	public AndroidElement waitAuto(final By by, int time) {
		
		try {
			System.out.println("定位的元素：" + by);
			System.out.println("元素所在的driver：" + driver);
			return new AndroidDriverWait(driver, time)
					.until(new ExpectedCondition<AndroidElement>() {
						@Override
						public AndroidElement apply(
								AndroidDriver androidDriver) {
							return (AndroidElement) androidDriver
									.findElement(by);
						}
					});
		} catch (TimeoutException e) {
			System.out.print(
					"查找元素超时!! " + time + " 秒之后还没找到元素 [" + by.toString() + "]");
			return null;
		}
	}

	public AndroidElement waitAutoById(String id) {
		return waitAutoById(id, WAIT_TIME);
	}
	public AndroidElement waitAutoById(String id, int time) {
		return waitAuto(By.id(id), time);
	}

	public AndroidElement waitAutoByName(String name) {
		return waitAutoByName(name, WAIT_TIME);
	}
	public AndroidElement waitAutoByName(String name, int time) {
		return waitAuto(By.name(name), time);
	}

	public AndroidElement waitAutoByXp(String xPath) {
		return waitAutoByXp(xPath, WAIT_TIME);
	}
	public AndroidElement waitAutoByXp(String xPath, int time) {
		return waitAuto(By.xpath(xPath), time);
	}

	/**
	 * 隐式等待，如果在指定时间内还是找不到下个元素则会报错停止脚本 全局设定的，find控件找不到就会按照这个事件来等待 与显示等待的区别就是
	 * 显示等待需要传入参数By,means 直到等待的元素出现为止
	 * 
	 * @param time
	 *            要等待的时间
	 */
	public void waitAuto() {
		waitAuto(WAIT_TIME);
	}
	public void waitAuto(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

}
