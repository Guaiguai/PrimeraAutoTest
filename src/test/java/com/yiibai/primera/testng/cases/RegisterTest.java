package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.yiibai.primera.testng.base.Assertion;

import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.RegisterOperate;
import com.yiibai.primera.testng.util.ResultUtil;

/**
 * 用户注册测试用例 
 * email 用户邮箱地址 验证规则是：必须含有'@','.' 不含有中文
 * pass 要求两次输入密码一致 长度在6-25个字符 （小于6 大于25 两次密码不一致） 
 * 邮箱没有验证其真实存在性
 * Created by ChenXiaoGuai on 2017/08/10.
 */
public class RegisterTest extends InitAppium {

	private RegisterOperate registerOperate;

	@BeforeMethod
	public void initDriver() {
		Assert.assertNotNull(driver);
		registerOperate = new RegisterOperate(driver);
	}
	
	/**
	 * 测试正常注册成功
	 */
	@Test(priority = 14)
	public void RegisterOk() {
		ResultUtil result = registerOperate.register("15906651240@qq.com",
				"15906651240", "15906651240");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱和密码均符合规则的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱缺失关键字符'@'和'.',注册失败
	 */
	@Test(priority = 1)
	public void EmailMissKeyChars() {
		ResultUtil result = registerOperate.register("18091969298",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱缺失关键字符'@'和'.'的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱只包含'@'，注册失败
	 */
	@Test(priority = 2)
	public void EmailMissKeyCharSpot() {
		ResultUtil result = registerOperate.register("18091969298@qqcom",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱只包含'@'的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱只包含'.'，注册失败
	 */
	@Test(priority = 3)
	public void EmailMissKeyCharAit() {
		ResultUtil result = registerOperate.register("18091969298qq.com",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱只包含'.'的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱只包含'@',且以其结尾，注册失败
	 */
	@Test(priority = 4)
	public void EmailOnlyCharAitEnd() {
		ResultUtil result = registerOperate.register("18091969298@",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱只包含'@',且以其结尾的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱只包含'.',且以其结尾，注册失败
	 */
	@Test(priority = 5)
	public void EmailOnlyCharSpotEnd() {
		ResultUtil result = registerOperate.register("18091969298qq.",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱只包含'.',且以其结尾的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱包含'@','.',且以'.'结尾，注册失败
	 */
	@Test(priority = 6)
	public void EmailSpotEnd() {
		ResultUtil result = registerOperate.register("18091969298@qq.",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱包含'@','.',且以'.'结尾的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱包含'@','.',且以'@'结尾，注册失败
	 */
	@Test(priority = 7)
	public void EmailAtEnd() {
		ResultUtil result = registerOperate.register("18091969298.qq@",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱包含'@','.',且以'@'结尾的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * 邮箱包含'@','.',且符号位置交换，注册失败
	 */
	@Test(priority = 8)
	public void EmailWrong() {
		ResultUtil result = registerOperate.register("18091969298.qq@com",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("邮箱包含'@','.',且符号位置交换的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * pass 长度小于6，注册失败
	 */
	@Test(priority = 9)
	public void PwdLessThanSix() {
		ResultUtil result = registerOperate.register("18091969298@qq.com",
				"12345", "12345");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("pass 长度小于6的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * pass 长度大于25，注册失败
	 */
	@Test(priority = 10)
	public void PwdMoreThanTwentyFive() {
		ResultUtil result = registerOperate.register("18091969298@qq.com",
				"123456789123456789123456789", "123456789123456789123456789");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("pass 长度大于25的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * pass 是中文，注册失败
	 */
	@Test(priority = 11)
	public void PwdIsChinese() {
		ResultUtil result = registerOperate.register("18091969298@qq.com",
				"测试测试测试测试", "测试测试测试测试");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("pass 是中文的情况下是否注册成功:" + result.getActual());
	}
	/**
	 * pass 是空格，注册失败
	 */
	@Test(priority = 12,enabled = false)
	public void PwdIsNull() {
		ResultUtil result = registerOperate.register("18091969298@qq.com",
				"", "");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("pass 是空格的情况下是否注册成功:" + result.getActual());
	}
	

	/**
	 * 测试 用户已经存在的注册是否成功
	 * TODU  运用到testng的依赖其他测试的通过
	 */
	@Test(priority = 13)
	public void RegisterExited() {
		ResultUtil result = registerOperate.register("550881764@qq.com",
				"18091969298", "18091969298");
        Assertion.verifyEquals(result.getActual(),result.getExcepted(),result.getMessage());
		print("用户已经存在的情况下是否注册成功:" + result.getActual());
	}
}
