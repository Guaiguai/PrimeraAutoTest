package com.yiibai.primera.testng.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.This;


/**
 *  基础方法类
 * @author ChenXiaoGuai
 *
 */
public class MethodUtils {

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
	/**
	 * 获得当前方法的名称
	 * @return
	 */
	public static String getFileLineMethod() {  
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        for (int i = 0; i < stacktrace.length; i++) {
//			System.out.println("第" + i + "次循环，方法名称是：" + stacktrace[i].getMethodName());
//		}
        StackTraceElement traceElement = stacktrace[2];  
        StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("]");
        return toStringBuffer.toString();  
    }
}
