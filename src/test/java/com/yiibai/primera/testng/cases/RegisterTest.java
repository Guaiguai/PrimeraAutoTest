package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.yiibai.primera.testng.base.Assertion;

import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.RegisterOperate;

/**
 * 用户注册测试用例 
 * email 用户邮箱地址 验证规则是：必须含有'@','.' 不含有中文
 * pass 要求两次输入密码一直 长度在6-25个字符 （小于6 大于25 两次密码不一致） 
 * 邮箱没有验证其真实存在性
 * Created by ChenXiaoGuai on 2017/08/10.
 */
@Test(priority = 1)
public class RegisterTest extends InitAppium {

	private RegisterOperate registerOperate;

	@BeforeClass
	public void initDriver() {
		Assert.assertNotNull(driver);
		registerOperate = new RegisterOperate(driver);
	}
	
	/**
	 * 测试正常注册成功
	 */
	@Test(priority = 14)
	public void RegisterOk() {
		boolean flag = registerOperate.register("15906651240@qq.com",
				"15906651240", "15906651240");
		Assertion.verifyEquals(flag, true, "邮箱和密码均符合规则的情况下是否注册成功");
		print("邮箱和密码均符合规则的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱缺失关键字符'@'和'.',注册失败
	 */
	@Test(priority = 1)
	public void EmailMissKeyChars() {
		boolean flag = registerOperate.register("18091969298",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱缺失关键字符'@'和'.'的情况下是否注册成功");
		print("邮箱缺失关键字符'@'和'.'的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱只包含'@'，注册失败
	 */
	@Test(priority = 2)
	public void EmailMissKeyCharSpot() {
		boolean flag = registerOperate.register("18091969298@qqcom",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱只包含'@'的情况下是否注册成功");
		print("邮箱只包含'@'的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱只包含'.'，注册失败
	 */
	@Test(priority = 3)
	public void EmailMissKeyCharAit() {
		boolean flag = registerOperate.register("18091969298qq.com",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱只包含'.'的情况下是否注册成功");
		print("邮箱只包含'.'的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱只包含'@',且以其结尾，注册失败
	 */
	@Test(priority = 4)
	public void EmailOnlyCharAitEnd() {
		boolean flag = registerOperate.register("18091969298@",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱只包含'@',且以其结尾的情况下是否注册成功");
		print("邮箱只包含'@',且以其结尾的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱只包含'.',且以其结尾，注册失败
	 */
	@Test(priority = 5)
	public void EmailOnlyCharSpotEnd() {
		boolean flag = registerOperate.register("18091969298qq.",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱只包含'.',且以其结尾的情况下是否注册成功");
		print("邮箱只包含'.',且以其结尾的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱包含'@','.',且以'.'结尾，注册失败
	 */
	@Test(priority = 6)
	public void EmailSpotEnd() {
		boolean flag = registerOperate.register("18091969298@qq.",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱包含'@','.',且以'.'结尾的情况下是否注册成功");
		print("邮箱包含'@','.',且以'.'结尾的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱包含'@','.',且以'@'结尾，注册失败
	 */
	@Test(priority = 7)
	public void EmailAitEnd() {
		boolean flag = registerOperate.register("18091969298.qq@",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱包含'@','.',且以'@'结尾的情况下是否注册成功");
		print("邮箱包含'@','.',且以'@'结尾的情况下是否注册成功:" + flag);
	}
	/**
	 * 邮箱包含'@','.',且符号位置交换，注册失败
	 */
	@Test(priority = 8)
	public void EmailWrong() {
		boolean flag = registerOperate.register("18091969298.qq@com",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "邮箱包含'@','.',且符号位置交换的情况下是否注册成功");
		print("邮箱包含'@','.',且符号位置交换的情况下是否注册成功:" + flag);
	}
	/**
	 * pass 长度小于6，注册失败
	 */
	@Test(priority = 9)
	public void PwdLessThanSix() {
		boolean flag = registerOperate.register("18091969298@qq.com",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "pass 长度小于6的情况下是否注册成功");
		print("pass 长度小于6的情况下是否注册成功:" + flag);
	}
	/**
	 * pass 长度大于25，注册失败
	 */
	@Test(priority = 10)
	public void PwdMoreThanTwentyFive() {
		boolean flag = registerOperate.register("18091969298@qq.com",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "pass 长度大于25的情况下是否注册成功");
		print("pass 长度大于25的情况下是否注册成功:" + flag);
	}
	/**
	 * pass 是中文，注册失败
	 */
	@Test(priority = 11)
	public void PwdIsChinese() {
		boolean flag = registerOperate.register("18091969298@qq.com",
				"测试测试测试测试", "测试测试测试测试");
		Assertion.verifyEquals(flag, false, "pass 是中文的情况下是否注册成功");
		print("pass 是中文的情况下是否注册成功:" + flag);
	}
	/**
	 * pass 是空格，注册失败
	 */
	@Test(priority = 12)
	public void PwdIsNull() {
		boolean flag = registerOperate.register("18091969298@qq.com",
				"", "");
		Assertion.verifyEquals(flag, false, "pass 是空格的情况下是否注册成功");
		print("pass 是空格的情况下是否注册成功:" + flag);
	}
	

	/**
	 * 测试 用户已经存在的注册是否成功
	 * TODU  运用到testng的依赖其他测试的通过
	 */
	@Test(priority = 13)
	public void RegisterExited() {
		boolean flag = registerOperate.register("550881764@qq.com",
				"18091969298", "18091969298");
		Assertion.verifyEquals(flag, false, "用户已经存在的情况下是否注册成功");
		print("用户已经存在的情况下是否注册成功:" + flag);
	}
}
