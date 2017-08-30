package com.yiibai.primera.testng.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.yiibai.primera.testng.base.Assertion;

/**
 * Created by ChenXiaoGuai on 2017/08/16.
 */

public class AssertionListener extends TestListenerAdapter {

    /**
     * 测试方法开始的时候回调
     * @param result
     */
    @Override
    public void onTestStart(ITestResult result) {
        Assertion.flag = true;
        Assertion.errors.clear();
    }


    /**
     * 测试终止时候回调
     * @param tr
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        this.handleAssertion(tr);
    }


    /**
     * Test跳过 的时候执行
     * @param tr
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
        this.handleAssertion(tr);
    }

    /**
     * Test运行完毕时候执行
     * @param tr
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        this.handleAssertion(tr);
    }

    private int index = 0;    //错误行号


    /**
     * 处理断言，每个Test执行完毕回调
     * @param tr 测试结果
     */
    private void handleAssertion(ITestResult tr){
        if(!Assertion.flag){  //为假，就是断言出错了就执行下面的
            //获取异常
            Throwable throwable = tr.getThrowable();
            if(throwable==null){
                throwable = new Throwable();
            }
            //获取异常堆栈信息
            StackTraceElement[] traces = throwable.getStackTrace();

            //创建要输出的所有堆栈信息
            StackTraceElement[] alltrace = new StackTraceElement[0];

            //循环获取断言的异常信息，
            for (Error e : Assertion.errors) {
                //获取错误的堆栈数组信息
                StackTraceElement[] errorTraces = e.getStackTrace();
                //
                StackTraceElement[] et = getKeyStackTrace(tr, errorTraces);
                //设置异常信息堆栈内容
                StackTraceElement[] message = handleMess(e.getMessage(),tr);

                //行号初始化为0
                index = 0;
                //堆栈信息合并
                alltrace = merge(alltrace, message);
                alltrace = merge(alltrace, et);
            }

            //如果异常信息不为空
            if(traces!=null){
                traces = getKeyStackTrace(tr, traces);
                alltrace = merge(alltrace, traces);
            }

            //保存异常信息
            throwable.setStackTrace(alltrace);
            tr.setThrowable(throwable);

            //清空
            Assertion.flag = true;
            Assertion.errors.clear();

            //输出异常信息
            tr.setStatus(ITestResult.FAILURE);
        }
    }


    /**
     * 获取堆栈信息
     * @param tr
     * @param stackTraceElements
     * @return
     */
    private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){

        List<StackTraceElement> ets = new ArrayList<>();
        //循环获取信息
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            //返回测试类的堆栈信息
            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){
                ets.add(stackTraceElement);
                index = stackTraceElement.getLineNumber();  //错误行号
            }
        }
        return ets.toArray(new StackTraceElement[ets.size()]);

    }

    /**
     * 合并两个堆栈信息
     * @param traces1 第一个
     * @param traces2
     * @return
     */
    private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){

        StackTraceElement[] result = Arrays.copyOf(traces1, traces1.length + traces2.length);
        System.arraycopy(traces2, 0, result, traces1.length, traces2.length);
        return result;
    }

    /**
     * 处理消息提示内容
     * @param mess 报错信息
     * @param tr 结果描述
     * @return
     */
    private StackTraceElement[] handleMess(String mess,ITestResult tr){
        String message = "\n报错信息: "+mess;
        String method = "\n报错方法名:"+tr.getMethod().getMethodName();
        String className = "\n报错类:"+tr.getTestClass().getRealClass().getSimpleName();
        return new StackTraceElement[]{
                new StackTraceElement(message,  //内容
                        method,                         //方法名
                        className+"\n报错行号",       //文件名
                        index)};
    }
}