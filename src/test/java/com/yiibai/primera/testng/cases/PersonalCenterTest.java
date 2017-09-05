package com.yiibai.primera.testng.cases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.yiibai.primera.testng.base.InitAppium;
import com.yiibai.primera.testng.operation.PersonalCenterOperate;

/**
 * 个人中心页面测试用例
 * 3.1、右上角设置的测试（包含字体大小设置、选择流量方式--主要体现在图片大小、是否开启接收通知的测试、缓存的清除的测试）
 * 3.2、我的收藏的测试  点击进去查看我的收藏，也可以编辑我的收藏--取消收藏、清空我的收藏
 * 3.3、版本更新的测试---如果有更新的版本，会显示红点
 * 3.4、分享按钮的测试
 * 
 * Created by ChenXiaoGuai on 2017/08/16.
 */
public class PersonalCenterTest extends InitAppium {

    private PersonalCenterOperate personalCenterOperate;


    @BeforeMethod
    public void initDriver(){
        Assert.assertNotNull(driver);
        personalCenterOperate = new PersonalCenterOperate(driver);
    }

    /**
     * 我的收藏的测试case
     */
    @Test(priority = 0)
    public void myCollection(){
        boolean flag = personalCenterOperate.myCollectionNew();
        Assert.assertTrue(flag, "测试我的收藏---取消第一条收藏的新闻是否成功");
        print("测试我的收藏---取消第一条收藏的新闻是否成功:"+ flag);
    }

}
