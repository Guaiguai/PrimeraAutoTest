package com.yiibai.primera.testng.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *  基础方法类
 * @author ChenXiaoGuai
 *
 */
public class MethodUtil {

	/**
	 * 中文判断--正则形式
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
