package com.yiibai.primera.testng.pages;

import java.util.List;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理 Created by LITP on 2016/9/22.
 */
public class PersonalCenterPage extends PageAppium {

	// cuenta 所在的控件
	public final String CUENTA_CLASS_ELEMENT = "android.widget.LinearLayout";// 点击进入个人中心
	///1.设置
	private final String SETTING_BTN_ID = "btn_setting";
	
	// 2.我的收藏
	private final String COLLECTION_BTN_ID = "btn_center_collection";
	private final String COLLECTION_PARENT_ID = "txt_newstitle1";
	private final String COLLECTION_EDIT_BTN_ID = "img_search";
	private final String COLLECTION_DELETE_ID = "img_delete";
	private final String COLLECTION_UPDATE_ID = "img_other";
	
	// 3.联系我们按钮
	private final String CONTACT_US_BTN_ID = "btn_center_contactus";
	
	// 4.更新版本 如果有新版本的话，会出现红色小点，提示更新到最新版本
	private final String UPDATE_BTN_ID = "btn_newver";
	
	// 5.分享给好友
	private final String SHARE_BTN_ID = "btn_sharetofriend";

	public PersonalCenterPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	/**
	 * 获取Home界面的右下角Cuenta元素
	 * 
	 * @return
	 */
	public AndroidElement getCuentaBtn() {
		return getLastOneElementByClassName(CUENTA_CLASS_ELEMENT);
	}
	/**
	 * 1.设置按钮
	 * @return
	 */
	public AndroidElement getSettingBtn() {
		return findById(SETTING_BTN_ID);
	}
	/**
	 * 2.我的收藏
	 * @return
	 */
	public AndroidElement getCollectionBtn() {
		return findById(COLLECTION_BTN_ID);
	}
	/**
	 * 2.1 我的收藏里面获得收藏的新闻
	 * @return
	 */
	public AndroidElement getCollectionParent() {
		return waitAutoById(COLLECTION_PARENT_ID);
	}
	/**
	 * 2.2我的收藏里面获得收藏新闻编辑的按钮
	 * @return
	 */
	public AndroidElement getCollectionEditBtn() {
		return waitAutoById(COLLECTION_EDIT_BTN_ID);
	}
	/**
	 * 2.3我的收藏里面获得收藏新闻的取消收藏按钮
	 * @return
	 */
	public AndroidElement getCollectionDeleteBtn() {
		return waitAutoById(COLLECTION_DELETE_ID);
	}
	/**
	 * 2.4我的收藏里面获得收藏编辑之后的保存按钮
	 * @return
	 */
	public AndroidElement getCollectionUpdateBtn() {
		return waitAutoById(COLLECTION_UPDATE_ID);
	}
	/**
	 * 3.联系我们
	 * @return
	 */
	public AndroidElement getContactUsBtn() {
		return findById(CONTACT_US_BTN_ID);
	}
	/**
	 * 4.版本更新
	 * @return
	 */
	public AndroidElement getUpdateBtn() {
		return findById(UPDATE_BTN_ID);
	}
	/**
	 * 5.APP分享给好友
	 * @return
	 */
	public AndroidElement getShareBtn() {
		return findById(SHARE_BTN_ID);
	}
	
	
}
