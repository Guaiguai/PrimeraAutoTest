package com.yiibai.primera.testng.pages;

import java.util.List;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理 Created by ChenXiaoGuai on 2017/09/25.
 */
public class VideoPage extends PageAppium {

//	private final String HOME_VIDEOS_TEXT = "Videos";
//	private final String HOME_MENU_CLASS = "android.widget.LinearLayout";
	private final String HOME_VIDEO_ID = "txt_tab";
	// 定位到一条video
	private final String VIDEO_DETAIL_ID = "img_enter_detail";
	// 返回
	private final String BACK_IMAGE_ID = "img_back";
	// 评论BTN
	private final String COMMENT_BTN_ID = "btn_comment";
	private final String COMMENT_SUBMIT_ID = "btn_send";
	private final String COMMENT_LIST_ID = "com_comment_content";
//	private final String COMMENT_USERNAME_ID = "username";
	// 收藏的BTN
	private final String COLLECT_BTN_ID = "btn_collect";
	// 点赞的BTN
	private final String LIKE_BTN_ID = "btn_like";
	// 字体设置的ID
	private final String FONT_BTN_ID = "btn_font";
	private final String FONT_SIZE_RADIO_CLASS = "android.widget.RadioButton";

	public VideoPage(AndroidDriver<AndroidElement> driver) {
		super(driver);
	}
	// 获得videos 菜单,切换到video
	public AndroidElement getVideosMenu() {
		return getListOneElementById(HOME_VIDEO_ID, 1);
	}
	// 取得video页面的第一个视频信息
	public AndroidElement getFirstVideo() {
		return getFirstElementById(VIDEO_DETAIL_ID);
	}

	// 返回
	public AndroidElement getBackBtn() {
		return waitAutoById(BACK_IMAGE_ID);
	}

	// 评论按钮
	public AndroidElement getCommentBtn() {
		return waitAutoById(COMMENT_BTN_ID);
	}
	// 评论提交
	public AndroidElement getCommentSubmitBtn() {
		return waitAutoById(COMMENT_SUBMIT_ID);
	}
	// 获得所有的评论的第一条
	public AndroidElement getFirstComment() {
		return getFirstElementById(COMMENT_LIST_ID);

	}
	/**
	 * 收藏
	 * 
	 * @return
	 */
	public AndroidElement getCollectBtn() {
		return waitAutoById(COLLECT_BTN_ID);
	}

	public boolean isCollected() {
		boolean isCollected = false;
		AndroidElement collectEl = getCollectBtn();
		if (collectEl != null && collectEl.isSelected())
			isCollected = true;

		return isCollected;
	}

	/**
	 * 点赞
	 * 
	 * @return
	 */
	public AndroidElement getLikeBtn() {
		return waitAutoById(LIKE_BTN_ID);
	}
	public boolean isLiked() {
		boolean isLiked = false;
		AndroidElement likeEl = getLikeBtn();
		if (likeEl != null && likeEl.isSelected())
			isLiked = true;
		return isLiked;
	}
	// 字体设置按钮
	public AndroidElement getFontSize() {
		return waitAutoById(FONT_BTN_ID);
	}
	/**
	 * 获得字体设置的单选按钮可点击的一个
	 * 
	 * @return AndroidElement
	 */
	public AndroidElement getOneUncheckedRadio() {
		AndroidElement radio = null;
		List<AndroidElement> list = getManyElementByClassName(
				FONT_SIZE_RADIO_CLASS, 4);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAttribute("checked").equals("false")) {
				radio = list.get(i);
				break;
			}
		}
		return radio;
	}

	/**
	 * 是否在视频详情页面
	 */
	public boolean isInVideoDetail() {
		boolean isTrue = false;
		if(isIdElementExist(COMMENT_BTN_ID) || isIdElementExist(LIKE_BTN_ID) || isIdElementExist(COLLECT_BTN_ID)) {
			isTrue = true;
		}
		return isTrue;
	}
}
