package com.yiibai.primera.testng.pages;


import java.util.List;

import com.yiibai.primera.testng.base.PageAppium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * ui界面类，界面在这里处理
 * Created by ChenXiaoGuai on 2017/08/10.
 */
public class RegisterPage extends PageAppium {
	
    //注册界面的注册按钮
    public final String SING_UP_BTN_ID = "btn_sing_up";//4、点击完成注册
    //个人中心的注册按钮
    public final String CREAR_BTN_ID = "text_right";//3、点击进入注册主界面
    
    public final String SING_IN_BTN_ID = "txt_sign_in";//2、点击进入登录主界面
    // cuenta 所在的控件
    public final String CUENTA_CLASS_ELEMENT = "android.widget.LinearLayout";//1、点击进入个人中心
    

    //完成注册之后的页面的 log out  btn  验证是否注册成功
    public final String LOGOUT_BTN_ID = "btn_logout";
    //确认退出账号ID
    public final String LOGOUT_OK_BTN_ID = "quit_sure";
    //注册界面的输入框控件
    public final String INPUT_ELEMENT = "android.widget.EditText";
    //注册时输入信息不符合规范的提示框
    public final String ALERT_ID = "msgbox_title";
    
    


    //app打开时是否有可滑动的广告图
    public final String APP_WELCOME_ELEMENTS_ID = "img_coopen";
    //app主页的文字（主要是顶部显示的文字）
    public final String APP_HOME_PAGE_TEXT = "Para ti";

    public RegisterPage(AndroidDriver<AndroidElement> driver){
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
     * 3、获取 登录页面的  Crear_BTN
     * @return
     */
    public AndroidElement getCrearBtn() {
    	return waitAutoById(CREAR_BTN_ID);
    }
    
    /**
     * 4、获取 注册 页面的    确认注册按钮
     * @return
     */
    public AndroidElement getSingUpBtn() {
    	return waitAutoById(SING_UP_BTN_ID);
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
     * 获取账号密码等输入框的控件
     * @return
     */
    public List<AndroidElement> getInputElements(){
        return getManyElementByClassName(INPUT_ELEMENT,3);
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
    public boolean isRegistered() {
    	Boolean isRegistered = false;
    	if(getLogOutBtn() != null)
    		isRegistered = true;
    	return isRegistered;
    }
}
