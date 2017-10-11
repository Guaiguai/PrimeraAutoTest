package com.yiibai.primera.testng.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;

import com.yiibai.primera.testng.wait.WaitAutoDriver;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * 逻辑处理父类 Created by LITP on 2016/9/22.
 */

public class OperateAppium extends WaitAutoDriver{

	AndroidDriver<AndroidElement> driver;

	// 单个触摸操作类
	TouchAction touchAction;

	// 多个触摸操作时间
	MultiTouchAction multiTouchAction;

	private final int SWIPE_DEFAULT_PERCENT = 5; // 默认滑动百分比
	
	private final int MAXSWIPNUM = 20; //屏幕滑动的最多的次数

	public final String SWIP_UP = "UP", SWIP_DOWN = "DOWN", SWIP_LEFT = "LEFT",
			SWIP_RIGHT = "RIGHT";

	public OperateAppium(AndroidDriver<AndroidElement> androidDriver) {
		super(androidDriver);
		this.driver = androidDriver;
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
	 * Click点击空格键
	 *
	 * @param ae
	 *            要点击的控件
	 * @return 返回是否点击
	 */
	public boolean clickView(AndroidElement ae) {
		return clickView(ae, "");
	}

	/**
	 * Click点击控件
	 *
	 * @param ae
	 *            控件
	 * @param str
	 *            控件的文字描述，供错误时候输出
	 * @return 返回是否存在控件
	 */
	public boolean clickView(AndroidElement ae, String str) {
		if (ae != null) {
			ae.click();
			return true;
		} else {
			print(str + "为空，点击错误");
		}
		return false;
	}

	/**
	 * 线程休眠秒数，单位秒
	 *
	 * @param s
	 *            要休眠的秒数
	 */
	public void sleep(long s) {
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	 * 获取触摸实例
	 *
	 * @return
	 */
	public TouchAction getTouch() {
		if (driver == null) {
			print("单点触摸时候driver为空");
			return null;
		} else {
			if (touchAction == null) {
				return new TouchAction(driver);
			} else {
				return touchAction;
			}

		}
	}

	/**
	 * 获取多点触摸实例
	 *
	 * @return
	 */
	public MultiTouchAction getMultiTouch() {
		if (driver == null) {
			print("多点触摸时候driver为空");
			return null;
		} else {
			if (multiTouchAction == null) {
				return new MultiTouchAction(driver);
			} else {
				return multiTouchAction;
			}

		}
	}

	/**
	 * 往控件输入字符串
	 *
	 * @param ae
	 *            要输入的控件
	 * @param str
	 *            要输入的字符串
	 */
	public void input(AndroidElement ae, String str) {
		if (ae == null) {
			print("控件为空,输入内容失败:" + str);
		} else {
			ae.sendKeys(str);
		}

	}

	public void swipeToUp(int during) {
		swipeToUp(during, SWIPE_DEFAULT_PERCENT);
	}

	/**
	 * 向上滑动，
	 *
	 * @param during
	 */
	public void swipeToUp(int during, int percent) {
		int width = getScreenWidth();
		int height = getScreenHeight();
//		System.out.println("width:" + width + ",height:" + height);
//		TouchAction swipe = new TouchAction(driver)
//				.press(width / 2, height * (percent - 1) / percent)
//				.waitAction(Duration.ofSeconds(during))
//				.moveTo(width / 2, height / percent)
//				.waitAction(Duration.ofSeconds(during)).release();
//		swipe.perform();
		 driver.swipe(width / 2, height * (percent - 1) / percent, width / 2,
		 height / percent, during);
	}

	public void swipeToDown(int during) {
		swipeToDown(during, SWIPE_DEFAULT_PERCENT);
	}

	/**
	 * 向下滑动，
	 *
	 * @param during
	 *            滑动时间
	 */
	public void swipeToDown(int during, int percent) {
		int width = getScreenWidth();
		int height = getScreenHeight();
//		TouchAction swipe = new TouchAction(driver).press(width / 2, height / percent)
//				.waitAction(Duration.ofSeconds(during))
//				.moveTo(width / 2, height * (percent - 1) / percent)
//				.waitAction(Duration.ofSeconds(during)).release();
//		swipe.perform();

		 driver.swipe(width / 2, height / percent, width / 2,
		 height * (percent - 1) / percent, during);
	}

	public void swipeToLeft(int during) {
		swipeToLeft(during, SWIPE_DEFAULT_PERCENT);
	}

	/**
	 * 向左滑动，
	 *
	 * @param during
	 *            滑动时间
	 * @param percent
	 *            位置的百分比，2-10， 例如3就是 从2/3滑到1/3
	 */
	public void swipeToLeft(int during, int percent) {
		int width = getScreenWidth();
		int height = getScreenHeight();
//		System.out.println("宽度：" + width + ",高度：" + height);
//		// System.out.println("转化的second是：" + Duration.ofSeconds(during));
//		System.out.println(
//				"起始点坐标：" + width * (percent - 1) / percent + "，" + height / 2);
//		System.out.println("滑动偏移量：" + width * (percent - 2) / percent + "，" + 0);
//		TouchAction swipe = new TouchAction(driver)
//				.press(width * (percent - 1) / percent, height / 2)
//				.waitAction(Duration.ofSeconds(during))
//				.moveTo(-width * (percent - 2) / percent, 0)
//				.waitAction(Duration.ofSeconds(during)).release();
//		swipe.perform();
//		TouchAction swipe = new TouchAction(driver)
//				.press(width / 2, height / 2)
//				.waitAction(Duration.ofSeconds(2)).moveTo(-width / 4, 0)
//				.waitAction(Duration.ofSeconds(2)).release();
//		swipe.perform();
		 driver.swipe(width * (percent - 1) / percent, height / 2,
		 width / percent, height / 2, during);
	}

	public void swipeToRight(int during) {
		swipeToRight(during, SWIPE_DEFAULT_PERCENT);
	}

	/**
	 * 向右滑动，
	 *
	 * @param during
	 *            滑动时间
	 * @param percent
	 *            位置的百分比，2-10， 例如3就是 从1/3滑到2/3
	 */
	public void swipeToRight(int during, int percent) {
		int width = getScreenWidth();
		int height = getScreenHeight();
		System.out.println("width:" + width + ",height:" + height);
//		TouchAction swipe = new TouchAction(driver)
//				.press(width / percent, height / 2)
//				.waitAction(Duration.ofSeconds(during))
//				.moveTo(width * (percent - 1) / percent, height / 2)
//				.waitAction(Duration.ofSeconds(during)).release();
		
//		TouchAction swipe = new TouchAction(driver)
//				.press(width / 2, height / 2)
//				.waitAction(Duration.ofSeconds(during))
//				.moveTo(width / 4, 0)
//				.waitAction(Duration.ofSeconds(during)).release();
//		swipe.perform();
		 driver.swipe(width / percent, height / 2,
		 width * (percent - 1) / percent, height / 2, during);
	}

	/**
	 * 打开Activity
	 *
	 * @param activityName
	 *            activity的名字
	 */
//	public void startActivity(String activityName) {
//		driver.startActivity(appPackage, activityName);
//	}

	/**
	 * 获取当前界面的所有EditText，并依次输入内容
	 *
	 * @param str
	 *            要输入的数组
	 */
	public void inputManyText(String... str) {
		List<AndroidElement> textFieldsList = driver
				.findElementsByClassName("android.widget.EditText");
		for (int i = 0; i < str.length; i++) {
			textFieldsList.get(i).click();
			clearText(textFieldsList.get(i)); // 清除内容
			textFieldsList.get(i).sendKeys(str[i]);
		}
	}

	/**
	 * 点击屏幕中间
	 */
	public void press() {
		press(getScreenWidth() / 2, getScreenHeight() / 2);
	}
	
	/*
	 * 用driver.tap(1, 10, 10, 800); 点击屏幕，
	 * 经常提示：An unknown server-side error occurred while processing the command
	 * 故用该方法替换
	 */
	public void touchScreen(){
		@SuppressWarnings("rawtypes")
		Map<String, Double> tap = new HashMap<String, Double>(); 
		tap.put("tapCount", new Double(2));
		tap.put("touchCount", new Double(1)); 
		tap.put("duration", new Double(0.5)); 
		tap.put("x", new Double(300)); 
		tap.put("y", new Double(300)); 
		driver.executeScript("mobile: tap", tap); 
	}
	/**
	 * 点击某个控件
	 *
	 * @param ae
	 *            要点击的控件
	 */
	public void press(AndroidElement ae) {
		try {
			getTouch().tap(ae).perform();
		} catch (Exception e) {
			print("tab点击元素错误" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 点击某个坐标
	 *
	 * @param x
	 * @param y
	 */
	public void press(int x, int y) {
		try {
//			TouchAction swipe = new TouchAction(driver).press(width / 2, height / 2).waitAction(Duration.ofSeconds(2))
//					.moveTo(0, height / 4).waitAction(Duration.ofSeconds(2)).release();
//			swipe.perform();
//			driver.tap(1, x, y, 500);
			 getTouch().tap(x, y).perform();
		} catch (Exception e) {
			print("tab点击元素位置异常" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 长按某个控件
	 *
	 * @param ae
	 *            要点击的控件
	 */
	public void longPress(AndroidElement ae) {
		try {
			getTouch().longPress(ae).release().perform();
		} catch (Exception e) {
			print("长按点击元素错误" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 长按某个坐标
	 *
	 * @param x
	 * @param y
	 */
	public void longPress(int x, int y) {
		try {
			getTouch().longPress(x, y).release().perform();
		} catch (Exception e) {
			print("长按点击元素错误" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 在控件上滑动
	 *
	 * @param element
	 *            要滑动的控件
	 * @param direction
	 *            方向，事件不设置默认1秒
	 */
	public void swipOnElement(AndroidElement element, String direction) {
		swipOnElement(element, direction, 1000); // 不设置时间就为2秒
	}

	/**
	 * 在某一个控件上滑动
	 *
	 * @param element
	 *            在那个元素上滑动
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 */
	public void swipOnElement(AndroidElement element, String direction,
			int during) {
//		TouchAction swipe = null;
		// 获取元素的起初xy，在左上角
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		// 获取元素的宽高
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		switch (direction) {
			case SWIP_UP :
				int startX = x + width / 2;
				// 在4/5的底部的中间向上滑动

//				swipe = new TouchAction(driver)
//						.press(startX, y + height * 4 / 5)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(startX, y + height / 5)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(startX, y + height * 4 / 5, startX, y + height / 5,
						during);
				break;
			case SWIP_DOWN :
				startX = x + width / 2;
				// 在4/5的底部的中间向上滑动
//				swipe = new TouchAction(driver)
//						.press(startX, y + height / 5)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(startX, y + height * 4 / 5)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(startX, y + height / 5, startX, y + height * 4 / 5,
						during);
				break;

			case SWIP_LEFT :
				int startY = y + width / 2;
//				swipe = new TouchAction(driver)
//						.press(x + width * 4 / 5, startY)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(x + width / 5, startY)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(x + width * 4 / 5, startY, x + width / 5, startY,
						during);
				break;
			case SWIP_RIGHT :
				startY = y + width / 2;
//				swipe = new TouchAction(driver)
//						.press(x + width / 5, startY)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(x + width * 4 / 5, startY)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(x + width / 5, startY, x + width * 4 / 5, startY,
						during);
				break;
		}
	}

	/**
	 * 在某个方向上滑动
	 *
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 * @param duration
	 *            持续时间
	 */
	public void swip(String direction, int duration) {
		switch (direction) {
			case "UP" :
				swipeToUp(duration);
				break;
			case "DOWN" :
				swipeToDown(duration);
				break;
			case "LEFT" :
				swipeToLeft(duration);
				break;
			case "RIGHT" :
				swipeToRight(duration);
				break;
		}
	}
	
	/**
	 *  在app顶部区域滑动
	 * @param direction
	 *            方向, LEFT RIGHT
	 * @param duration
	 *            持续时间
	 */
	public void swipAtTop(String direction,int during) 
	{
//		TouchAction swipe = null;
		int width = getScreenWidth();
		int height = getScreenHeight();
		int percent = SWIPE_DEFAULT_PERCENT;
		switch (direction) {
			case "LEFT" :
//				swipe = new TouchAction(driver)
//						.press(width * (percent - 1) / percent, height * 8 / 10)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(width / percent, height * 8 / 10)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(width * (percent - 1) / percent, height * 8 / 10,
						width / percent, height * 8 / 10, during);
				break;
			case "RIGHT" :
//				swipe = new TouchAction(driver)
//						.press(width / percent, height * 8 / 10)
//						.waitAction(Duration.ofSeconds(during))
//						.moveTo(width * (percent - 1) / percent, height * 8 / 10)
//						.waitAction(Duration.ofSeconds(during)).release();
//				swipe.perform();
				driver.swipe(width / percent, height * 8 / 10,
						width * (percent - 1) / percent, height * 8 / 10, during);
				break;
		}	
	}
	
	/**
	 * 在某个方向滑动直到这个元素出现
	 *
	 * @param by
	 *            控件
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 * @param duration
	 *            滑动一次持续时间
	 * @param maxSwipNum
	 *            最大滑动次数
	 */
	public boolean swipAtTopUtilElementAppear(By by, String direction, int duration,int maxSwipNum) {
		Boolean flag = false;
		while (!flag && maxSwipNum > 0) {
			try {
				flag = driver.findElement(by).isDisplayed();
			} catch (Exception e) {
				maxSwipNum--;
//				System.out.println("还剩" + maxSwipNum + "次滑屏才可能找到！");
				swipAtTop(direction, duration);
			}
		}
//		System.out.println("是否找到元素：" + flag);
		return flag;
	}

	/**
	 * 在指定次数的条件下，某个方向滑动，直到这个元素出现
	 *
	 * @param by
	 *            控件
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 * @param duration
	 *            滑动一次持续时间
	 * @param maxSwipNum
	 *            最大滑动次数
	 */
	public void swipUtilElementAppear(By by, String direction, int duration,
			int maxSwipNum) {
		int i = maxSwipNum;
		Boolean flag = true;
		while (flag) {
			try {
				if (i <= 0) {
					flag = false;
				}
				driver.findElement(by);
				flag = false;
			} catch (Exception e) {
				i--;
				swip(direction, duration);
			}
		}
	}

	/**
	 * 在某个方向滑动直到这个元素出现
	 *
	 * @param by
	 *            控件
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 * @param duration
	 *            滑动一次持续时间
	 */
	public void swipUtilElementAppear(By by, String direction, int duration) {
		Boolean flag = true;
		while (flag) {
			try {
				driver.findElement(by);
				flag = false;
			} catch (Exception e) {
				swip(direction, duration);
			}
		}
	}
	
	/**
	 * 在某个方向滑动直到这个元素出现
	 *
	 * @param by
	 *            控件
	 * @param direction
	 *            方向，UP DOWN LEFT RIGHT
	 * @param duration
	 *            滑动一次持续时间
	 */
	public AndroidElement swipTilElementAppear(By by, String direction, int duration) {
		Boolean flag = true;
		int swipNum = MAXSWIPNUM;
		AndroidElement element = null;
		while (flag && swipNum > 0) {
			try {
				element = driver.findElement(by);
				flag = false;
			} catch (Exception e) {
//				System.out.println("还有"+swipNum + "次滑动！");
				swipNum--;
				swip(direction, duration);
			}
		}
		return element;
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

	/**
	 * 逐字删除编辑框中的文字
	 * 碰到问题：无论如何都删除不了密码输入框的内容
	 * 解决方法：不管有没有内容都 都在密码框进行回退按键删除
	 * @param element
	 *            文本框架控件
	 */
	public void clearText(AndroidElement element) {
		String text = element.getText();
//		System.out.println(
//				"text length is:" + text.length() + ",text is:" + text);
		// 跳到最后 新版中sendKeyEvent()已经被删除，用pressKeyCode()取代
		// driver.pressKeyCode(KEYCODE_MOVE_END);定位到文本输入框最后
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_A, AndroidKeyCode.META_CTRL_MASK);//文本内容全选
		int size = text.length() == 0 ? 50 : text.length();
		for (int i = 0; i < size; i++) {
			// 循环后退删除
			driver.pressKeyCode(AndroidKeyCode.BACKSPACE);//删除
		}

	}
	/**
	 * 返回上一级
	 */
	public void back() {
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	/**
	 * 切换WEB页面查找元素
	 */
	public void switchToWeb() {
		Set<String> contextNames = driver.getContextHandles();
		print("contexts is:" + contextNames.toString());
		for (String contextName : contextNames) {
			if(contextName.toString().toLowerCase().contains("webview")) {
				driver.context(contextName);
				print("跳转到WEB页面，开始操作web页面");
			}
		}
	}
	/**
	 * 切换NATIVE页面查找元素
	 * "NATIVE_APP" 原生的APP
	 */
	public void switchToNative() {
		Set<String> contextNames = driver.getContextHandles();
		print("contexts is:" + contextNames.toString());
		for (String contextName : contextNames) {
			if(contextName.contains("NATIVE_APP")) {
				driver.context("NATIVE_APP");
				print("跳转到NATIVE页面，开始操作Native页面");
			}
		}
	}
}
