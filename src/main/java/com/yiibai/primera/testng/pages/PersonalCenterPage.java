package com.yiibai.primera.testng.pages;

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
	private final String CACHE_SIZE_ID = "txt_cache_size";//缓存总数量
	private final String CLEAR_CACHE_BTN_ID = "btn_clear_cache";
	
	// 2.我的收藏
	private final String COLLECTION_BTN_ID = "btn_center_collection";
	private final String COLLECTION_NEWS_PARENT_ID = "lin_parent";//我的收藏新闻的最外层的id,作为是否有收藏的验证
	private final String COLLECTION_PARENT_ID = "txt_newstitle1";//新闻正文的ID
	private final String COLLECTION_NEWS_TITLE_ID = "local_title";//收藏的新闻详情页面的title
	private final String COLLECTION_EDIT_BTN_ID = "img_search";//我的收藏的编辑Btn
	private final String COLLECTION_DELETE_ID = "img_delete";//新闻后面的取消收藏Btn
	private final String COLLECTION_UPDATE_ID = "img_other";//我的收藏编辑之后的保存Btn
	private final String COLLECTION_CLEAR_ALL_ID = "btn_clear_all";//取消全部收藏Btn
	private final String COLLECTION_CLEAR_ALL_SURE_ID = "quit_sure";//清空我的收藏弹出框的确认按钮 
	
	// 3.联系我们按钮
	private final String CONTACT_US_BTN_ID = "btn_center_contactus";
	
	// 4.更新版本 如果有新版本的话，会出现红色小点，提示更新到最新版本
	private final String UPDATE_BTN_ID = "btn_newver";
	
	// 5.分享给好友
	private final String SHARE_BTN_ID = "btn_sharetofriend";
	
	//6.用户退出系统
	private final String LOGOUT_BTN_ID = "btn_logout";
	private final String LOGOUT_SURE_ID = "quit_sure";
	
	//7.用户个人信息更改
	private final String PERSONAL_EDIT_LAYOUT_ID = "lin_have_login";
	private final String PERSONAL_EDIT_SUCCESSFUL = "title_name";
	private final String PERSONAL_EDIT_SUCCESSFUL_TEXT = "Perfil";
	
//	private final String PERSONAL_IMAGE_EDIT_ID = "btn_center_head";//头像
//	private final String PERSONAL_IMAPE_CAMERA_ID = "tv_take_picture";//选择系统相机ID
//	private final String PERSONAL_IMAGE_PIC_ID = "tv_choose_gallery";//选择系统图片ID
	
	private final String PERSONAL_NAME_EDIT_ID = "btn_center_name";//用户昵称
	private final String PERSONAL_NAME_OLD_ID = "my_center_name_txt";//编辑之前的用户昵称
	private final String NAME_EDIT_SAVE_ID = "btn_save_name";
	
