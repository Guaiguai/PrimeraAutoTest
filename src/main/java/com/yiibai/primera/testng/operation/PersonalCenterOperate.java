package com.yiibai.primera.testng.operation;

import com.yiibai.primera.testng.base.OperateAppium;
import com.yiibai.primera.testng.base.UiAutomatorAppium;
import com.yiibai.primera.testng.pages.HomePage;
import com.yiibai.primera.testng.pages.PersonalCenterPage;
import com.yiibai.primera.testng.utils.ConstantUtils;
import com.yiibai.primera.testng.utils.ResultUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 个人中心 Created by ChenXiaoGuai on 2017/08/23.
 */

public class PersonalCenterOperate extends OperateAppium {

	private PersonalCenterPage personalCenterPage;
	
	private HomePage homePage;
	
	private String BASE_ACCOUNT = "18091969298";
	private String BASE_PWD = "18091969298";

	AndroidDriver<AndroidElement> driver;

	public PersonalCenterOperate(AndroidDriver<AndroidElement> driver2) {
		super(driver2);
		personalCenterPage = new PersonalCenterPage(driver2);
		homePage = new HomePage(driver2);
		this.driver = driver2;
	}
	/**
	 * 系统设置中，清除缓存
	 * @return
	 */
	public ResultUtils clearCache() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		
		if(!isPersonalCenter()) {
			result.setMessage("请定位到APP个人中心界面！");
			return result;
		}
		//1.点击进入系统设置主界面
		clickView(personalCenterPage.getSettingBtn());
		//2.获得缓存数据
		double cacheSize = personalCenterPage.getCacheSize();
		if(cacheSize != 0) {
			clickView(personalCenterPage.getCacheClearBtn());
		}
		double cacheSizeAfterClear = personalCenterPage.getCacheSize();
		if(cacheSizeAfterClear == 0) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("清空缓存成功！");
		}else {
			result.setMessage("清空缓存失败！");
		}
		backHome();
		return result;
	}
	
	/**
	 * 用户基本信息编辑  昵称  登陆账号
	 * @param username
	 * @param oldAccount
	 * @param account
	 * @return
	 */
	public ResultUtils personalEditNameAndAccount(String username,String account) {
		String message = "";
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			result.setMessage("请定位到APP个人中心界面！");
			return result;
		}
		clickView(personalCenterPage.getPersonalEdit());
		//修改用户昵称信息  昵称限制在4-25个字符
		if(username != null && !username.isEmpty() && username != "") {
			clickView(personalCenterPage.getUsername());
			inputManyText(username);
			clickView(personalCenterPage.getNameEditSaveBtn());
			waitAuto();
			press();
			if(personalCenterPage.isEditedSuccessed()) {
				result.setActual(ConstantUtils.ASSERT_TRUE);
				message += "编辑用户昵称成功;";
			}else {
				message += "编辑用户昵称失败;";
			}
			result.setMessage(message);
//			if(oldUsername.equals(username)) {
//				message += "---(编辑前后用户昵称一致);";
//			}else {
//				message += "---(编辑前后用户昵称不一致);";
//			}
		}
		//修改用户登录账号   登录账号唯一性验证 6-20个字符
		if(account != null && !account.isEmpty() && account != "") {
			String oldAccount = personalCenterPage.getOldAccountText();
//			System.out.println("之前的account为：" + oldAccount);
			clickView(personalCenterPage.getAccount());
			if(account.equals(BASE_ACCOUNT))
				account = getValue(Long.parseLong(BASE_ACCOUNT),oldAccount);
//			System.out.println("写入的account为：" + account);
			inputManyText(account);
			clickView(personalCenterPage.getAccountEditSaveBtn());
			waitAuto();
			press();
			sleep(3000);
			if(personalCenterPage.isEditedSuccessed()) {
				result.setActual(ConstantUtils.ASSERT_TRUE);
				message += "编辑用户登录账号成功;";
			}else {
				message += "编辑用户登录账号失败;";
			}
		}
		result.setMessage(message);
		sleep(3000);
		backHome();
//		System.out.println("已经返回到首页！！！");
		return result;
	}
	/**
	 * 用户密码修改
	 * @param oldPassword
	 * @param newPassword
	 * @param newPwdConfirm
	 * @return
	 */
	public ResultUtils personalEditPwd(String oldPassword,String newPassword,String newPwdConfirm) {
		String message = "";
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			result.setMessage("请定位到APP个人中心界面！");
			return result;
		}
		clickView(personalCenterPage.getPersonalEdit());
		//修改用户登录密码   长度在6-25个字符，且修改前后密码不同
		String account = personalCenterPage.getOldAccountText();
		if(oldPassword == null) {
			oldPassword = getValue(Long.parseLong(BASE_PWD),account);
		}
		boolean change = true;//是否变更新密码
		if(newPassword == null && newPwdConfirm == null) {
			newPwdConfirm = newPassword = oldPassword;
			change = false;
		}
		if(oldPassword != null && !newPassword.isEmpty() && newPwdConfirm != "") {
			clickView(personalCenterPage.getPassword());
			if(newPassword.equals(newPwdConfirm) && newPassword.equals(BASE_PWD) && change) {
				newPassword = account;
				newPwdConfirm = newPassword;
			}
//			System.out.println("oldpwd is:" + oldPassword + ",newpwd is:" + newPassword + ",newpwdconfirm is:" + newPwdConfirm);
			inputManyText(oldPassword,newPassword,newPwdConfirm);
			clickView(personalCenterPage.getPasswordSaveBtn());
			waitAuto();
			press();
			if(personalCenterPage.isEditedSuccessed()) {
				result.setActual(ConstantUtils.ASSERT_TRUE);
				message += "修改用户登录密码成功;";
			}else {
				message += "修改用户登录密码失败;";
			}
		}
		result.setMessage(message);
		backHome();
		return result;
	}
	/**
	 * 6.用户退出
	 * @return
	 */
	public ResultUtils logout() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			result.setMessage("请定位到APP个人中心界面！");
			return result;
		}
