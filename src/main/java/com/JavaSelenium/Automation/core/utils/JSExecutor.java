package com.JavaSelenium.Automation.core.utils;

import com.JavaSelenium.Automation.core.driver.DriverSetup;
import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.logger.Logs;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class JSExecutor {

    private JSExecutor(){}
    public static void jsExecutor(String value) throws FrameworkExceptions {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverSetup.getDriver();
            js.executeScript(value);
        } catch (WebDriverException we) {
            throw new FrameworkExceptions("Failed in jsExecutor method"+we);
        }
    }

    // Java Script Executor
    public static void jsExecutor(String value, WebElement locator) throws FrameworkExceptions {
        try {
            getJavaScriptExec().executeScript(value, locator);
        } catch (WebDriverException we) {
            throw new FrameworkExceptions("Exception occurred"+we);
        }
    }

    // Click using Java Script Executor
    public static void jsClick(WebElement element){
        getJavaScriptExec().executeScript("arguments[0].click();", element);
        Waits.waitForPageLoadJS();
    }

    public static JavascriptExecutor getJavaScriptExec() {
        return (JavascriptExecutor) DriverSetup.getDriver();
    }

    // Get Value using Java Script Executor
    public static String jsGetValue(WebElement element){
        return getJavaScriptExec().executeScript("return arguments[0].value;",element).toString();
    }

    public static void mouseHover(WebElement element)
    {
        try {
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) DriverSetup.getDriver()).executeScript(mouseOverScript, element);
        }
        catch (Exception e)
        {
            Logs.error("Failed in mouseHover method in JSExecutor class");
        }
    }

    public static void jsScrollIntoView(WebElement element){
        getJavaScriptExec().executeScript("arguments[0].scrollIntoView(true);",element);
    }
}
