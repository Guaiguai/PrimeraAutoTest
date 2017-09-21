package com.yiibai.primera.testng.operation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.log4testng.Logger;

import com.sun.jna.Function;
import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.util.MethodUtil;
import com.yiibai.primera.testng.util.ConstantUtil;
import com.yiibai.primera.testng.util.ResultUtil;

import bsh.This;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * APP欢迎界面逻辑 作为测试的基础 
 * Created by ChenXiaoGuai on 2017/08/16.
 */

public class HomeOperate extends OperateAppium {

	private HomePage homePage;
	private static Logger logger = Logger.getLogger(HomeOperate.class);

	AndroidDriver<AndroidElement> driver;
	
	public HomeOperate(AndroidDriver<AndroidElement> initdriver) {
		super(initdriver);
		System.out.println(this.getClass() + "----");
		homePage = new HomePage(initdriver);
		this.driver = initdriver;
	}
	/**
	 * 首页右上角新闻菜单编辑
	 * @return
	 */
	public ResultUtil AutoChannelMenusEdit() {
		ResultUtil result = new ResultUtil();
		if(!isHomePage()) {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			result.setMessage("请定位到APP首页！");
			return result;
		}
		//进入菜单编辑主页面
		clickView(homePage.getHomeMenuEditBtn());
		clickView(homePage.getMenuEditBtn());
		sleep(300);
		AndroidElement menuAuto = swipTilElementAppear(homePage.menuAuto(),"UP", 300);
		
		if(menuAuto != null) {
			logger.info("menuAuto text is:" + menuAuto.getText());
			AndroidElement autoBtn = null;
			autoBtn = homePage.Auto();
			if(autoBtn != null) {
				logger.info("用xpath方式定位到元素");
				clickView(autoBtn);
				clickView(homePage.getMenuEditBtn());
				//TODU 需要开发配合修改，比如添加文字说明，已添加，未添加之类，根据该文字返回首页滑动菜单查找测试元素
				//返回首页
				clickView(homePage.getMenuBack());
				//在菜单上滑动知道Auto菜单出现
				boolean isDisplayed = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
				logger.info("isDisplayed:" + isDisplayed);
				result.setMessage("AUTO频道：" + isDisplayed);
				//此时需要判断是否应该显示  加菜单，显示 /减菜单，不显示就OK，反之case失败  开发配合
			}
		}
		result.setActual(ConstantUtil.ASSERT_TRUE);
		return result;
	}
	/**
	 * 频道菜单编辑
	 * 对频道菜单进行全部取消
	 * 取得第一个频道，根据他是否已经被取消做为依据
	 * TODU
	 * @return
	 */
	public ResultUtil channelMenusMoreThanThree() {
		ResultUtil result = new ResultUtil();
		if(!isHomePage()) {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			result.setMessage("请定位到APP首页！");
			return result;
		}
		//进入菜单编辑主页面
		clickView(homePage.getHomeMenuEditBtn());
		//获得第一部分没有被取消的频道，如果不存在，则表示已经取消全部频道，此时点击确认，点击返回
		System.out.println("频道是否全部取消掉：" + homePage.isDropedAllChannal());
		if(!homePage.isDropedAllChannal()) {
			clickView(homePage.getMenuEditBtn());
			//编辑频道，将“Añadir más”之上的全部点击一次
			List<AndroidElement> list = homePage.getAllAddedMenus();
			System.out.println(list.isEmpty());
			if (!list.isEmpty()) {
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					AndroidElement androidElement = (AndroidElement) iterator
							.next();
					System.out.println("元素ID为：" + androidElement.getId());
				}
			}
		}
		//TODU
		result.setActual(ConstantUtil.ASSERT_FALSE);
		return result;
	}
	/**
	 * 首页左上角新闻搜索
	 * 两种方式搜索
	 * @return
	 */
	public ResultUtil searchByInput(String searchedKeyword) {
		ResultUtil result = new ResultUtil();
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页");
			result.setActual(ConstantUtil.ASSERT_FALSE);
			return result;
		}
		//如果搜索关键字是中文，则正常是搜索不到新闻的
		if(MethodUtil.isContainChinese(searchedKeyword)) {
			result.setExcepted(ConstantUtil.ASSERT_FALSE);
		}
		
		if(homePage.getHomeSearchBtn() != null)
			clickView(homePage.getHomeSearchBtn());
		inputManyText(searchedKeyword);
		clickView(homePage.getSearchBtn());
		//对于搜索之后的结果的处理
		SearchedData(result);
		return result;
	}
	/**
	 * 首页左上角新闻搜索
	 * 两种方式搜索
	 * @return
	 */
	public ResultUtil searchByHistory() {
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页");
			return result;
		}
		clickView(homePage.getHomeSearchBtn());
		if(homePage.getSearchHistoryBtn() == null) {
			result.setMessage("暂时没有搜索历史！");
			return result;
		}
		result.setActual(ConstantUtil.ASSERT_TRUE);
		clickView(homePage.getSearchHistoryBtn());
		waitAuto();
		//对于搜索之后的结果的处理
		SearchedData(result);
		return result;
	}
	
	private void SearchedData(ResultUtil result) {
		//根据内容判断是否有搜索到信息
		if(!homePage.isSearchNoData()) {
			result.setMessage("搜索到新闻，可以查看新闻详情！");
			result.setActual(ConstantUtil.ASSERT_TRUE);
			clickView(homePage.getSearchAll());
		}else {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			result.setMessage("没有搜索结果！！");
		}
		//操作完成之后返回主页
		backHome();
	}
	
	/**
	 * 清空搜索记录
	 * @return
	 */
	public ResultUtil clearSearchHistory() {
		ResultUtil result = new ResultUtil();
		if(!isHomePage()) {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			result.setMessage("请定位到APP首页");
			return result;
		}
		clickView(homePage.getHomeSearchBtn());
		if(homePage.getSearchHistoryBtn() == null) {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			result.setMessage("暂时没有搜索历史！");
			return result;
		}
		//点击清空搜索历史
		clickView(homePage.getClearSearchHistoryBtn());
		//验证是否清空
		if(homePage.getSearchHistoryBtn() == null) {
			result.setActual(ConstantUtil.ASSERT_TRUE);
			result.setMessage("清空搜索历史成功！");
		}
		return result;
	}
	
	/**
	 * TODU首页刷新
	 * @return
	 */
	public boolean refresh() {
		if(!isHomePage()) {
			return ConstantUtil.ASSERT_FALSE;
		}
//		boolean isDisplayed = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
//		logger.info("isDisplayed:" + isDisplayed);
		return ConstantUtil.ASSERT_FALSE;
	}
	/**
	 *  首页晨报的测试
	 * @return
	 * @throws ParseException 
	 */
	public ResultUtil MorningPaper() throws ParseException {
//		Boolean flag = false;
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);
		if(!isHomePage()) {
			result.setActual(ConstantUtil.ASSERT_FALSE);
			return result;
		}
		//是否应该显示早报
		Boolean morningPaperShouldExist = false;
		//当前时间和晨报显示时间对比，小于则显示晨报，大于则不显示
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date nowTime = df.parse(df.format(now));//当前时间
		Date compareTime = df.parse("10:30:00");//指定时间
		if(nowTime.getTime() - compareTime.getTime() <= 0) {
			morningPaperShouldExist = true;
//			result.setMessage("nowTime <= compareTime,应该显示早报");
		}
		//抓取元素
		boolean morningPaperIsExist = false;
		AndroidElement element = homePage.getMorningPaperImageElement();
		if(element != null) {
			morningPaperIsExist = true;
		}
		//应该显示+显示了 或者 不应该显示+没有显示则通过
		if((morningPaperShouldExist && morningPaperIsExist) || (!morningPaperShouldExist && !morningPaperIsExist)) {
			result.setActual(ConstantUtil.ASSERT_TRUE);
			result.setMessage("应该显示+显示了 或者 不应该显示+没有显示,则该条测试是通过的");
			if(morningPaperIsExist) {
				clickView(element);
				//点击一条早报信息,进入新闻详情页面，此时可以依赖NewsDetails.java
				press();
				backHome();
			}
		}
		//应该显示+没有显示 或者 不应该显示+显示则测试不通过
		if((morningPaperShouldExist && !morningPaperIsExist) || (!morningPaperShouldExist && morningPaperIsExist)) {
			result.setActual(ConstantUtil.ASSERT_TRUE);
			result.setMessage("应该显示+没有显示 或者 不应该显示+显示,则该条测试是不通过的");
		}
		return result;
	}
	/**
	 * 测试完成之后返回到首页
	 */
	private void backHome() {
		while (!isHomePage()) {
			back();
		}
	}

	/**
	 * 页面操作---作为其他所有页面测试的基类调用
	 * 
	 * @return 是否成功登录到主页
	 */
	public boolean isHomePage() {
		boolean isHomePage = false;
		sleep(1000);
		// 是否在欢迎页面---广告动态切换的SWIPE
		if (homePage.isWelcome()) {
			print("app打开时广告动态切换的swipe...");
			for (int i = 0; i < 3; i++) {
				swipeToLeft(300);
				sleep(500);
			}
		}
		// 如果不在主页，直接退出
		if (homePage.isHomePage()) {
			isHomePage = true;
		}else {
			//
			isHomePage = false;
		}
		return isHomePage;
	}

}
