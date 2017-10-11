package com.yiibai.primera.testng.base;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 逻辑处理父类 Created by ChenXiaoGuai on 2017/08/18.
 */

public class ImageAppium {

	AndroidDriver<AndroidElement> driver;

	/**
	 * 构造函数，初始化
	 * 
	 * @param androidDriver
	 */
	public ImageAppium(AndroidDriver<AndroidElement> androidDriver) {
		this.driver = androidDriver;
	}
	/**
	 * This Method create for take screenshot
	 * 截屏
	 * @author ChenXiaoGuai
	 * @param drivername
	 * @param filename
	 */
	public static void snapshot(TakesScreenshot drivername, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		
		String currentPath = setImgFolder();
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			FileUtil.copyFile(scrFile,
					new File(currentPath + "\\" + filename));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
	/**
	 * 设置截图存储的位置
	 * 在项目根目录下的 /snap-shop/yyyyMMdd文件夹下，按照日期分开存储
	 * @return
	 */
	public static String setImgFolder() {
		String currentPath = System.getProperty("user.dir"); // get current work
		currentPath = currentPath + "/snap-shot/" + getNowDate("yyyyMMdd");	// folder
		File myFilePath = new File(currentPath);
		if(!myFilePath.isDirectory()) {
			myFilePath.mkdir();
		}
		return currentPath;
	}
	public static BufferedImage getSubImage(BufferedImage image, int x, int y,
			int w, int h) {
		return image.getSubimage(x, y, w, h);
	}
	/**
	 * 从存放位置获取图片
	 * @param f
	 * @return
	 */
	public static BufferedImage getImageFromFile(File f) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	/**
	 * 图片的比较
	 * @param myImage
	 * @param otherImage
	 * @param percent
	 * @return
	 */
	public static boolean sameAs(BufferedImage myImage,
			BufferedImage otherImage, double percent) {
		if (otherImage.getWidth() != myImage.getWidth()) {
			return false;
		}
		if (otherImage.getHeight() != myImage.getHeight()) {
			return false;
		}
		int width = myImage.getWidth();
		int height = myImage.getHeight();
		int numDiffPixels = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (myImage.getRGB(x, y) != otherImage.getRGB(x, y)) {
					numDiffPixels++;
				}
			}
		}
		double numberPixels = height * width;
		double diffPercent = numDiffPixels / numberPixels;
		return percent <= 1.0D - diffPercent;
	}
	
	/**
	 * 执行adb命令
	 * 
	 * @param s
	 *            要执行的命令 //使用adb shell 查看devices都有哪些输入法 excuteAdbShell("adb
	 *            shell ime list -s"); //使用adb shell 切换输入法-更改为搜狗拼音，这个看你本来用的什么输入法
	 *            excuteAdbShell("adb shell ime set
	 *            com.sohu.inputmethod.sogou.xiaomi/.SogouIME");
	 *            //再次点击输入框，调取键盘，软键盘被成功调出 clickView(page.getSearch());
	 *            //点击右下角的搜索，即ENTER键 pressKeyCode(AndroidKeyCode.ENTER); //再次切回
	 *            输入法键盘为Appium unicodeKeyboard excuteAdbShell("adb shell ime set
	 *            io.appium.android.ime/.UnicodeIME");
	 */
	private void excuteAdbShell(String s) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(s);
		} catch (Exception e) {
			System.out.println("执行命令:" + s + "出错");
		}
	}
	
	
	/**
	 * 匹配截屏的文件的文件名称
	 * @param name
	 * @return
	 */
	public String _formatImg(String name) {
//		String before = "primera_";
		String fullName = name + getNowDate("yyyyMMddHHmmss") + ".png";
		return fullName;
	}
	/**
	 * 
	 * @param dateFormat  
	 * 可以为：yyyy-MM-dd yyyy-MM-dd HH:mm:ss yyyyMMddHHmmss
	 * @return
	 */
	private static String getNowDate(String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date currentTime = new Date();
		
		String dateString = formatter.format(currentTime);
		return dateString;
	}
}
