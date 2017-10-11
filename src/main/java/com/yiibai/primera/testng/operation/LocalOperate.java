package com.yiibai.primera.testng.operation;

import java.util.Arrays;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.pages.LocalPage;
import com.yiibai.primera.testng.util.ConstantUtil;
import com.yiibai.primera.testng.util.ResultUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 当地频道逻辑处理 Created by CHENXIAOGUAI on 2017/9/29. 1.1 区域定位 1.2 新闻不感兴趣
 */

public class LocalOperate extends OperateAppium {

	private LocalPage localPage;

	private HomePage homePage;

	private String[] autoAreaList = {"Guerrero", "Localización fracasa",
			"Puebla"};

	AndroidDriver<AndroidElement> driver;

	public LocalOperate(AndroidDriver<AndroidElement> driver) {
		super(driver);
		localPage = new LocalPage(driver);
		homePage = new HomePage(driver);
		this.driver = driver;
	}
	
	public ResultUtil readingMore() {
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);

		if (!isLocalPage()) {
			result.setMessage("请定位到Local频道！");
			return result;
		}
		if(!localPage.hasNewsList()) {
			String area = localPage.getAreaNameText();
			result.setMessage("Local频道的定位坐标："+area+"，没有新闻列表");
			return result;
		}
		//0.定位第一条新闻的详情页面
		clickView(localPage.getFirstNews());
		String newsTitle = localPage.getNewsTitle();
		System.out.println("新闻标题：" + newsTitle);
		//1.滑动到出现相关阅读部分
		AndroidElement title = swipTilElementAppear(localPage.getReadingMoreTile(), SWIP_UP, 300);
		if(title == null) {
			result.setMessage("程序出错！");
			return result;
		}
		swipeToUp(300);
		//2.点击相关阅读的第一条相关新闻浏览
		clickView(localPage.getFirstReadingMoreNews());
		//3.可以正常浏览则表示测试通过(判断依据：新闻详情的新闻标题不一致)
		String readingMoreTitle = localPage.getNewsTitle();
		System.out.println("更多阅读的新闻标题：" + readingMoreTitle);
		if(!newsTitle.equals(readingMoreTitle)) {
			result.setActual(ConstantUtil.ASSERT_TRUE);
		}
		if(!isLocalPage()) {
			back();
		}
		return result;
	}

	/**
	 * 定位的验证 自动定位 + 选择定位
	 * 
	 * @return
	 */
	public ResultUtil location() {
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);

		if (!isLocalPage()) {
			result.setMessage("请定位到Local频道！");
			return result;
		}
		String areaNameBefore = localPage.getAreaNameText();
		// 如果定位显示的区域为Oaxaca,则先自动定位
		if (areaNameBefore.equals(localPage.AREA_SELECT_LIST_OAXACA_TEXT)) {
			locationByAuto();
			sleep(500);
			// 操作完成之后的验证
			String areaNameAfter = localPage.getAreaNameText();
			if (!areaNameBefore.equals(areaNameAfter)
					&& Arrays.asList(autoAreaList).contains(areaNameAfter)) {
				result.setActual(ConstantUtil.ASSERT_TRUE);
				result.setMessage("自动定位成功！");
			}
		} else{
			// 如果定位显示的区域为Localización fracasa,则下拉定位
			locationByChoise();
			sleep(500);
			// 操作完成之后的验证
			String areaNameAfter = localPage.getAreaNameText();
			if (!areaNameBefore.equals(areaNameAfter) && areaNameAfter
					.equals(localPage.AREA_SELECT_LIST_OAXACA_TEXT)) {
				result.setActual(ConstantUtil.ASSERT_TRUE);
				result.setMessage("选择定位成功！");
			}
		}

		return result;
	}

	/**
	 * 新闻不再感兴趣
	 * 
	 * @return
	 */
	public ResultUtil cancleUnlikednews() {
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);

		if (!isLocalPage()) {
			result.setMessage("请定位到Local频道！");
			return result;
		}
		if(!localPage.hasNewsList()) {
			String area = localPage.getAreaNameText();
			result.setMessage("Local频道的定位坐标："+ area +"，没有新闻列表");
			return result;
		}
		String firstTitleBefore = localPage.getFirstNewsTitle();
		clickView(localPage.getFirstDislikeBtn());
		clickView(localPage.getDislikeSureBtn());
		waitAuto(3000);
		String firstTitleAfter = localPage.getFirstNewsTitle();
		
		System.out.println("前：" + firstTitleBefore);
		System.out.println("后：" + firstTitleAfter);
		if (!firstTitleBefore.equals(firstTitleAfter)) {
			result.setActual(ConstantUtil.ASSERT_TRUE);
			result.setMessage("测试通过！");
		}
		return result;
	}

	/**
	 * 刷新：点击文字“Acaba de cargar hasta aquí, haga clic en actualizar” 进行刷新
	 * 
	 * @return
	 */
	public ResultUtil refreshByKeyword() {
		ResultUtil result = new ResultUtil();
		result.setActual(ConstantUtil.ASSERT_FALSE);

		if (!isLocalPage()) {
			result.setMessage("请定位到Local频道！");
			return result;
		}
		//0.刷新下页面
		swipeToDown(300);
		//1.向下滑动知道刷新的文字出现为止
		AndroidElement element = swipTilElementAppear(localPage.getRefreshedText(), SWIP_UP, 300);
		if (element == null) {
			result.setMessage("Local频道的新闻列表中没有出现'Acaba de cargar hasta aquí, haga clic en actualizar'!");
			return result;
		}
		//2.点击文字，刷新页面
		clickView(localPage.getRefreshedTextEl());
		sleep(3000);
		result.setActual(ConstantUtil.ASSERT_TRUE);
		return result;
	}

	/**
	 * 定位到local频道主页
	 * 
	 * @return
	 */
	public boolean isLocalPage() {
		Boolean flag = false;
		sleep(1000);
		if (homePage.isHomePage()) {
			// print("在App主页面..........");
			// 在主页面定位Local频道进去，而不是视频
			clickView(localPage.getLocalMenu());
		}
		// 验证
		if (localPage.isLocalPage()) {
			flag = true;
		}
//		System.out.println(flag);
		return flag;
	}

	/**
	 * 定位的验证方式一：下拉选择
	 * 
	 * @return
	 */
	private void locationByChoise() {
		// 1.点击下拉框
		clickView(localPage.getLocationSelectBtn());
		// 2.选择Oaxaca区域
		clickView(localPage.getOaxaca());
		sleep(300);
	}

	/**
	 * 定位的验证方式一：自动定位
	 * 
	 * @return
	 */
	private void locationByAuto() {
		// 1.点击自动定位按钮
		clickView(localPage.getLocationBtn());
		// 2.等待数秒，知道定位的元素出现
		waitAuto(localPage.getAreaName(), 10000);
	}

}
