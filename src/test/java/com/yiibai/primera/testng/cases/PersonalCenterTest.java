package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.PersonalCenterOperate;
import com.yiibai.primera.testng.util.ResultUtil;

/**
 * 个人中心页面测试用例
 * 3.1、右上角设置的测试（包含字体大小设置、选择流量方式--主要体现在图片大小、是否开启接收通知的测试、缓存的清除的测试）
 * 3.2、我的收藏的测试  点击进去查看我的收藏，也可以编辑我的收藏--取消收藏、清空我的收藏
 * 3.3、版本更新的测试---如果有更新的版本，会显示红点
 * 3.4、分享按钮的测试
 * 
 * Created by ChenXiaoGuai on 2017/08/16.
 */
public class PersonalCenterTest extends InitAppium {

    private PersonalCenterOperate personalCenterOperate;


    @BeforeMethod
    public void initDriver(){
        Assert.assertNotNull(driver);
        personalCenterOperate = new PersonalCenterOperate(driver);
    }
    /**
     * 编辑用户昵称
     * 昵称限制在4-25个字符
     */
    @Test(priority = 1)
    public void editUsernameConfirm() {
    	ResultUtil result = personalCenterOperate.personalEditNameAndAccount("ceshi",null);
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试编辑用户昵称，返回信息：" + result.getMessage());
    }
    @Test(priority = 0)
    public void editUsernameLessThanFourChars() {
    	ResultUtil result = personalCenterOperate.personalEditNameAndAccount("cc",null);
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试编辑用户昵称，返回信息：" + result.getMessage());
    }
    /**
     * 编辑用户登陆账号
     * 登录账号唯一性验证 6-20个字符
     */
    @Test(priority = 2)
    public void editAccountExited() {
    	ResultUtil result = personalCenterOperate.personalEditNameAndAccount(null,"15906652401");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试登陆账号编辑---登陆账号已经存在，返回信息：" + result.getMessage());
    }
    @Test(priority = 3)
    public void editAccountLessThanSixChars() {
    	ResultUtil result = personalCenterOperate.personalEditNameAndAccount(null,"55088");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试登陆账号编辑---登陆账号字符长度小于6，返回信息：" + result.getMessage());
    }
    @Test(priority = 4)
    public void editAccountConfirm() {
    	ResultUtil result = personalCenterOperate.personalEditNameAndAccount(null,"18091969298");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试登陆账号编辑---数据正确，返回信息：" + result.getMessage());
    }
    /**
     * 修改用户登录密码
     * 长度在6-25个字符，且修改前后密码不同
     */
    @Test(priority = 5)
    public void editPasswordEqualOld() {
    	ResultUtil result = personalCenterOperate.personalEditPwd(null, null, null);
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---和上次密码一致，返回信息：" + result.getMessage());
    }
    @Test(priority = 6)
    public void editPasswordWrongOldPwd() {
    	ResultUtil result = personalCenterOperate.personalEditPwd("15906652401", null, null);
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---原密码错误，返回信息：" + result.getMessage());
    }
    @Test(priority = 7)
    public void editPasswordLessThanSixChars() {
    	ResultUtil result = personalCenterOperate.personalEditPwd(null, "1809", "1809");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---密码长度小于6，返回信息：" + result.getMessage());
    }
    @Test(priority = 8)
    public void editPasswordMoreThanTwentyFiveChars() {
    	ResultUtil result = personalCenterOperate.personalEditPwd(null, "18091969298180919692981809196", "18091969298180919692981809196");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---密码长度大于25，返回信息：" + result.getMessage());
    }
    @Test(priority = 9)
    public void editPasswordNotEqualWithConfirmed() {
    	ResultUtil result = personalCenterOperate.personalEditPwd(null, "18091969298", "15906652401");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---新密码和确认新密码不一致，返回信息：" + result.getMessage());
    }
    @Test(priority = 11)
    public void editPasswordConfirm() {
    	ResultUtil result = personalCenterOperate.personalEditPwd(null, "18091969298", "18091969298");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户密码更改---数据正确，返回信息：" + result.getMessage());
    }
    /**
     * 清空缓存
     */
    @Test(priority = 12)
    public void clearCache() {
    	ResultUtil result = personalCenterOperate.clearCache();
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试清空缓存信息，返回：" + result.getMessage());
    }
    /**
     * 我的收藏的测试
	 * 取消收藏的第一条新闻验证
     */
    @Test(priority = 13)
    public void clearFirstCollection(){
        ResultUtil result = personalCenterOperate.clearFirstCollection();
        Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试取消第一条收藏的新闻，返回：" + result.getMessage());
    }
    /**
     * 我的收藏的测试
	 * 取消全部收藏的新闻
     */
    @Test(priority = 14)
    public void clearAllCollection(){
        ResultUtil result = personalCenterOperate.clearAllCollection();
        Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试清空缓存信息，返回：" + result.getMessage());
    }
    
    /**
     * 用户退出登录APP
     */
    @Test(priority = 15)
    public void Logout() {
    	ResultUtil result = personalCenterOperate.logout();
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println("测试用户退出登录，返回信息：" + result.getMessage());
    }
}
