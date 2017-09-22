package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.LoginOperate;
import com.yiibai.primera.testng.util.ResultUtil;

/**
 * 登录测试用例
 * Created by ChenXiaoGuai on 2017/08/15.
 */
public class LoginTest extends InitAppium {

    private LoginOperate loginOperate;

    @BeforeMethod
    public void initDriver(){
        Assert.assertNotNull(driver);
        loginOperate = new LoginOperate(driver);
    }
    /**
     * 测试帐号对 密码不对情况
     */
    @Test(priority = 0)
    public void loginErrorUser(){
        ResultUtil result = loginOperate.login("18091969298","iuhihj");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println("帐号密码不对情况登录:"+ result.getActual());
    }

    /**
     * 测试帐号密码规格不对情况
     */
    @Test(priority = 1)
    public void loginErrorNum(){
        ResultUtil result = loginOperate.login("1319262asdfsddsa","dfgd#@$1234fgdfds");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println("帐号密码格式不对情况登录:"+ result.getActual());
    }


    /**
     * 测试帐号密码为中文情况
     */
    @Test(priority = 2)
    public void loginChinese(){
        ResultUtil result = loginOperate.login("帐号","密码");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println("帐号密码为中文情况登录:"+ result.getActual());
    }



    /**
     * 测试帐号密码为空情况
     */
    @Test(priority = 3,enabled = false)
    public void loginEmpty(){
        ResultUtil result = loginOperate.login("","");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println("帐号密码为空情况登录:"+ result.getActual());
    }
    /**
     * 测试帐号密码正确情况
     */
    @Test(priority = 4)
    public void loginConfim() {
        ResultUtil result = loginOperate.login("18091969298","18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        Assert.assertTrue(result.getActual(),"帐号密码对的情况登录");

    }
    /**
     * 测试帐号密码正确情况
     */
    @Test(priority = 5)
    public void loginConfim2() {
        ResultUtil result = loginOperate.login("18091969398","18091969398");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        Assert.assertTrue(result.getActual(),"帐号密码对的情况登录");

    }

}
