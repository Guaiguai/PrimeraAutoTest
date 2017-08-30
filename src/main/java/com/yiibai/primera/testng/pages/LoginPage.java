package com.yiibai.primera.testng.pages;


import java.util.List;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理
 * Created by LITP on 2016/9/22.
 */
public class LoginPage extends PageAppium {
	
    //个人中心的注册按钮
    public final String LOGIN_BTN_ID = "login";//3、点击进入注册主界面
    
    public final String SING_IN_BTN_ID = "txt_sign_in";//2、点击进入登录主界面
    // cuenta 所在的控件
    public final String CUENTA_CLASS_ELEMENT = "android.widget.LinearLayout";//1、点击进入个人中心
    

    //完成登录之后的页面的 log out  btn  验证是否登录成功
    public final String LOGOUT_BTN_ID = "btn_logout";
    //确认退出账号ID
    public final String LOGOUT_OK_BTN_ID = "quit_sure";
    //注册界面的输入框控件
    public final String INPUT_ELEMENT = "android.widget.EditText";
    //登录时输入信息不符合规范的提示框
    public final String ALERT_ID = "msgbox_title";

    //app打开时是否有可滑动的广告图
    public final String APP_WELCOME_ELEMENTS_ID = "img_coopen";
    //app主页的文字（主要是顶部显示的文字）
    public final String APP_HOME_PAGE_TEXT = "Para ti";

    public LoginPage(AndroidDriver<AndroidElement> driver){
        super(driver);
    }
    /**
     * 1、获取Home界面的右下角Cuenta元素
     * @return
     */
    public AndroidElement getCuentaBtn() {
    	return getLastOneElementByClassName(CUENTA_CLASS_ELEMENT);
    }
    
    /**
     * 2、获取 个人中心 页面的  Registrarse_BTN
     * @return
     */
    public AndroidElement getSignInBtn() {
    	return waitAutoById(SING_IN_BTN_ID);
    }
    
    /**
     * 2.1、获取 个人中心（如果已有账号的情况） 页面的  LogOut_BTN
     * @return
     */
    public AndroidElement getLogOutBtn() {
//    	System.out.println(driv);
    	return waitAutoById(LOGOUT_BTN_ID);
    }

    
    /**
     * 2.2、获取 退出登录弹出框的OK 按钮
     * @return
     */
    public AndroidElement getLogOutOkBtn() {
    	return waitAutoById(LOGOUT_OK_BTN_ID);
    }
    
    /**
     * 3、获取 登录主界面的登录 按钮
     * @return
     */
    public AndroidElement getLoginBtn() {
    	return waitAutoById(LOGIN_BTN_ID);
    }
    /**
     * 获取账号密码等输入框的控件
     * @return
     */
    public List<AndroidElement> getInputElements(){
        return getManyElementByClassName(INPUT_ELEMENT,3);
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
        return isTextExist(APP_HOME_PAGE_TEXT);
    }
    /**
     * 注册时输入信息不符合规范时的弹出信息
     * @return
     */
    public boolean isAlert() {
    	return isIdElementExist(ALERT_ID);
    }
    /**
     * 判断用户是否已经注册过，即个人中心的已经登录界面
     * @return
     */
    public boolean isLogined() {
    	
    	return isIdElementExist(LOGOUT_BTN_ID);
    }
}
