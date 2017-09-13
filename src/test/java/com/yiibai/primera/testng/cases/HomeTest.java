package com.yiibai.primera.testng.cases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.HomeOperate;


/**
 * 登录测试用例
 * Created by ChenXiaoGuai on 2017/08/15.
 */
//@Listeners({com.yiibai.primera.testng.listener.ReportListener.class})
public class HomeTest extends InitAppium {

    private HomeOperate homeOperate;

    @BeforeMethod
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
        System.out.println("首页刷新验证:"+ flag);
    }
    /**
     * 首页菜单编辑验证
     */
    @Test(priority = 1)
	public void menusEdit(){
        boolean flag = homeOperate.menusEdit();
        Assertion.verifyEquals(flag,true,"首页菜单编辑验证");
        System.out.println("首页菜单编辑验证:"+ flag);
    }
    /**
     * 首页搜索验证
     */
    @Test(priority = 3)
	public void searchByInput(){
        boolean flag = homeOperate.searchByInput();
        Assertion.verifyEquals(flag,true,"首页搜索验证---在输入框输入搜索条件搜索新闻");
        System.out.println("首页搜索验证---在输入框输入搜索条件搜索新闻:"+ flag);
    }
    /**
     * 首页搜索验证
     */
    @Test(priority = 2)
	public void searchByHistory(){
        boolean flag = homeOperate.searchByHistory();
        Assertion.verifyEquals(flag,true,"首页搜索验证---根据搜索历史搜索");
        System.out.println("首页搜索验证---根据搜索历史搜索:"+ flag);
    }
    /**
     * 首页是否显示晨报
     * @throws ParseException 
     */
    @Test(priority = 4)
	public void morningPaper() throws ParseException{
    	Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date nowTime = df.parse(df.format(now));//当前时间
		Date compareTime = df.parse("10:30:00");//指定时间
		Boolean expected = false;
		if(nowTime.getTime() - compareTime.getTime() <= 0) {
			expected = true;
		}
		boolean flag = homeOperate.MorningPaper();
		Assertion.verifyEquals(flag,expected,"首页是否显示晨报");
        System.out.println("首页是否显示晨报:"+ flag);
    }
}
