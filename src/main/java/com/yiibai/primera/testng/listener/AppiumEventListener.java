package com.yiibai.primera.testng.listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yiibai.primera.testng.utils.ConstantUtils;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;

/** 
 * @version:1.0 
 * @description:appium事件监听 
 * @author songer.xing 
 * @date:2017-4-19 
 * @history: 
 */  
  
public class AppiumEventListener implements AppiumWebDriverEventListener {  
  
    static Logger log = LogManager.getLogger(AppiumEventListener.class  
            .getCanonicalName());  
    private String locator = null;  
//    private MyDriver myDriver;  
  
    @Override  
    public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {}  
    @Override  
    public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {}  
    @Override  
    public void beforeFindBy(final By by, WebElement element, WebDriver driver) {  
        try {  
            locator = splitBy(by);  
        } catch (Exception e) {  
            log.error("by不能按格式切割！");  
        }  
        try {  
            WebDriverWait wait = new WebDriverWait(driver,  
                    10);  
            wait.until(new ExpectedCondition<WebElement>() {  
                @Override  
                public WebElement apply(WebDriver driver) {  
                    return driver.findElement(by);  
                }  
            }).isDisplayed();  
            log.info("beforeFindBy:seaching............"  
                    + driver.findElement(by));  
        } catch (Exception e) {  
            try {  
                log.error("beforeFindBy监听" + ConstantUtils.LISTENER_WAIT_SECONDS  
                        + "秒" + " " + splitBy(by) + "不可见");  
            } catch (Exception e2) {  
                log.error("beforeFindBy监听" + ConstantUtils.LISTENER_WAIT_SECONDS  
                        + "秒,by不能按格式切割！");  
            }  
        }  
    }  
    @Override  
    public void afterFindBy(By by, WebElement element, WebDriver driver) {}  
    @Override  
    public void beforeClickOn(WebElement element, WebDriver driver) {  
        try {  
            log.info("beforeClickOn:Clicking............"  
                    + splitElement(element));  
        } catch (Exception e) {  
            log.error("element不能按格式切割！");  
        }  
  
    }  
    @Override  
    public void afterClickOn(WebElement element, WebDriver driver) {}  
    @Override  
    public void beforeNavigateBack(WebDriver arg0) {}  
    @Override  
    public void afterNavigateBack(WebDriver arg0) {}  
    @Override  
    public void beforeNavigateForward(WebDriver arg0) {}  
    @Override  
    public void afterNavigateForward(WebDriver arg0) {}  
    @Override  
    public void beforeNavigateRefresh(WebDriver arg0) {}  
    @Override  
    public void afterNavigateRefresh(WebDriver arg0) {}  
    @Override  
    public void beforeNavigateTo(String arg0, WebDriver arg1) {}  
    @Override  
    public void afterNavigateTo(String arg0, WebDriver arg1) {}  
    @Override  
    public void beforeScript(String arg0, WebDriver arg1) {}  
    @Override  
    public void afterScript(String arg0, WebDriver arg1) {}  
    @Override  
    public void onException(Throwable error, WebDriver arg1) {}  
    
    private String splitElement(WebElement element) {  
        String str = element.toString().split("-> ")[1];  
        return str.substring(0, str.length() - 1);  
    }  
  
    private String splitBy(By by) {  
        String str = by.toString().split("-")[1].toString().split(":")[3];  
        return str.substring(0, str.length() - 3);  
    }
}