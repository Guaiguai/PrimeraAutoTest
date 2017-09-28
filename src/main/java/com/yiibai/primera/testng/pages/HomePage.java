package com.yiibai.primera.testng.pages;

import java.util.List;

import org.openqa.selenium.By;
import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理 Created by ChenXiaoGuai on 2017/08/16.
 */
public class HomePage extends PageAppium {
	// 返回上一级页面
	private final String BACK_IMAGE_ID = "img_back";
	// app打开时是否有可滑动的广告图
	public final String APP_WELCOME_ELEMENTS_ID = "myvi";
	// app主页的文字（主要是顶部显示的文字）
	public final String APP_HOME_PAGE_TEXT = "Para ti";
	// app主页 顶部显示的文字的id
	public final String HOME_TOP_ELEMENTS_ID = "txt_news_top";
	// 1.晨报验证
	// 首页有显示文字“Periódico Matutino” 则表示有晨报
	private final String HOME_TEXT_CLASS = "android.widget.TextView";
	private final String HOME_MORNING_PAPER_TEXT = "Periódico Matutino";
	// 2.新闻搜索
	// 首页左上角的search 搜索结果根据内容显示判断，无--SEARCH_NONE 为true 有--SEARCH_ALL
	private final String HOME_SEARCH_BTN_ID = "lin_left";
	private final String SEARCH_BTN_ID = "img_search_btn";
	// 如果关键字搜索不到新闻，则页面显示‘Lo siento, no hay datos’
	private final String SEARCH_NONE_ID = "lin_shownodata";
	private final String SEARCH_ALL = "lin_parent";
	private final String SEARCH_HISTORY = "txt_historyword";
	private final String SEARCH_CLEAR_ID = "txt_clear";// 清空搜索历史
	// 3.频道编辑
	// 右上角的新闻菜单编辑按钮
	private final String HOME_MENUS_EDIT_BTN_ID = "img_rightchannel";
	// 菜单编辑页面的编辑按钮
	private final String MENUS_EDIT_BTN_ID = "img_search";
	private final String MENU_AUTO_TEXT = "Auto";
	private final String AUTO_BTN_XPATH = "//android.widget.TextView[@text='Auto']/following-sibling::android.widget.ImageView";
	private final String MENU_BACK_BTN_ID = "img_back";
//	private final String MENU_ADD_MORE_ID = "txt_addmore";
//	private final String MENU_CHANNAL_TEXT_ID = "txt_name";
	private final String MENU_CHANNAL_ADDED = "channel_list_top";
//	private final String MENU_CHANNAL_DROPED = "channel_list_bottom";
	// 获得所有的已经加入的频道
//	private final String ALL_ADDED_MENUS_XPATH = "//android.support.v7.widget.RecyclerView[contains(@resource-id,'"
//			+ MENU_CHANNAL_ADDED + "')]/android.widget.LinearLayout/";
	// 4.新闻刷新
	// 首页新闻刷新的测试
//	private final String REFRESH_BTN_ID = "btn_refresh";
	private final String REFRESH_NEWS_COUNT_ID = "news_new_count";// 刷新的新闻的总数量控件（3秒消失）
	
	//5.video的切换验证
	private final String HOME_VIDEOS_MENU_ID = "txt_tab";