//		System.out.println("该用户是否已经退出：" + personalCenterPage.isLogout());
		if(!personalCenterPage.isLogout()) {
			clickView(personalCenterPage.getLogoutBtn());
			clickView(personalCenterPage.getLogoutConfirmBtn());
		}
		
		if(!personalCenterPage.isLogout()) {
			result.setMessage("出错！！！");
		}else {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("验证用户退出登录成功");
		}
		backHome();
		return result;
	}
	
	/**
	 * 5.APP分享
	 * TODU
	 * @return
	 */
	public ResultUtils share() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			return result;
		}
		clickView(personalCenterPage.getShareBtn());
		
		return result;
	}
	/**
	 * 2.我的收藏
	 * 用uiAutomator方式查找元素，不是很稳定，目前一直报错，该方法暂时搁置
	 * @return
	 */
	public ResultUtils myCollectionWithUiAutomator() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			return result;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		UiAutomatorAppium uiAutomatorAppium = new UiAutomatorAppium(driver);
		AndroidElement theEnd = uiAutomatorAppium.swipToElementAppear();
		if(theEnd != null) {
//			System.out.println(theEnd.getText());

			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏里面没有收藏的新闻列表");
			return result;
		}else {
			result.setMessage("出错啦~~~ ");
		}
		backHome();
		return result;
	}
	
	/**
	 * 2.我的收藏
	 * 2.1 取消我的收藏的第一条收藏的新闻验证
	 * @return
	 */
	public ResultUtils clearFirstCollection() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			return result;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		AndroidElement firstCollection = personalCenterPage.getCollectionParent();
		if(firstCollection == null) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏里面没有收藏的新闻列表");
			backHome();
			return result;
		}
		clickView(personalCenterPage.getCollectionEditBtn());
		clickView(personalCenterPage.getCollectionDeleteBtn());
		clickView(personalCenterPage.getCollectionUpdateBtn());
		AndroidElement firstNews = personalCenterPage.getCollectionParent();
		if(firstNews != null && firstCollection.getText() == firstNews.getText()) {
			result.setMessage("我的收藏---取消收藏的第一条新闻---结果失败");
		}else {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏---取消收藏的第一条新闻---结果成功");
		}
		sleep(3000);
		backHome();
		return result;
	}
	/**
	 * 2.我的收藏
	 * 2.2 清空我的收藏的验证
	 * @return
	 */
	public ResultUtils clearAllCollection() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			result.setMessage("请定位到个人中心主页！");
			return result;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		AndroidElement firstCollection = personalCenterPage.getCollectionParent();
		if(firstCollection == null) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏里面没有收藏的新闻列表");
			backHome();
			return result;
		}
		clickView(personalCenterPage.getCollectionEditBtn());
		clickView(personalCenterPage.getClearCollectionBtn());
		clickView(personalCenterPage.getClearCollectionSure());
		sleep(1000);
		if(personalCenterPage.hasCollections()) {
			result.setMessage("我的收藏---清空我的收藏失败");
		}else {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏---清空我的收藏成功");
		}
		backHome();
		return result;
	}
	/**
	 * 2.我的收藏
	 * 2.3 进入第一条收藏的新闻的详情页面
	 * @return
	 */
	public ResultUtils readFirstCollection() {
		ResultUtils result = new ResultUtils();
		result.setActual(ConstantUtils.ASSERT_FALSE);
		if(!isPersonalCenter()) {
			return result;
		}
		clickView(personalCenterPage.getCollectionBtn());
		//收藏的新闻的第一条
		AndroidElement firstCollection = personalCenterPage.getCollectionParent();
		if(firstCollection == null) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("我的收藏里面没有收藏的新闻列表");
			backHome();
			return result;
		}
		//获得新闻标题
		String text = firstCollection.getText();
//		System.out.println("text is:" + text);
		clickView(firstCollection);
		waitAuto();
		String title = personalCenterPage.getNewsTitle();
//		System.out.println("title is:" + title);
		if(text.equals(title)) {
			result.setActual(ConstantUtils.ASSERT_TRUE);
			result.setMessage("成功！");
		}
		backHome();
		return result;
	}
	/**
	 * 对于登陆账号，密码做对应处理，方便测试
	 * 如 登陆账号18091969298  进行+/-100，密码同理
	 * @param base 测试用户的初始账号 18091969298
	 * @return
	 */
	private String getValue(long base,String oldAcccount) {
		String editAccount = null;
		
		switch ((int)(Long.parseLong(oldAcccount) - base)) {
			case 0 :
				editAccount = base + 100 + "";
				break;
			case 100 :
				editAccount = base + "";
				break;

			default :
				break;
		}
//		System.out.println("处理过后的数据为：" + editAccount);
		return editAccount;
	}
	/**
	 * 需要测试的case走完之后返回到首页，为下一次的测试准备
	 */
	private void backHome() {
		while (!homePage.isHomePage()) {
			back();
		}
	}
	/**
	 * 是否在个人中心主页面
	 * @return
	 */
	private boolean isPersonalCenter() {
		if(!homePage.isHomePage()) {
			return ConstantUtils.ASSERT_FALSE;
		}
		clickView(personalCenterPage.getCuentaBtn());
		return ConstantUtils.ASSERT_TRUE;
	}

}
