package com.yiibai.primera.testng.pages;


import java.util.List;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理
 * Created by ChenXiaoGuai on 2017/08/16.
 */
public class NewsDetailsPage extends PageAppium {

    //app打开时是否有可滑动的广告图
    public final String APP_WELCOME_ELEMENTS_ID = "img_coopen";
    //app主页的文字（主要是顶部显示的文字）
    public final String APP_HOME_PAGE_TEXT = "Para ti";
    
    //收藏的BTN
    public final String COLLECT_BTN_ID = "btn_collect";
    //点赞的BTN
    public final String LIKE_BTN_ID = "btn_like";
    //评论支持的BTN(给别人的评论点赞)
    public final String COMMENT_SUPP_BTN_ID = "img_com_supp";
    //评论的BTN
    public final String COMMENT_BTN_ID = "btn_comment";
    public final String COMMENT_SEND_BTN_ID = "btn_send";
    public final String COMMENT_CONTENT_INPUT_ID = "edit_comment";
    
    //图片的ID
    public final String IMAGES_ID = "img_local_big";
    public final String IMAG_COUNT_ID = "txt_allcout";//配图总数
    
    //字体设置的ID
    public final String FONT_BTN_ID = "btn_font";
    public final String FONTSIZE_RADIO_CLASS = "android.widget.RadioButton";
    
    //文章分享的ID
    public final String SHARE_BTN_ID = "img_right";
    public NewsDetailsPage(AndroidDriver<AndroidElement> driver){
        super(driver);
    }
    
    //评论提交按钮
    public AndroidElement shareBtn() {
    	return waitAutoById(SHARE_BTN_ID);
    }
    
    //评论提交按钮
    public AndroidElement fontSize() {
    	return waitAutoById(FONT_BTN_ID);
    }
    
    public AndroidElement fontSizeRadio(){
    	AndroidElement radio = null;
    	List<AndroidElement> list = getManyElementByClassName(FONTSIZE_RADIO_CLASS,4);
    	System.out.println("list size is:" + list.size());
    	for (int i = 0; i < list.size(); i++) {
    		System.out.println("radio text is:" + list.get(i).getText());
    		System.out.println("radio checked is:" + list.get(i).getAttribute("checked"));
			if(list.get(i).getAttribute("checked").equals("false")) {
				radio = list.get(i);
				break;
			}
		}
    	return radio;
    }
    
    //获得新闻配图
    public AndroidElement imageElement() {
    	return waitAutoById(IMAGES_ID);
    }
    //获得配图的总数量
    public int imageCount() {
    	int count = 1;
    	AndroidElement imageCout = waitAutoById(IMAG_COUNT_ID);
    	String text = imageCout.getText();
    	count = Integer.parseInt(text.substring(1, text.length()));
    	return count;
    }
    
    //评论按钮
    public AndroidElement commentSendBtn() {
    	return waitAutoById(COMMENT_SEND_BTN_ID);
    }
    //评论提交按钮
    public AndroidElement commentInput() {
    	return waitAutoById(COMMENT_CONTENT_INPUT_ID);
    }
    
    /**
     * 获得文章点赞按钮
     * @return 
     */
    public AndroidElement likeBtn() {
    	return waitAutoById(LIKE_BTN_ID);
    }
    
    /**
     * 文章是否点赞
     * @return 
     */
    public boolean isLiked() {
    	AndroidElement likeBtn = findById(LIKE_BTN_ID);
    	System.out.println("likeBtn is selected:" + likeBtn.isSelected());
    	if(null != likeBtn && likeBtn.isSelected()) {
    		return true;
    	}
    	return false;
    }
    /**
     * 获得文章收藏按钮
     * @return 
     */
    public AndroidElement collectBtn() {
    	return waitAutoById(COLLECT_BTN_ID);
    }
    
    /**
     * 文章是否收藏
     * @return 
     */
    public boolean isCollected() {
    	AndroidElement collectBtn = findById(COLLECT_BTN_ID);
    	System.out.println("collectBtn is selected:" + collectBtn.isSelected());
    	if(null != collectBtn && collectBtn.isSelected()) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * 获得文章评论按钮
     * @return 
     */
    public AndroidElement commentBtn() {
    	return waitAutoById(COMMENT_BTN_ID);
    }
}