	public HomePage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}

	// 首页刷新
	public int getRefreshedTotal() {
		int total = 0;
		if (isIdElementExist(REFRESH_NEWS_COUNT_ID)) {
			System.out.println("控件定位到！");
			AndroidElement refreshedEl = findById(REFRESH_NEWS_COUNT_ID);
			String str = refreshedEl.getText();
			String[] strList = str.split("\\s+");
			String totalStr = strList[0];
			total = Integer.parseInt(totalStr);
		}
		return total;
	}

	// 右上角频道编辑主界面
	public AndroidElement getHomeMenuEditBtn() {
		return waitAutoById(HOME_MENUS_EDIT_BTN_ID);
	}
	// 根据显示的文字获得当前控件的Xpath
	public String getXpath(String text) {
		return "//android.widget.TextView[@text='" + text
				+ "']/following-sibling::android.widget.ImageView";
	}
	// 获得已经添加的频道的xpath
	public String getAddedMenusXpath() {
		return "//android.support.v7.widget.RecyclerView[contains(@resource-id,'"
				+ MENU_CHANNAL_ADDED + "')]"
				+ "/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/"
				+ "android.widget.ImageView[contains(@resource-id,'"
				+ MENUS_EDIT_BTN_ID + "')]";
	}

	public List<AndroidElement> getAllAddedMenus() {
		return getManyElementByXpath(getAddedMenusXpath(), 0);
	}
	// 判断频道是否已经被取消完
	public boolean isDropedAllChannal() {
		return !isIdElementExist(MENU_CHANNAL_ADDED);
	}
	// 获得所有的已经加入的菜单
	// public List<AndroidElement> getAllAddedMenus(){
	// AndroidElement element = findById(MENU_CHANNAL_ADDED);
	// String xpath = element.
	// return getManyElementByXpath(ALL_ADDED_MENUS_XPATH,0);
	// }
	// public AndroidElement getFirstChannalImage() {
	// AndroidElement firstChannal = getFirstChannalMenu();
	// String text = firstChannal.getText();
	// String xpath = getXpath(text);
	// return findByXpath(xpath);
	// }
	public AndroidElement Auto() {
		return findByXpath(AUTO_BTN_XPATH);
	}
	public AndroidElement getMenuEditBtn() {
		return waitAutoById(MENUS_EDIT_BTN_ID);
	}
	public By menuAuto() {
		return By.name(MENU_AUTO_TEXT);
	}
	public By menuPara() {
		return By.name(APP_HOME_PAGE_TEXT);
	}
	public AndroidElement getMenuBack() {
		return findById(MENU_BACK_BTN_ID);
	}
	public AndroidElement getOneMenu() {
		return getListOneElementById(HOME_TOP_ELEMENTS_ID, 3);
	}

	// 左上角新闻搜索
	public AndroidElement getHomeSearchBtn() {
		return waitAutoById(HOME_SEARCH_BTN_ID);
	}
	public AndroidElement getSearchHistoryBtn() {
		return waitAutoById(SEARCH_HISTORY);
	}
	public AndroidElement getSearchBtn() {
		return waitAutoById(SEARCH_BTN_ID);
	}
	public AndroidElement getClearSearchHistoryBtn() {
		return waitAutoById(SEARCH_CLEAR_ID);
	}
	public boolean isSearchNoData() {
		boolean isNoData = false;
		AndroidElement searchNoDataEl = waitAutoById(SEARCH_NONE_ID);
		if (searchNoDataEl != null)
			isNoData = true;
		return isNoData;
	}
	public AndroidElement getSearchAll() {
		return waitAutoById(SEARCH_ALL);
	}

	/**
	 * 获得首页晨报测图片的元素 待测
	 * 
	 * @return
	 */
	public AndroidElement getMorningPaperImageElement() {
		AndroidElement morningPaper = null;
		// List<AndroidElement> all =
		// getManyElementByClassName(HOME_IMAGE_CLASS,10);
		List<AndroidElement> allText = getManyElementByClassName(
				HOME_TEXT_CLASS, 10);

		// if(all == null) {
		// System.out.println("all is empty!");
		// return null;
		// }
		// for (int i = 0; i < all.size(); i++) {
		// System.out.println("element id is:" + all.get(i).getId());
		// }

		if (allText == null) {
			System.out.println("allText is empty!");
			return null;
		}
		for (int i = 0; i < allText.size(); i++) {
			if (allText.get(i).getText().equals(HOME_MORNING_PAPER_TEXT)) {
				morningPaper = allText.get(i);
				break;
			}
		}
		return morningPaper;
	}

	/**
	 * 是否在欢迎界面(app打开时的广告切换界面)
	 */
	public boolean isWelcome() {
		Boolean isWelcome = false;
		List<AndroidElement> elements = getManyElementById(
				APP_WELCOME_ELEMENTS_ID, 1);
		if (elements == null || elements.isEmpty()) {
			isWelcome = false;
		} else {
			isWelcome = true;
		}
		return isWelcome;
	}

	/**
	 * 判断当前界面是不是主界面
	 *
	 * @param
	 * @return 存在返回真
	 */
	public boolean isHomePage() {
		boolean isHomePage = false;
		if (!isIdElementExist(HOME_TOP_ELEMENTS_ID)) {
			return isHomePage;
		}
		AndroidElement element = findById(HOME_TOP_ELEMENTS_ID);
		if (element != null && element.getText().equals(APP_HOME_PAGE_TEXT)) {
			isHomePage = true;
		}
		return isHomePage;
	}
	/**
	 * 首页video频道的验证（与Inicio比较）
	 * @return
	 */
	public boolean isVideoPage() {
    	boolean isVideoPage = false;
    	if(!isIdElementExist(HOME_SEARCH_BTN_ID) && !isIdElementExist(HOME_MENUS_EDIT_BTN_ID)) {
    		isVideoPage = true;
    	}
    	return isVideoPage;
    }
	
	public List<AndroidElement> getTopMenusList(){
		return getManyElementById(HOME_TOP_ELEMENTS_ID, 20);
	}
	
	public AndroidElement getVideosMenu() {
		return getListOneElementById(HOME_VIDEOS_MENU_ID, 1);
	}

	public AndroidElement getBack() {
		return waitAutoById(BACK_IMAGE_ID);
	}
}
