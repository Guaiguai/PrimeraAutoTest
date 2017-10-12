package com.yiibai.primera.testng.operation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.utils.ConstantUtils;
import com.yiibai.primera.testng.utils.MethodUtils;
import com.yiibai.primera.testng.utils.ResultUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * APP欢迎界面逻辑 作为测试的基础 
 * Created by ChenXiaoGuai on 2017/08/16.
 */

public class HomeOperate extends OperateAppium {

	private HomePage homePage;
//	private static Logger logger = Logger.getLogger(HomeOperate.class);

	AndroidDriver<AndroidElement> driver;
	
	public HomeOperate(AndroidDriver<AndroidElement> driver2) {
		super(driver2);
		homePage = new HomePage(driver2);
		this.driver = driver2;
	}
	/**
	 * 首页下部分菜单验证，主要是Inicio 与 Videos的切换
	 * @return
	 */
	public ResultUtils changeToVideo() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页！");
			return result;
		}
		clickView(homePage.getVideosMenu());
		if(homePage.isVideoPage()) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
		}
		return result;
	}
	/**
	 * 首页顶部的频道切换验证
	 * @return
	 */
	public ResultUtils changeTopMenus() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页！");
			return result;
		}
		List<AndroidElement> list = homePage.getTopMenusList();
		if(!list.isEmpty() && list.size() > 0) {
			int total = list.size();
			int num = 1;
			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getText());
				clickView(list.get(i));
				waitAuto();
				if(list.get(i).isSelected()) {
					num++;
				}
			}
			
			if(total == num) {
				result.setActual(ConstantUtils.ASSERT_TRUE);
			}
			//返回到首页
			for (int j = 0; j < num; j++) {
				swipeToRight(300);
			}
		}
		return result;
	}
	/**
	 * 首页右上角新闻菜单编辑
	 * @return
	 */
	public ResultUtils AutoChannelMenusEdit() {
		ResultUtils result = new ResultUtils();
		if(!isHomePage()) {
			result.setActual(ConstantUtils.ASSERT_FALSE);
			result.setMessage("请定位到APP首页！");
			return result;
		}
		//1.首页AUTO频道是否存在
		boolean isDisappearedBeforeEdit = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
		System.out.println("AUTO频道编辑之前的是否出现：" + isDisappearedBeforeEdit);
		//进入菜单编辑主页面
		clickView(homePage.getHomeMenuEditBtn());
		clickView(homePage.getMenuEditBtn());
		sleep(300);
		//查找到AUTO频道
		boolean isDisappearedAfterEdit = false;
		AndroidElement menuAuto = swipTilElementAppear(homePage.menuAuto(),"UP", 300);
		if(menuAuto != null) {
			AndroidElement autoBtn = null;
			autoBtn = homePage.Auto();
			if(autoBtn != null) {
				clickView(autoBtn);
				clickView(homePage.getMenuEditBtn());
				//返回首页
				clickView(homePage.getMenuBack());
				//在菜单上滑动知道Auto菜单出现
				isDisappearedAfterEdit = swipAtTopUtilElementAppear(homePage.menuAuto(), "LEFT", 300,15);
				System.out.println("AUTO频道编辑之后是否出现：" + isDisappearedAfterEdit);
			}
		}
		if ((isDisappearedBeforeEdit && !isDisappearedAfterEdit)
				|| (!isDisappearedBeforeEdit && isDisappearedAfterEdit)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("AUTO频道编辑：添加/取消成功！");
		}
		back();
		//操作完成之后定位到Parati频道
		swipAtTopUtilElementAppear(homePage.menuPara(), "RIGHT", 300, 15);
		return result;
	}
	/**
	 * 频道菜单编辑
	 * 对频道菜单进行全部取消
	 * 取得第一个频道，根据他是否已经被取消做为依据
	 * TODU
	 * @return
	 */
	public ResultUtils channelMenusMoreThanThree() {
		ResultUtils result = new ResultUtils();
		if(!isHomePage()) {
			result.setActual(ConstantUtils.ASSERT_FALSE);
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
		result.setActual(ConstantUtils.ASSERT_FALSE);
		return result;
	}
	/**
	 * 首页左上角新闻搜索
	 * 两种方式搜索
	 * @return
	 */
	public ResultUtils searchByInput(String searchedKeyword) {
		ResultUtils result = new ResultUtils();
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页");
			result.setActual(ConstantUtils.ASSERT_FALSE);
			return result;
		}
		//如果搜索关键字是中文，则正常是搜索不到新闻的
		if(MethodUtils.isContainChinese(searchedKeyword)) {
			result.setExcepted(ConstantUtils.ASSERT_FALSE);
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
	public ResultUtils searchByHistory() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage()) {
			result.setMessage("请定位到APP首页");
			return result;
		}
		clickView(homePage.getHomeSearchBtn());
		if(homePage.getSearchHistoryBtn() == null) {
			result.setMessage("暂时没有搜索历史！");
			return result;
		}
		result.setActual(ConstantUtils.ASSERT_TRUE);
		clickView(homePage.getSearchHistoryBtn());
		waitAuto();
		//对于搜索之后的结果的处理
		SearchedData(result);
		return result;
	}
	
	private void SearchedData(ResultUtils result) {
		//根据内容判断是否有搜索到信息
		if(!homePage.isSearchNoData()) {
			result.setMessage("搜索到新闻，可以查看新闻详情！");
			result.setActual(ConstantUtils.ASSERT_TRUE);
			clickView(homePage.getSearchAll());
		}else {
			result.setActual(ConstantUtils.ASSERT_FALSE);
			result.setMessage("没有搜索结果！！");
		}
		//操作完成之后返回主页
		backHome();
	}
	
	/**
	 * 清空搜索记录
	 * @return
	 */
	public ResultUtils clearSearchHistory() {
		ResultUtils result = new ResultUtils();
		if(!isHomePage()) {
			result.setActual(ConstantUtils.ASSERT_FALSE);
			result.setMessage("请定位到APP首页");
			return result;
		}
		clickView(homePage.getHomeSearchBtn());
		if(homePage.getSearchHistoryBtn() == null) {
			result.setActual(ConstantUtils.ASSERT_FALSE);
			result.setMessage("暂时没有搜索历史！");
			return result;
		}
		//点击清空搜索历史
		clickView(homePage.getClearSearchHistoryBtn());
		//验证是否清空
		if(homePage.getSearchHistoryBtn() == null) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("清空搜索历史成功！");
		}
		backHome();
		return result;
	}
	
	/**
	 * 首页刷新
	 * @return
	 */
	public ResultUtils refresh() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage()) {
			return result;
		}
		//首页刷新
		swipeToDown(300);
		sleep(3000);
		//获得刷新的控件
		int total = homePage.getRefreshedTotal();
		result.setMessage("首页刷新，刷新的新数据有：" + total + "条");
		return result;
	}
	/**
	 *  首页晨报的测试
	 * @return
	 * @throws ParseException 
	 */
	public ResultUtils MorningPaper() throws ParseException {

		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage())  return result;
