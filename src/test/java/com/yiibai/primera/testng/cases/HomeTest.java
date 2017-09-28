package com.yiibai.primera.testng.cases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.Assertion;
import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.HomeOperate;
import com.yiibai.primera.testng.util.MethodUtil;
import com.yiibai.primera.testng.util.ResultUtil;
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
    @Test(priority = 0,enabled = false)
	public void refresh(){
        ResultUtil result = homeOperate.refresh();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页刷新验证:"+ result.getActual());
    }

    /**
     * 首页是否显示晨报
     * @throws ParseException 
     */
    @Test(priority = 2)
	public void morningPaper() throws ParseException{
		ResultUtil result = homeOperate.MorningPaper();
		Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页验证晨报:"+ result.getActual() + ",提示信息为:" + result.getMessage());
    }
    /**
     * 首页搜索验证
     */
    @Test(priority = 3)
	public void searchByInputChinese(){
    	ResultUtil result = homeOperate.searchByInput("测试");
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println(MethodUtil.getFileLineMethod() + "-验证结果是：" + result.getActual() + ",返回信息是：" + result.getMessage());
    }
    @Test(priority = 4)
   	public void searchByInputEnglish(){
       	ResultUtil result = homeOperate.searchByInput("hello");
       	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
       	System.out.println(MethodUtil.getFileLineMethod() + "-验证结果是：" + result.getActual() + ",返回信息是：" + result.getMessage());
       }
    /**
     * 首页搜索验证
     */
    @Test(priority = 5)
	public void searchByHistory(){
    	ResultUtil result = homeOperate.searchByHistory();
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println(MethodUtil.getFileLineMethod() + "-验证结果是：" + result.getActual() + ",返回信息是：" + result.getMessage());
    }
    
    /**
     * 清空搜索历史
     */
    @Test(priority = 6)
    public void clearSearchedHistory() {
    	ResultUtil result = homeOperate.clearSearchHistory();
    	Assertion.verifyEquals(result.getActual(), result.getExcepted(), result.getMessage());
    	System.out.println(MethodUtil.getFileLineMethod() + "-验证结果是：" + result.getActual() + ",返回信息是：" + result.getMessage());
    }

    /**
     * 首页菜单编辑验证
     */
    @Test(priority = 9)
	public void autoMenuEdit(){
        ResultUtil  result = homeOperate.AutoChannelMenusEdit();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页菜单编辑验证:"+ result.getActual());
    }
    
    @Test(priority = 10)
    public void changeTopMenus() {
    	ResultUtil result = homeOperate.changeTopMenus();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页菜单顶部频道切换的验证:"+ result.getActual());
    }
    
    @Test(priority = 11)
    public void changeToVideos() {
    	ResultUtil result = homeOperate.changeToVideo();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页菜单Inicio与Videos切换的验证:"+ result.getActual());
    }
    /**
     * 首页菜单编辑验证
     */
    @Test(enabled = false)
	public void allMenusDroped(){
        ResultUtil  result = homeOperate.channelMenusMoreThanThree();
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
        System.out.println(MethodUtil.getFileLineMethod() + "-首页菜单编辑验证:"+ result.getActual());
    }
}
