package com.yiibai.primera.testng.cases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.HomeOperate;

/**
 * 登录测试用例
 * Created by ChenXiaoGuai on 2017/08/15.
 */
@Listeners({com.yiibai.primera.testng.listener.ReportListener.class})
public class HomeTest extends InitAppium {

    private HomeOperate homeOperate;


    @BeforeClass
    public void initDriver(){
        Assert.assertNotNull(driver);
        homeOperate = new HomeOperate(driver);
    }

    /**
     * 首页刷新验证
     */
    @Test(enabled = false)
	public void refresh(){
        boolean flag = homeOperate.refresh();
        Assertion.verifyEquals(flag,false,"首页刷新验证");
        print("首页刷新验证:"+ flag);
    }
    /**
     * 首页菜单编辑验证
     */
    @Test(priority = 1)
	public void menusEdit(){
        boolean flag = homeOperate.menusEdit();
        Assertion.verifyEquals(flag,true,"首页菜单编辑验证");
        print("首页菜单编辑验证:"+ flag);
    }
    /**
     * 首页搜索验证
     */
    @Test(priority = 2)
	public void search1(){
        boolean flag = homeOperate.search(1);
        Assertion.verifyEquals(flag,true,"首页搜索验证");
        print("首页搜索验证:"+ flag);
    }
    /**
     * 首页搜索验证
     */
    @Test(priority = 3)
	public void search2(){
        boolean flag = homeOperate.search(2);
        Assertion.verifyEquals(flag,true,"首页搜索验证");
        print("首页搜索验证:"+ flag);
    }
    /**
     * 首页是否显示晨报
     * @throws ParseException 
     */
    @Test(priority = 4)
	public void morningPaper() throws ParseException{
        boolean flag = homeOperate.MorningPaper();
        Assertion.verifyEquals(flag,false,"首页是否显示晨报");
        print("首页是否显示晨报:"+ flag);
    }
}
