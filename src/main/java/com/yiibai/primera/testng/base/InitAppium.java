package com.yiibai.primera.testng.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import org.apache.http.util.TextUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * 测试用例的父类 Created by LITP on 2016/9/7.
 */
@Listeners({com.yiibai.primera.testng.listener.AssertionListener.class})
public class InitAppium {
	/*公共参数区*/
	public static String device = "Android";
	// 调试设备名字
	public static String deviceName = "fd47af2d7d42";
	public static String udid = deviceName;
	// public static String deviceName = "X8QDU14A18000077";
	// 调试设备系统版本
	public static String platformVersion = "4.1";
	//dirver的session的超时时间，默认是60秒
	public static String commandTimeout = "6000";
	//automationName表示appium使用的测试引擎，默认是appium，也可以是uiautomator
	public static String automationName = "appium";
	// 不重置应用数据，如重新启动登录过的App时，仍然是登录状态，不需要重新登录
	public static String noReset = "True";
	
	/*android特有的配置*/
	// 是否不重新签名
	public static String noSign = "True";
	// unicodeKeyboard设置为true表示我们要使用appium自带的输入法，用来支持中文和隐藏键盘，并且将其设置为默认输入法
	public static String unicodeKeyboard = "True";
	// 在执行完测试之后，将手机的输入法从appium输入法还原成手机默认输入法
	public static String resetKeyboard = "True";
	//_当你的其实activity和真正启动后的activity不一致的时候，请把启动的activity放在这里
	public static String waitActivity = "";

	
	// app路径
	public static String appPath = System.getProperty("user.dir") 
			+ "/src/main/java/apps/PN_ADS.apk";
	// 包名
	public static String appPackage = "com.turbo.turbo.mexico";
	// 要启动的Activity
//	 public static String appActivity =".baiyi.jj.app.activity.LogoActivity";
	public static String appActivity = "";

	public static AndroidDriver<AndroidElement> driver;

	// 构造方法
	public InitAppium() {
		this(new Builder());
	}

	public InitAppium(Builder builder) {

		device = builder.device;
		deviceName = builder.deviceName;
		udid = builder.udid;
		commandTimeout = builder.commandTimeout;
		automationName = builder.automationName;
		platformVersion = builder.platformVersion;
		noReset = builder.noReset;
		
		noSign = builder.noSign;
		unicodeKeyboard = builder.unicodeKeyboard;
		resetKeyboard = builder.resetKeyboard;

		appActivity = builder.appActivity;
		appPackage = builder.appPackage;
		appPath = builder.appPath;
	}

	/**
	 * appium启动参数
	 *
	 * @throws MalformedURLException
	 * @throws InterruptedException 
	 */
	@BeforeClass
	public void beforeSuite() throws MalformedURLException, InterruptedException {
		System.out.println("beforeSuite begin---");
		//启动appium服务
//		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//		service.start();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//共用参数区
		capabilities.setCapability("device", device);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.UDID, udid);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, commandTimeout);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, noReset);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		// 支持中文
		capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, unicodeKeyboard);
		capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD,resetKeyboard);
		capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, noSign);
//		capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, waitActivity);
		

		capabilities.setCapability("app", new File(appPath).getAbsolutePath());
		capabilities.setCapability("appPackage", appPackage);
		// 打开的activity
		if (!TextUtils.isEmpty(appActivity)) {
			capabilities.setCapability("appActivity", appActivity);
		}
		// System.out.println("appActivity is: " +
		// capabilities.getCapability(appActivity));
		// 启动Driver
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		System.out.println("driver is:" + driver);
		System.out.println("beforeSuite over----");
//		return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
//				capabilities);
	}

//	@AfterSuite
//	public void afterTest() {
//		driver.quit();
//	}

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
