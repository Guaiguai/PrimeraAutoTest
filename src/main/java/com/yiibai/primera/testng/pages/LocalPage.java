package com.yiibai.primera.testng.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理
 * Created by CHENXIAOGUAI on 2016/9/29.
 */
public class LocalPage extends PageAppium {

	private final String HOME_LOCAL_MENU_ID = "txt_news_top";
	private final String HOME_LOCAL_MENU_TEXT = "Local";
	
	private final String NO_NEWS_ID = "lin_shownonews";//区域没有新闻列表
	
	//区域定位ID 点击自动定位
	private final String AREA_LOCATION_ID = "btn_relocal";
	//定位显示的区域名称
	private final String AREA_NAME_ID = "txt_areaname";
	private final String AREA_NAME_TEXT = "Localización fracasa";
	//区域选择id
	private final String AREA_SELECT_ID = "btn_selectarea";
//	private final String AREA_SELECT_LIST_ID = "list_area";
	public String AREA_SELECT_LIST_OAXACA_TEXT = "México";
	
	
    //新闻不感兴趣id（不唯一）
	private final String NEWS_TITLE_ID = "txt_newstitle";
	private final String NEWS_TITLE_ID1 = "txt_newstitle1";
	private final String NEWS_TITLE_ID2 = "txt_newstitle2";
	private final String NEWS_DISLIKE_ID = "news_more_3line";
	private final String NEWS_DISLIKE_SURE_ID = "lin_dislike";
	
	//相关阅读
	private final String READINGMORE_TITLE_TEXT = "Sugerencias";
	private final String READINGMORE_NEWS_ID = "tv_reading";
//	private final String READINGMORE_NEWS_PARENT_ID = "lin_reading";
//	private final String NEWS_DETAIL_COMMENT = "btn_comment";
	private final String READINFMORE_NEWS_TITLE_ID = "local_title";
	
	//新闻刷新的文字显示
	private final String REFRESHED_TEXT = "Acaba de cargar hasta aquí, haga clic en actualizar";

    public LocalPage(AndroidDriver<AndroidElement> driver){
        super(driver);
    }
    /**
     * 页面刷新
     * @return
     */
    public By getRefreshedText() {
    	return By.name(REFRESHED_TEXT);
    }
    public AndroidElement getRefreshedTextEl() {
    	return waitAutoById(REFRESHED_TEXT);
    }
    /**
     * 更多阅读部分
     */
    public AndroidElement getFirstNews() {
    	AndroidElement element = getFirstElementById(NEWS_TITLE_ID);
    	if(element == null)
    		element = getFirstElementById(NEWS_TITLE_ID1);
    	if(element == null)
    		element = getFirstElementById(NEWS_TITLE_ID2);
    	return element;
    }
    public String getNewsTitle() {
    	AndroidElement element = waitAutoById(READINFMORE_NEWS_TITLE_ID);
    	return element.getText();
    }
    //更多阅读部分的title
    public By getReadingMoreTile() {
    	return By.name(READINGMORE_TITLE_TEXT);
    }
    //获得第一条的更多阅读的新闻
    public AndroidElement getFirstReadingMoreNews() {
    	return waitAutoById(READINGMORE_NEWS_ID);
    }
    /**
     * 获得页面的第一条新闻标题
     * @return
     */
    public String getFirstNewsTitle() {
    	return this.getFirstNews().getText();
    }
    /**
     * 获得第一条新闻的“不感兴趣”按钮
     * @return
     */
    public AndroidElement getFirstDislikeBtn() {
    	return getFirstElementById(NEWS_DISLIKE_ID);
    }
    public AndroidElement getDislikeSureBtn() {
    	return findById(NEWS_DISLIKE_SURE_ID);
    }
    
    /**
     * 自动定位Btn
     * @return
     */
    public AndroidElement getLocationBtn() {
    	return waitAutoById(AREA_LOCATION_ID);
    }
    /**
     * 区域定位下来单
     * @return
     */
    public AndroidElement getLocationSelectBtn() {
    	return waitAutoById(AREA_SELECT_ID);
    }
    
    public AndroidElement getOaxaca() {
    	return findByName(AREA_SELECT_LIST_OAXACA_TEXT);
    }
    //区域名称
    public String getAreaNameText() {
    	AndroidElement element = findById(AREA_NAME_ID);
    	return element.getText();
    }
    //自动定位后显示的区域
    public String getAutoAreaName() {
    	AndroidElement element = findByName(AREA_NAME_TEXT);
    	return element.getText();
    }
    public By getAreaName() {
    	return By.name(AREA_NAME_TEXT);
    }
    /**
     * 是否有新闻列表
     * @return
     */
    public boolean hasNewsList() {
    	return !isIdElementExist(NO_NEWS_ID);
    }
    /**
     * 定位到Local频道
     * @return
     */
    public AndroidElement getLocalMenu() {
    	AndroidElement localMenu = null;
    	
		List<AndroidElement> list = getManyElementById(HOME_LOCAL_MENU_ID, 4);
		if(list.size() > 0 && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				String text = list.get(i).getText();
				if(text != null && text != "" && text.equals(HOME_LOCAL_MENU_TEXT)) {
					localMenu = list.get(i);
					break;
				}
			}
		}
		return localMenu;
	}
    /**
     * 验证是否在local频道页面
     * 根据区域选择器判断
     * @return
     */
    public boolean isLocalPage() {
    	return isIdElementExist(AREA_LOCATION_ID);
    }
    
}
