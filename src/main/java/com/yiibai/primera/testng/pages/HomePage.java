package com.yiibai.primera.testng.pages;


import java.util.List;

import org.openqa.selenium.By;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理
 * Created by ChenXiaoGuai on 2017/08/16.
 */
public class HomePage extends PageAppium {

    //app打开时是否有可滑动的广告图
    public final String APP_WELCOME_ELEMENTS_ID = "myvi";
    //app主页的文字（主要是顶部显示的文字）
    public final String APP_HOME_PAGE_TEXT = "Para ti";
  //app主页 顶部显示的文字的id
    public final String HOME_TOP_ELEMENTS_ID = "txt_news_top";
    //首页有显示文字“Periódico Matutino” 则表示有晨报
    private final String HOME_TEXT_CLASS = "android.widget.TextView";
    private final String HOME_MORNING_PAPER_TEXT = "Periódico Matutino";
    
    //首页左上角的search 搜索结果根据内容显示判断，无--SEARCH_NONE 为true 有--SEARCH_ALL
    private final String HOME_SEARCH_BTN_ID = "lin_left";
    private final String SEARCH_BTN_ID = "img_search_btn";
    private final String SEARCH_NONE = "txt_clear";
    private final String SEARCH_ALL = "lin_parent";
    private final String SEARCH_HISTORY = "txt_historyword";
    //右上角的新闻菜单编辑按钮
    private final String HOME_MENUS_EDIT_BTN_ID = "img_rightchannel";
    //菜单编辑页面的编辑按钮
    private final String MENUS_EDIT_BTN_ID = "img_search";
    private final String MENU_AUTO_TEXT = "Auto";
    private final String AUTO_BTN_XPATH = "//android.widget.TextView[@text='Auto']/following-sibling::android.widget.ImageView";
    private final String MENU_BACK_BTN_ID = "img_back";
    
    //首页新闻刷新的测试
    private final String REFRESH_BTN_ID = "btn_refresh";
    
    public HomePage(AndroidDriver<AndroidElement> driver){
        super(driver);
    }

    //右上角菜单编辑主界面
    public AndroidElement getHomeMenuEditBtn() {
    	return waitAutoById(HOME_MENUS_EDIT_BTN_ID);
    }
    public AndroidElement Auto() {
    	return findByXpath(AUTO_BTN_XPATH);
    }
    public AndroidElement getMenuEditBtn() {
    	return waitAutoById(MENUS_EDIT_BTN_ID);
    }
    public By menuAuto(){
    	return By.name(MENU_AUTO_TEXT);
    }
    public AndroidElement getMenuBack(){
    	return findById(MENU_BACK_BTN_ID);
    }
    public AndroidElement getOneMenu() {
    	return getListOneElementById(HOME_TOP_ELEMENTS_ID, 3);
    }
    
    //左上角新闻搜索
    public AndroidElement getHomeSearchBtn() {
    	return waitAutoById(HOME_SEARCH_BTN_ID);
    }
    public AndroidElement getSearchHistoryBtn() {
    	return waitAutoById(SEARCH_HISTORY);
    }
    public AndroidElement getSearchBtn() {
    	return waitAutoById(SEARCH_BTN_ID);
    }
    public AndroidElement getSearchNone() {
    	return waitAutoById(SEARCH_NONE);
    }
    public AndroidElement getSearchAll() {
    	return waitAutoById(SEARCH_ALL);
    }
    
    /**
     * 获得首页晨报测图片的元素
     * 待测
     * @return
     */
    public AndroidElement getMorningPaperImageElement() {
    	AndroidElement morningPaper = null;
//		List<AndroidElement> all = getManyElementByClassName(HOME_IMAGE_CLASS,10);
		List<AndroidElement> allText = getManyElementByClassName(HOME_TEXT_CLASS, 10);
		
//		if(all == null) {
//			System.out.println("all is empty!");
//			return null;
//		}
//		for (int i = 0; i < all.size(); i++) {
//			System.out.println("element id is:" + all.get(i).getId());
//		}
		
		if(allText == null) {
			System.out.println("allText is empty!");
			return null;
		}
		for (int i = 0; i < allText.size(); i++) {
			if(allText.get(i).getText().equals(HOME_MORNING_PAPER_TEXT)) {
				morningPaper = allText.get(i);
				break;
			}
		}
		return morningPaper;
	}
    
    /**
     * 是否在欢迎界面(app打开时的广告切换界面)
     */
    public boolean isWelcome(){
//    	System.out.println("isWelcome begin...");
    	Boolean isWelcome = false;
    	List<AndroidElement> elements = getManyElementById(APP_WELCOME_ELEMENTS_ID,1);
    	if(elements == null) {
    		isWelcome = false;
    	}else {
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
    	//
    	boolean isHomePage = false;
    	AndroidElement element = getListOneElementById(HOME_TOP_ELEMENTS_ID,1);
    	if(element != null) {
    		isHomePage = true;
    	}
        return isHomePage;
    }
}
