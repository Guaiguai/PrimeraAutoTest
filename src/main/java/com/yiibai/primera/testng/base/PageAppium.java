package com.yiibai.primera.testng.base;

import static com.yiibai.primera.testng.base.InitAppium.appPackage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.yiibai.primera.testng.wait.WaitAutoDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 页面UI获取定位父类，供给Page层使用 Created by LITP on 2016/9/23.
 */

public class PageAppium extends WaitAutoDriver{

	AndroidDriver<AndroidElement> driver;

	private static int WAIT_TIME = 3; // 默认的等待控件时间

	/**
	 * 构造函数，初始化
	 * 
	 * @param androidDriver
	 */
	public PageAppium(AndroidDriver<AndroidElement> androidDriver) {
		super(androidDriver);
		this.driver = androidDriver;
		waitAuto(WAIT_TIME);
	}

	/**
	 * 根据id判断当前界面是否存在并显示这个控件
	 *
	 * @param id
	 *            要查找的id
	 * @param isShow
	 *            是否判断控件显示
	 * @return 返回对应的控件
	 */
	public boolean isIdElementExist(String id) {
		return isIdElementExist(id, 0);
	}

	public boolean isIdElementExist(String id, int timeOut) {
		return isIdElementExist(id, timeOut, false);
	}
	public boolean isIdElementExist(String id, int timeOut, boolean isShow) {
		return isElementExist(By.id(appPackage + ":id/" + id), timeOut, isShow);
	}

	/**
	 * 选择当前界面的有这个文字的控件
	 *
	 * @param name
	 * @return
	 */
	public boolean isNameElementExist(String name) {
		return isNameElementExist(name, 0);
	}

	public boolean isNameElementExist(String name, int timeOut) {
		return isNameElementExist(name, timeOut, false);
	}

	public boolean isNameElementExist(String name, int timeOut,
			boolean isShow) {
		return isElementExist(By.name(name), timeOut, isShow);
	}

	/**
	 * 判断当前界面有没有这个Xpath控件存在
	 *
	 * @param text
	 *            要判断的字符串
	 * @return 存在返回真
	 */
	public boolean isXpathExist(String text) {
		return isXpathExist(text, 0);
	}

	public boolean isXpathExist(String text, int timeOut) {
		return isXpathExist(text, timeOut, false);
	}

	public boolean isXpathExist(String text, int timeOut, boolean isShow) {
		//// android.widget.TextView[@text='"+text+"']
		return isElementExist(By.xpath(text), timeOut, isShow);

	}

