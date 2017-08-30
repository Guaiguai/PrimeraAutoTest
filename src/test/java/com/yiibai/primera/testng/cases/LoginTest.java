package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.LoginOperate;

/**
 * 登录测试用例
 * Created by ChenXiaoGuai on 2017/08/15.
 */

public class LoginTest extends InitAppium {

    private LoginOperate loginOperate;


    @BeforeClass
    public void initDriver(){
        Assert.assertNotNull(driver);
        loginOperate = new LoginOperate(driver);
    }

    /**
     * 测试帐号对 密码不对情况
     */
    @Test(priority = 0)
    public void loginErrorUser(){
        boolean flag = loginOperate.login("18091969298","iuhihj");
        Assertion.verifyEquals(flag,false,"帐号对,密码错误是否登录成功");
        print("帐号密码不对情况登录:"+ flag);
    }

    /**
     * 测试帐号密码规格不对情况
     */
    @Test(priority = 1)
    public void loginErrorNum(){
        boolean flag = loginOperate.login("1319262asdfsddsa","dfgd#@$1234fgdfds");
        Assertion.verifyEquals(flag,false,"帐号密码格式不对是否登录成功");
        print("帐号密码格式不对情况登录:"+ flag);
    }


//    /**
//     * 测试帐号密码为中文情况
//     */
//    @Test(priority = 2)
//    public void loginChinese(){
//        boolean flag = loginOperate.login("帐号","密码");
//        Assertion.verifyEquals(flag,false,"帐号密码为中文是否登录成功");
//        print("帐号密码为中文情况登录:"+ flag);
//    }
//
//
//
//    /**
//     * 测试帐号密码为空情况
//     */
//    @Test(priority = 3)
//    public void loginEmpty(){
//        boolean flag = loginOperate.login("","");
//        Assertion.verifyEquals(flag,false,"帐号密码为空是否登录成功");
//        print("帐号密码为空情况登录:"+ flag);
//    }
//    /**
//     * 测试帐号密码正确情况
//     */
//    @Test(priority = 4)
//    public void loginConfim() {
//        boolean flag = loginOperate.login("18091969298","18091969298");
//        print("帐号密码对的情况登录:"+ flag);
//        Assert.assertTrue(flag,"帐号密码对的情况登录");
//
//    }

}