//	private final String PERSONAL_EMAIL_EDIT_ID = "btn_center_email";//邮箱
	
	private final String PERSONAL_ACCOUNT_EDIT_ID = "btn_center_accout";//登录账号
	private final String PERSONAL_ACCOUNT_OLD_ID = "txt_center_accout";//编辑之前的用户登录账号
	private final String ACCOUNT_EDIT_SAVE_ID = "btn_save_member_name";
	
	private final String PERSONAL_PASSWORD_EDIT_ID = "btn_center_loginpassword";//密码
	private final String PASSWORD_EDIT_SAVE_ID = "btn_save";
	

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
	public AndroidElement getCacheClearBtn() {
		return findById(CLEAR_CACHE_BTN_ID);
	}
	/**
	 * 1.2获得已经存在的缓存数据的大小
	 * @return
	 */
	public double getCacheSize() {
		AndroidElement cacheSizeEl = findById(CACHE_SIZE_ID);
		String cacheSizeText = cacheSizeEl.getText();
		if(cacheSizeText.equals("0B")) {
			return 0;
		}
		String size = cacheSizeText.substring(0, cacheSizeText.length()-2);
//		System.out.println("字符串截取之后的新字符串为：" + size);
		return Double.valueOf(size);
	}
	
	/**
	 * 7.0 个人用户信息编辑的主入口
	 * @return
	 */
	public AndroidElement getPersonalEdit() {
		return findById(PERSONAL_EDIT_LAYOUT_ID);
	}
	
	public boolean isEditedSuccessed() {
		boolean isSuccessed = false;
		AndroidElement element = findById(PERSONAL_EDIT_SUCCESSFUL);
		String text = element.getText();
		if(text.equals(PERSONAL_EDIT_SUCCESSFUL_TEXT)) {
			isSuccessed = true;
		}
		return isSuccessed;
	}
	/**
	 * 7.1 用户昵称的编辑
	 * @return
	 */
	public AndroidElement getUsername() {
		return findById(PERSONAL_NAME_EDIT_ID);
	}
	public String getUsernameText() {
		String usernameText = "";
		if(isIdElementExist(PERSONAL_NAME_OLD_ID)) {
			AndroidElement usernameEl = findById(PERSONAL_NAME_OLD_ID);
			usernameText = usernameEl.getText();
		}
		
		return usernameText;
	}
	public AndroidElement getNameEditSaveBtn() {
		return findById(NAME_EDIT_SAVE_ID);
	}
	/**
	 * 7.2用户登录账号的编辑
	 * @return
	 */
	public AndroidElement getAccount() {
		return findById(PERSONAL_ACCOUNT_EDIT_ID);
	}
	public AndroidElement getAccountEditSaveBtn() {
		return findById(ACCOUNT_EDIT_SAVE_ID);
	}
	public String getOldAccountText() {
		AndroidElement accountEl = findById(PERSONAL_ACCOUNT_OLD_ID);
		return accountEl.getText();
	}
	/**
	 * 7.3 用户密码修改
	 * @return
	 */
	public AndroidElement getPassword() {
		return findById(PERSONAL_PASSWORD_EDIT_ID);
	}
	public AndroidElement getPasswordSaveBtn() {
		return findById(PASSWORD_EDIT_SAVE_ID);
	}
	/**
	 * 6.1获得用户退出按钮
	 * @return
	 */
	public AndroidElement getLogoutBtn() {
		return findById(LOGOUT_BTN_ID);
	}
	/**
	 * 6.2获得用户退出确认按钮
	 * @return
	 */
	public AndroidElement getLogoutConfirmBtn() {
		return findById(LOGOUT_SURE_ID);
	}
	/**
	 * 6.3验证用户是否退出
	 * @return
	 */
	public boolean isLogout() {
		return !isIdElementExist(LOGOUT_BTN_ID);
	}
	
	/**
	 * 2.我的收藏
	 * @return
	 */
	public AndroidElement getCollectionBtn() {
		return findById(COLLECTION_BTN_ID);
	}
	/**
	 * 2.1.1 我的收藏里面获得收藏的新闻
	 * @return
	 */
	public AndroidElement getCollectionParent() {
		return waitAutoById(COLLECTION_PARENT_ID);
	}
	/**
	 * 2.1.2我的收藏里面获得收藏新闻编辑的按钮
	 * @return
	 */
	public AndroidElement getCollectionEditBtn() {
		return waitAutoById(COLLECTION_EDIT_BTN_ID);
	}
	/**
	 * 2.1.3我的收藏里面获得收藏新闻的取消收藏按钮
	 * @return
	 */
	public AndroidElement getCollectionDeleteBtn() {
		return waitAutoById(COLLECTION_DELETE_ID);
	}
	/**
	 * 2.1.4我的收藏里面获得收藏编辑之后的保存按钮
	 * @return
	 */
	public AndroidElement getCollectionUpdateBtn() {
		return waitAutoById(COLLECTION_UPDATE_ID);
	}
	/**
	 * 判断是否有我的收藏的新闻
	 * @return
	 */
	public boolean hasCollections() {
		return isIdElementExist(COLLECTION_NEWS_PARENT_ID);
	}
	/**
	 * 2.2.1 获得清除我的收藏的按钮
	 * @return
	 */
	public AndroidElement getClearCollectionBtn() {
		return findById(COLLECTION_CLEAR_ALL_ID);
	}
	/**
	 * 2.2.2确认清空全部收藏
	 * @return
	 */
	public AndroidElement getClearCollectionSure() {
		return findById(COLLECTION_CLEAR_ALL_SURE_ID);
	}
	
	public String getNewsTitle() {
		String title = null;
		AndroidElement newsEl = findById(COLLECTION_NEWS_TITLE_ID);
		if(newsEl != null) {
			title = newsEl.getText();
		}
		return title;
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
