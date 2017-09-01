package com.yiibai.primera.testng.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.bcel.generic.NEW;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 测试用例的父类 Created by LITP on 2016/9/7.
 */
@Listeners({com.yiibai.primera.testng.listener.AssertionListener.class})
public class InitAppium {

	public static String device = "Android";
	// 调试设备名字
	public static String deviceName = "fd47af2d7d42";
	// public static String deviceName = "X8QDU14A18000077";
	// 调试设备系统版本
	public static String platformVersion = "4.1";
	// app路径
	// public static String appPath = "E:\\GuaiGuai\\work
	// apps\\primera_1.4.0_0725.apk";
	public static String appPath = System.getProperty("user.dir") 
			+ "/src/main/java/apps/Pokdeng_v1.0.9_apkpure.com.apk";
	// 包名
	public static String appPackage = "com.turbo.turbo.mexico";

	// 是否需要重新安装
	public static String noReset = "True";

	// 是否不重新签名
	public static String noSign = "True";

	// 是否使用unicode输入法，真是支持中文
	public static String unicodeKeyboard = "True";

	// 是否祸福默认呢输入法
	public static String resetKeyboard = "True";

	// 要启动的Activity
	// public static String appActivity =
	// "com.baiyi.jj.app.activity.LogoActivity";
	public static String appActivity = "";

	public AndroidDriver<AndroidElement> driver = null;

	// 构造方法
	public InitAppium() {
		this(new Builder());
	}

	public InitAppium(Builder builder) {

		appActivity = builder.appActivity;
		appPackage = builder.appPackage;
		appPath = builder.appPath;
		device = builder.device;
		deviceName = builder.deviceName;
		noReset = builder.noReset;
		noSign = builder.noSign;
		unicodeKeyboard = builder.unicodeKeyboard;
		resetKeyboard = builder.resetKeyboard;
	}

	/**
	 * appium启动参数
	 *
	 * @throws MalformedURLException
	 */
	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		//启动appium服务
//		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//		service.start();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("device", device);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("app", new File(appPath).getAbsolutePath());
		capabilities.setCapability("appPackage", appPackage);
		// 支持中文
		capabilities.setCapability("unicodeKeyboard", unicodeKeyboard);
		// 运行完毕之后，变回系统的输入法
		capabilities.setCapability("resetKeyboard", resetKeyboard);
		// 不重复安装
		capabilities.setCapability("noReset", noReset);
		// 不重新签名
		capabilities.setCapability("noSign", noSign);
		// 打开的activity
		if (!TextUtils.isEmpty(appActivity)) {
			capabilities.setCapability("appActivity", appActivity);
		}
		// System.out.println("appActivity is: " +
		// capabilities.getCapability(appActivity));
		// 启动Driver
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		// 每一个用例完毕结束这次测试
		 driver.quit();
	}
	/**
	 * 打印字符 未引用
	 * 
	 * @param str
	 *            要打印的字符
	 */
	public <T> void print(T str) {
		if (!TextUtils.isEmpty(String.valueOf(str))) {
			System.out.println(str);
		} else {
			System.out.println("输出了空字符");
		}
	}

}