//		是否应该显示早报
		Boolean morningPaperShouldExist = false;
		//当前时间和晨报显示时间对比，小于则显示晨报，大于则不显示
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date nowTime = df.parse(df.format(now));//当前时间
		Date compareTime = df.parse("10:30:00");//指定时间
		if(nowTime.getTime() - compareTime.getTime() <= 0) {
			morningPaperShouldExist = true;
			result.setMessage("nowTime <= compareTime,应该显示早报");
		}
		//抓取元素
		boolean morningPaperIsExist = false;
		AndroidElement element = homePage.getMorningPaperImageElement();
		if(element != null) {
			morningPaperIsExist = true;
		}
		//应该显示+显示了 或者 不应该显示+没有显示则通过
		if((morningPaperShouldExist && morningPaperIsExist) || (!morningPaperShouldExist && !morningPaperIsExist)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("测试通过!");
			if(morningPaperIsExist) {
				clickView(element);
				//点击一条早报信息,进入新闻详情页面，此时可以依赖NewsDetails.java
				press();
				backHome();
			}
		}
		//应该显示+没有显示 或者 不应该显示+显示则测试不通过
		if((morningPaperShouldExist && !morningPaperIsExist) || (!morningPaperShouldExist && morningPaperIsExist)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("应该显示+没有显示 或者 不应该显示+显示,则该条测试是不通过的");
		}
		return result;
	}
	
	/**
	 * 返回上一级的测试
	 * 主要是返回按钮的测试
	 */
	public ResultUtils backToPreviousMenu(){
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isHomePage())  return result;
		//1.搜索的返回
		if(homePage.getHomeSearchBtn() != null)
			clickView(homePage.getHomeSearchBtn());
		inputManyText("previous menu");
		clickView(homePage.getSearchBtn());
		//对于搜索之后的结果的处理
		if(!homePage.isSearchNoData()) {
			clickView(homePage.getSearchAll());
		}
//		return result;
		//2.频道编辑的返回
		return result;
	}
	
	/**
	 * 测试完成之后返回到首页
	 */
	private void backHome() {
		while (!isHomePage()) {
//			back();
			clickView(homePage.getBack());
		}
	}

	/**
	 * 页面操作---作为其他所有页面测试的基类调用
	 * 
	 * @return 是否成功登录到主页
	 */
	public boolean isHomePage() {
		boolean isHomePage = false;
		sleep(3000);
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
			isHomePage = false;
		}
		return isHomePage;
	}

}