	/**
	 * 判断控件时候存在
	 *
	 * @param by
	 *            By
	 * @param timeout
	 *            等待的事件
	 * @return
	 */
	public boolean isElementExist(By by, int timeout, boolean isShow) {
		try {
			AndroidElement element = waitAuto(by, timeout);
			if (element == null) {
				return false;
			} else {
				if (isShow) {
					return element.isDisplayed();
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 获取当前的activity,返回文件名
	 *
	 * @return
	 */
	public String getCurrActivity() {
		String str = driver.currentActivity();
		return str.substring(str.lastIndexOf(".") + 1);
	}

	/**
	 * 判断当前界面有没有这个字符串存在
	 *
	 * @param text
	 *            要判断的字符串
	 * @return 存在返回真
	 */
	public boolean isTextExist(String text) {
//		print("pageAppium---isTextExit(): " + text);
		String str = driver.getPageSource();
//		print(str);
//		print("pageAppium---isTextExit() ret is:" + str.contains(text));
		return str.contains(text);
	}

	public AndroidElement findByFullId(String id) {
		try {
			if (driver != null) {
				return (AndroidElement) driver.findElement(By.id(id));
			} else {
				print("driver为空");
			}
		} catch (NoSuchElementException e) {
			print("找不到控件:" + " 异常信息:" + e.getMessage());

		}

		return null;
	}

	/**
	 * 根据id获取当前界面的一个控件
	 *
	 * @param id
	 *            要查找的id
	 * @return 返回对应的控件
	 */
	public AndroidElement findById(String id) {
		return findById(id, "");
	}
	public AndroidElement findById(String id, String desc) {
		return findElementBy(By.id(id), desc);
	}

	/**
	 * 根据Name选择当前界面的有这个文字的控件
	 *
	 * @param name
	 *            内容
	 * @return 找到的控件
	 */
	public AndroidElement findByName(String name) {
		return findByName(name, "");
	}
	public AndroidElement findByName(String name, String desc) {
		return findElementBy(By.name(name), desc);
	}

	/**
	 * 根据ClassName获取当前界面的一个控件
	 *
	 * @param name
	 *            要查找的控件的类名
	 * @return 返回对应的控件
	 */
	public AndroidElement findByClassName(String name) {
		return findByClassName(name, "");
	}
	public AndroidElement findByClassName(String name, String desc) {
		return findElementBy(By.className(name), desc);
	}
	/**
	 * 根据Xpath获取当前界面的一个控件
	 *
	 * @param xpath
	 *            要查找的控件的类名
	 * @return 返回对应的控件
	 */
	public AndroidElement findByXpath(String xpath) {
		return findByXpath(xpath, "");
	}
	public AndroidElement findByXpath(String xpath, String desc) {
		return findElementBy(By.xpath(xpath), desc);
	}

	/**
	 * 获取控件
	 * 
	 * @param by
	 *            by
	 * @param str
	 *            报错提示信息
	 * @return
	 */
	public AndroidElement findElementBy(By by) {
		return findElementBy(by, "");
	}

	public AndroidElement findElementBy(By by, String str) {
		try {
			if (driver != null) {
				return (AndroidElement) driver.findElement(by);
			} else {
				print("driver为空");
			}
		} catch (NoSuchElementException e) {
			print("找不到控件:" + str + " 异常信息:" + e.getMessage());

		}

		return null;
	}

	/**
	 * 打印字符
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

	/**
	 * 根据ClassName获取多个控件
	 *
	 * @param className
	 *            控件的类名字，例如 android.widget.EditText
	 * @param num
	 *            返回的数量
	 * @return
	 */
	public List<AndroidElement> getManyElementByClassName(String className,
			int num) {
		List<AndroidElement> textFieldsList = driver
				.findElementsByClassName(className);
		List<AndroidElement> list = new ArrayList<AndroidElement>();
		try {
			for (int i = 0; i < num; i++) {
				list.add(textFieldsList.get(i));
			}
			return list;
		} catch (Exception e) {
			print("获取多个控件异常" + e.getMessage());
		}
		return null;

	}
	/**
	 * 根据Xpath获取多个控件
	 *
	 * @param Xpath
	 *            控件的xpath路径
	 * @param num
	 *            返回的数量
	 * @return
	 */
	public List<AndroidElement> getManyElementByXpath(String xpath,
			int num) {
//		System.out.println("xpath is:" + xpath);
		List<AndroidElement> xpathFieldsList = driver
				.findElementsByXPath(xpath);
		List<AndroidElement> list = new ArrayList<AndroidElement>();
		try {
			if(num == 0) num = xpathFieldsList.size();
			for (int i = 0; i < num; i++) {
				list.add(xpathFieldsList.get(i));
			}
			return list;
		} catch (Exception e) {
			print("根据XPATH获取多个控件异常" + e.getMessage());
		}
		return null;

	}
	/**
	 * 获取同className的list的控件
	 * 
	 * @param String
	 *            className
	 * @param num
	 *            取第NUM个控件
	 * @return
	 */
	public AndroidElement getLastOneElementByClassName(String className) {
		AndroidElement lastElement = null;
		if (driver != null) {
			try {
				List<AndroidElement> elements = driver
						.findElementsByClassName(className);
				if (elements != null) {
					lastElement = elements.get(elements.size() - 1);
				}
				return lastElement;
			} catch (Exception e) {
				print("getLastOneElementByClassName找不到");
				return null;
			}
		} else {
			print("getLastOneElementByClassName:" + className + " 时候driver为空");
			return null;
		}
	}

	/**
	 * 根据Id获取多个控件
	 *
	 * @param id
	 *            控件的类名字，例如 android.widget.EditText
	 * @param num
	 *            返回的数量
	 * @return
	 */
	public List<AndroidElement> getManyElementById(String id, int num) {
		if (driver != null) {
			List<AndroidElement> textFieldsList = driver.findElementsById(id);
			List<AndroidElement> list = new ArrayList<AndroidElement>();
			try {
				for (int i = 0; i < num; i++) {
					list.add(textFieldsList.get(i));
				}
				return list;
			} catch (Exception e) {
				print("获取多个控件异常" + e.getMessage());
			}
		} else {
			print("获取多个控件" + id + "时候driver为空");
		}

		return null;

	}

	/**
	 * 获取同id的list的控件
	 * 
	 * @param id
	 *            id
	 * @param num
	 *            取那一个控件
	 * @return
	 */
	public AndroidElement getListOneElementById(String id, int num) {
		if (driver != null) {
			try {
				return (AndroidElement) driver
						.findElementsById(appPackage + ":id/" + id).get(num);
			} catch (Exception e) {
				print("getListOneElementById找不到第" + num + "个控件" + id);
				return null;
			}
		} else {
			print("getListOneElementById:" + id + " 时候driver为空");
			return null;
		}
	}
	
	public void xxxx(){
		//UiAutomator原生
		//此方法的含义是先获取当前页面可滑动的元素，然后在这个元素的基础上，找到包含`上课中`这三个字的项目，再点击。
//		UiObject cl = new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(
//		cl.clickt();
		AndroidElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector())");
	}

	/**
	 * 获取屏幕的宽高
	 *
	 * @return 返回宽高的数组
	 */
	public int[] getScreen() {
		int width = driver.manage().window().getSize().getWidth();
		int height = driver.manage().window().getSize().getHeight();
		return new int[]{width, height};
	}

	/**
	 * 获取屏幕宽度
	 *
	 * @return
	 */
	public int getScreenWidth() {
		return driver.manage().window().getSize().getWidth();
	}

	/**
	 * 获取屏幕高度
	 *
	 * @return
	 */
	public int getScreenHeight() {
		return driver.manage().window().getSize().getHeight();
	}
}
